import java.util.ArrayList;
import java.util.Random;

public class Combat {

    private static final String[] DIRECTIONS = {"north", "east", "south", "west"};
    private static final String[] ENEMYNUMBER = {"first", "second", "third", "fourth"};

    private Player player;
    private Room room;
    private UserInterface userInterface;
    private Audio audio;
    private Random random;
    private boolean fled;

    public Combat(Player player, Room room, UserInterface userInterface, Audio audio, Random random) {
        this.player = player;
        this.room = room;
        this.userInterface = userInterface;
        this.audio = audio;
        this.random = random;
    }

    public CombatResult run() {
        userInterface.printHighlightedMessage("*** ENTERING COMBAT ***");
        userInterface.printMessage("\033[37m[Enter the number of which move you'd like to perform:]\u001b[0m");

        while (true) {
            playerTurn();

            if (fled) {
                return CombatResult.FLED;
            }
            if (room.getEnemies().isEmpty()) {
                return CombatResult.VICTORY;
            }

            enemyTurn();

            if (player.getHealth() <= 0) {
                return CombatResult.DEFEAT;
            }
        }
    }

    private RollResult combatRoll() {
        int diceRoll = random.nextInt(20) + 1;
        if (diceRoll == 20) {
            return RollResult.CRITICAL_HIT;
        }

        if (diceRoll > 1) {
            return RollResult.HIT;
        } else {
            return RollResult.CRITICAL_FAIL;
        }
    }

    private int calculateDamage(RollResult result, int baseDamage) {
        return switch (result) {
            case CRITICAL_HIT -> baseDamage * 2;
            case HIT -> baseDamage;
            case CRITICAL_FAIL -> 0;
        };
    }

    private void playerTurn() {
        boolean turnUsed = false;

        while (!turnUsed) {
            ArrayList<CombatOption> options = buildOptions();

            userInterface.printMessage("\nWhat do you do?");
            for (int i = 0; i < options.size(); i++) {
                userInterface.printMessage("\033[0;97m" + (i + 1) + ". " + "[" + options.get(i).getLabel() + "]\u001b[0m");
            }

            int choice = userInterface.getChoice(options.size());
            turnUsed = performOption(options.get(choice - 1));
        }
    }

    private boolean performOption(CombatOption option) {
        return switch (option.getAction()) {
            case ATTACK -> attack(player.getEquipped(), option.getTarget(), option.getTargetName());
            case EAT -> eat();
            case SWITCH_WEAPON -> switchWeapon();
            case FLEE -> flee();
            case HOLD -> holdGround();
        };
    }

    private boolean holdGround() {
        userInterface.printMessage("You brace yourself and wait.");
        return true;
    }

    private ArrayList<CombatOption> buildOptions() {
        ArrayList<CombatOption> options = new ArrayList<>();
        Weapon weapon = player.getEquipped();
        ArrayList<Enemy> enemies = new ArrayList<>(room.getEnemies());
        ArrayList<String> labels = enemyLabels(enemies);

        if (weapon != null && weapon.canUse()) {
            for (int i = 0; i < enemies.size(); i++) {
                Enemy target = enemies.get(i);
                String label = labels.get(i);
                options.add(new CombatOption("Attack the " + label + " with the " + weapon.getShortName(),
                        CombatAction.ATTACK, target, label));
            }
        }

        if (!player.getInventory().isEmpty()) {
            options.add(new CombatOption("Eat something (free action)", CombatAction.EAT));
            options.add(new CombatOption("Switch weapon", CombatAction.SWITCH_WEAPON));
        }

        if (!fleeDirections().isEmpty()) {
            options.add(new CombatOption("Flee", CombatAction.FLEE));
        }

        if (options.isEmpty()) {
            options.add(new CombatOption("Hold your ground", CombatAction.HOLD));
        }

        return options;
    }

    private ArrayList<String> enemyLabels(ArrayList<Enemy> enemies) {
        ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < enemies.size(); i++) {
            String name = enemies.get(i).getShortName();
            int total = 0;
            int before = 0;

            for (int j = 0; j < enemies.size(); j++) {
                if (enemies.get(j).getShortName().equals(name)) {
                    total++;
                    if (j < i) before++;
                }

            }

            if (total > 1 && before < ENEMYNUMBER.length) {
                labels.add(ENEMYNUMBER[before] + " " + name);
            } else {
                labels.add(name);
            }
        }
        return labels;
    }

    private boolean attack(Weapon weapon, Enemy target, String label) {
        weapon.use();
        RollResult result = combatRoll();
        int damage = calculateDamage(result, weapon.getDamage());

        audio.play(weapon.getAttackSound());
        userInterface.printMessage(weapon.getAttackMessage(label, result));

        if (damage > 0 && target.hit(damage)) {
            userInterface.printMessage("The " + label + " is down.");
        }
        return true;
    }

    private boolean eat() {
        String name = askForItemName("Eat what?");

        if (name == null) {
            return false;
        }

        EatResult result = player.eat(name);
        userInterface.printEatResult(result, name);

        if (result == EatResult.EATEN) {
            userInterface.printMessage("Health: " + player.getHealth() + ".");
        }
        return false;
    }

    private boolean switchWeapon() {
        String name = askForItemName("Switch to which weapon?");
        if (name == null) {
            return false;
        }

        WeaponResult result = player.equip(name);
        userInterface.printEquipResult(result, name);

        return result == WeaponResult.IS_WEAPON;
    }

    private String askForItemName(String question) {
        userInterface.printInventoryList((player.getInventory()), player.getEquipped());
        userInterface.printMessage(question + " (type its name, or 'cancel')");

        String name = userInterface.getInput().trim().toLowerCase();

        if (name.isEmpty() || name.equals("cancel")) {
            userInterface.printMessage("You change your mind");
            return null;
        }
        return name;
    }

    private ArrayList<String> fleeDirections() {
        ArrayList<String> result = new ArrayList<>();

        for (String direction : DIRECTIONS) {
            Room target = player.getRoomInDirection(direction);
            if (target != null && !target.needsPassword() && !target.isLockedFor(player)) {
                result.add(direction);
            }
        }
        return result;
    }

    private boolean flee() {
        ArrayList<String> directions = fleeDirections();

        userInterface.printMessage("Flee where?");
        for (int i = 0; i < directions.size(); i++) {
            Room target = player.getRoomInDirection(directions.get(i));
            userInterface.printMessage((i + 1) + ". " + directions.get(i) + " (" + target.getName() + ")");
        }
        int stay = directions.size() + 1;
        userInterface.printMessage(stay + ". Stay and fight.");

        int choice = userInterface.getChoice(stay);
        if (choice == stay) {
            return false;
        }

        userInterface.printMessage("You turn and run!");
        enemyTurn();
        player.move(directions.get(choice - 1));
        fled = true;
        return true;
    }

    // FJENDERNES TUR
    private void enemyTurn() {
        ArrayList<Enemy> enemies = new ArrayList<>(room.getEnemies());
        ArrayList<String> labels = enemyLabels(enemies);

        for (int i = 0; i < enemies.size(); i++) {
            Weapon weapon = enemies.get(i).getWeapon();

            if (weapon == null) {
                continue;
            }

            String name = labels.get(i);
            RollResult result = combatRoll();
            int damage = calculateDamage(result, weapon.getDamage());

            if (damage >= player.getHealth()) {
                result = RollResult.CRITICAL_FAIL;
                damage = 0;
            }

            userInterface.printMessage(weapon.getEnemyAttackMessage(name, result));

            if (damage > 0) {
                player.hit(damage);
                userInterface.printMessage("You take " + damage + " damage. Health: " + player.getHealth() + ".");
            }
        }
    }
}