import java.util.ArrayList;

public class Adventure {

    // Vores variable
    private Player player;
    private ArrayList<Enemy> enemies;
    private UserInterface userInterface;
    private Map map;
    private boolean gameRunning;

    // Konstruktør
    public Adventure(UserInterface userInterface) {
        this.userInterface = userInterface;
        map = new Map();
    }

    private void runConversation(DialogueNode startNode) {
        DialogueNode current = startNode;

        while (current != null) {
            userInterface.printDialogueNode(current);

            if (current.getOptions().isEmpty()) {
                break;
            }

            int choice = userInterface.getChoice(current.getOptions().size());
            DialogueOption chosen = current.getOptions().get(choice - 1);
            current = chosen.getNextNode();
        }

        userInterface.printMessage("You step away from the terminal.");
    }

    public void printRoomDescription() {
        userInterface.printHighlightedMessage(player.getCurrentRoom().getName());
        userInterface.printMessage(player.getCurrentRoom().getDescription());
        userInterface.printItemList(player.getCurrentRoom().getItems());
        userInterface.printEnemyList(player.getCurrentRoom().getEnemies());
    }

    public void useTerminal() {
        DialogueNode dialogue = player.getCurrentRoom().getTerminalDialogue();

        if (dialogue == null) {
            userInterface.printMessage("There is no terminal here.");
        } else {
            runConversation(dialogue);
        }
    }

    public void checkHealth() {
        if (player.getHealth() >= 100) {
            userInterface.printMessage("Health: " + player.getHealth() + ". You are in perfect health.");
        } else if (player.getHealth() >= 50 && player.getHealth() <= 99) {
            userInterface.printMessage("Health: " + player.getHealth() + ". You are in good health, but avoid fighting right now.");
        } else if (player.getHealth() >= 25 && player.getHealth() <= 49) {
            userInterface.printMessage("Health: " + player.getHealth() + ". You are wounded - find something healthy to eat");
        } else if (player.getHealth() >= 1 && player.getHealth() <= 24) {
            userInterface.printMessage("Health: " + player.getHealth() + ". You are barely alive");
        } else if (player.getHealth() <= 0) {
            userInterface.printMessage("Health: " + player.getHealth() + ". You should be dead.");
        }
    }

    public void takeItems(String argument) {
        if (argument.isEmpty()) {
            userInterface.printMessage("Take what?");
        } else if (argument.equalsIgnoreCase("all")) {
            ArrayList<Item> taken = player.takeAllItems();
            if (taken.isEmpty()) {
                userInterface.printMessage("There is nothing to take here.");
            } else {
                for (Item item : taken) {
                    userInterface.printMessage("You took the " + item.getShortName() + ".");
                }
            }
        } else if (player.takeItem(argument)) {
            userInterface.printMessage("You took the " + argument + ".");
            /*Item item = player.findItem("key card");
            if (item != null) {
                map.openRoomTwo();
            }*/
        }
        else {
            userInterface.printMessage("There is no " + argument + " here.");
        }
    }

    public void look() {
        userInterface.printHighlightedMessage("You are in " + player.getCurrentRoom().getName());
        userInterface.printMessage(player.look());
        userInterface.printItemList(player.getCurrentRoom().getItems());
        userInterface.printEnemyList(player.getCurrentRoom().getEnemies());
    }

    private void dropItem(String argument) {
        if (argument.isEmpty()) {
            userInterface.printMessage("Drop what?");
        } else if (player.dropItem(argument)) {
            userInterface.printMessage("You dropped the " + argument + ".");
        } else {
            userInterface.printMessage("There is no " + argument + " here.");
        }
    }

    public void equip(String argument) {
        if (argument.isEmpty()) {
            userInterface.printMessage("Equip what?");
        } else {
            WeaponResult result = player.equip(argument);
            if (result == WeaponResult.IS_WEAPON) {
                userInterface.printMessage("You equipped the " + argument + ".");
            } else if (result == WeaponResult.NOT_WEAPON) {
                userInterface.printMessage(("That " + argument + " can definitely not be equipped."));
            } else if (result == WeaponResult.NOT_FOUND) {
                userInterface.printMessage("There is no " + argument + " to equip here.");
            }
        }
    }

    public void eat(String argument) {
        if (argument.isEmpty()) {
            userInterface.printMessage("Eat what?");
        } else {
            EatResult result = player.eat(argument);
            if (result == EatResult.EATEN) {
                userInterface.printMessage("You ate the " + argument + ".");
            } else if (result == EatResult.NOT_FOOD) {
                userInterface.printMessage(("That " + argument + " can definitely not be eaten."));
            } else if (result == EatResult.NOT_FOUND) {
                userInterface.printMessage("There is no " + argument + " to eat here.");
            }
        }
    }

    private void attack(String argument) {
        Weapon weapon = player.getEquipped();

        if (weapon == null) {
            userInterface.printMessage("You have no weapon equipped. (Equip a weapon to attack)");
            return;
        }

        if (!weapon.canUse()) {
            userInterface.printMessage("You're out of ammo");
            return;
        }

        Enemy target = player.getCurrentRoom().findEnemy(argument);
        player.attack(argument);
        userInterface.printMessage(weapon.getAttackMessage(target));

        /*else if(argument.isEmpty()) {
            player.attack(argument);
            userInterface.printMessage(weapon.getAttackMessage(player.getCurrentRoom().findEnemy(argument)));
        }
        else
        {
            player.attack(argument);
            userInterface.printMessage(weapon.getAttackMessage(player.getCurrentRoom().findEnemy(argument)));
        }*/
    }

    public void exitGame() {
        userInterface.printMessage("Really? Boring!");
        userInterface.closeScanner();
        gameRunning = false;
    }

    private void tryToMove(String userInput) {
        String direction = userInterface.parseDirection(userInput);

        if(direction == null) {
            userInterface.printMessage("I don't understand that.");
            return;
        }

        MoveResult result = player.move(direction);

        switch(result) {
            case NO_EXIT -> userInterface.printMessage("You can't go that way.");
            case LOCKED -> userInterface.printMessage("The door is locked. You need a key.");
            case NEEDS_PASSWORD -> askForPassword(direction);
            case MOVED -> {
                printRoomDescription();
                player.getCurrentRoom().setBeenInRoomBefore();
            }
        }
    }

    private void askForPassword(String direction){
        Room target = player.getRoomInDirection(direction);

        if(target.getAttemptsLeft() <= 0) {
            userInterface.printMessage("The keypad is dead. Too many wrong attempts.");
            return;
        }

        if(target.getAttemptsLeft() < Room.MAX_PASSWORD_ATTEMPTS){
            userInterface.printMessage("The keypad blinks: " + target.getAttemptsLeft() + " of " + Room.MAX_PASSWORD_ATTEMPTS + " attempts left.");

        }

        userInterface.printMessage("A keypad blocks the door. Enter password: ");
        while(target.getAttemptsLeft() > 0) {
            String input = userInterface.getInput();

            if(input.equalsIgnoreCase("cancel")) {
                userInterface.printMessage("You step away from the keypad.");
                return;
            }

            if(target.tryPassword(input)) {
                userInterface.printMessage("Access granted.");
                tryToMove(direction);
                return;
            }

            if(target.getAttemptsLeft() > 0) {
                userInterface.printMessage("Wrong password. " + target.getAttemptsLeft() + " attempt(s) left.");
                userInterface.printMessage("Type: cancel - to step away");
            }
        }

        userInterface.printMessage("Wrong password. The keypad shuts down.");
    }

        public void startGame() {
            map.buildWorld();
            player = new Player(map.getStartRoom());
            gameRunning = true;
            printRoomDescription();
            player.getCurrentRoom().setBeenInRoomBefore();

            while(gameRunning) {
                String userInput = userInterface.getInput().trim().toLowerCase();
                String[] parts = userInput.split(" ", 2);
                String command = parts[0].toLowerCase();
                String argument = parts.length > 1 ? parts[1].trim() : "";

                switch(command) {
                    case "help" -> userInterface.showHelp();
                    case "look" -> look();
                    case "take" -> takeItems(argument);
                    case "drop" -> dropItem(argument);
                    case "eat" -> eat(argument);
                    case "equip" -> equip(argument);
                    case "attack" -> attack(argument);
                    case "health" -> checkHealth();
                    case "inventory" -> userInterface.printInventoryList(player.getInventory(), player.getEquipped());
                    case "terminal" -> useTerminal();
                    case "access", "use" -> {
                        if(argument.equals("terminal")) useTerminal();
                        else userInterface.printMessage(command + "what?");
                    }
                    case "exit" -> exitGame();
                    default -> tryToMove(userInput);
                }
                }
            }
        }


