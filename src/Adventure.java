import java.util.ArrayList;

public class Adventure {

    // Vores variable
    private Player player;
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

            if(current.getOptions().isEmpty()) {
                break;
            }

            int choice = userInterface.getChoice(current.getOptions().size());
            DialogueOption chosen = current.getOptions().get(choice - 1);
            current = chosen.getNextNode();
        }

        userInterface.printMessage("You step away from the terminal.");
    }

        public void startGame() {
            map.buildMap();
            map.buildItems();
            map.buildDialogue();
            player = new Player(map.getStartRoom());
            gameRunning = true;

            userInterface.printHighlightedMessage("You are in " + player.getCurrentRoom().getName());
            userInterface.printMessage(player.getCurrentRoom().getDescription());

            // Tilføj if-statement her, som kun printer items ud, hvis der er nogle.
            userInterface.printItemList(player.getCurrentRoom().getItems());


            player.getCurrentRoom().setBeenInRoomBefore();

            while(gameRunning) {
                String userInput = userInterface.getInput();
                // Et String array oprettes, som splitter inputtet ved mellemrummet.
                String[] parts = userInput.split(" ", 2);
                String command = parts[0].toLowerCase();
                String argument = parts.length > 1 ? parts[1].trim() : "";

                if(userInput.equalsIgnoreCase("help"))
                {
                    userInterface.showHelp();
                }
                else if(userInput.equalsIgnoreCase("look")){
                    userInterface.printHighlightedMessage("You are in " + player.getCurrentRoom().getName());
                    userInterface.printMessage(player.look());
                    userInterface.printItemList(player.getCurrentRoom().getItems());
                }
                else if (userInput.equalsIgnoreCase("exit")){
                    userInterface.printMessage("Really? Boring!");
                    userInterface.closeScanner();
                    gameRunning = false;
                }

                // Take metoden skal kunne søge efter et item i en ArrayList
                // Er den der, skal den kalde takeItem() metoden
                else if (command.equalsIgnoreCase("take")) {
                    if(argument.isEmpty()) {
                        userInterface.printMessage("Take what?");
                    }
                    else if (argument.equalsIgnoreCase("all"))
                    {
                        ArrayList<Item> taken = player.takeAllItems();
                        if (taken.isEmpty())
                        {
                            userInterface.printMessage("There is nothing to take here.");
                        }
                        else
                        {
                            for (Item item : taken)
                            {
                                userInterface.printMessage("You took the " + item.getShortName() + ".");
                            }
                        }
                    }
                    else if (player.takeItem(argument)) {
                        userInterface.printMessage("You took the " + argument + ".");
                    }
                    // Take all options
                    else {
                        userInterface.printMessage("There is no " + argument + " here.");
                    }
                }

                else if (command.equalsIgnoreCase("Drop")) {
                    if(argument.isEmpty()) {
                        userInterface.printMessage("Drop what?");
                    }
                    else if (player.dropItem(argument)) {
                        userInterface.printMessage("You dropped the " + argument + ".");
                    }
                    else {
                        userInterface.printMessage("There is no " + argument + " here.");
                    }
                }

                else if (userInput.equalsIgnoreCase("Health"))
                {
                    if(player.getHealth() >= 100)
                    {
                        userInterface.printMessage("Health: " + player.getHealth() + ". You are in perfect health.");
                    }
                    else if(player.getHealth() >= 50 && player.getHealth() <= 99)
                    {
                        userInterface.printMessage("Health: " + player.getHealth() + ". You are in good health, but avoid fighting right now.");
                    }
                    else if(player.getHealth() >= 25 && player.getHealth() <= 49)
                    {
                        userInterface.printMessage("Health: " + player.getHealth() + ". You are wounded - find something healthy to eat");
                    }
                    else if(player.getHealth() >= 1 && player.getHealth() <= 24)
                    {
                        userInterface.printMessage("Health: " + player.getHealth() + ". You are barely alive");
                    }
                    else if(player.getHealth() <= 0)
                    {
                        userInterface.printMessage("Health: " + player.getHealth() + ". You should be dead.");
                    }
                }

                else if (command.equalsIgnoreCase("Eat")) {

                    if(argument.isEmpty()) {
                        userInterface.printMessage("Eat what?");
                    }
                    if (player.eat(argument) == EatResult.EATEN) {
                        userInterface.printMessage("You ate the " + argument + ".");
                    }
                    else if (player.eat(argument) == EatResult.NOT_FOOD)
                    {
                        userInterface.printMessage(("That " + argument + " can definitely not be eaten."));
                    }
                    else if (player.eat(argument) == EatResult.NOT_FOUND){
                        userInterface.printMessage("There is no " + argument + " to eat here.");
                    }
                }

                else if (command.equalsIgnoreCase("Equip")) {

                    if(argument.isEmpty()) {
                        userInterface.printMessage("Equip what?");
                    }
                    if (player.equip(argument) == WeaponResult.IS_WEAPON) {
                        userInterface.printMessage("You equipped the " + argument + ".");
                    }
                    else if (player.equip(argument) == WeaponResult.NOT_WEAPON)
                    {
                        userInterface.printMessage(("That " + argument + " can definitely not be equipped."));
                    }
                    else if (player.equip(argument) == WeaponResult.NOT_FOUND){
                        userInterface.printMessage("There is no " + argument + " to equip here.");
                    }
                }

                else if (command.equalsIgnoreCase("Attack")) {
                    Weapon weapon = player.getEquipped();
                    if (weapon == null)
                    {
                        userInterface.printMessage("You have no weapon equipped.");
                    }
                    else if (!weapon.canUse())
                    {
                        userInterface.printMessage("Out of ammo");
                    }
                    else
                    {
                        player.attack();
                        userInterface.printMessage(weapon.getAttackMessage());
                    }
                }

                else if (userInput.equalsIgnoreCase("inventory")) {
                    userInterface.printInventoryList(player.getInventory(), player.getEquipped());
                }

                else if (command.equals("terminal")){
                    DialogueNode dialogue = player.getCurrentRoom().getTerminalDialogue();

                    if (dialogue == null) {
                        userInterface.printMessage("There is nobody to talk to here.");
                    }
                    else {
                        runConversation(dialogue);
                    }
                }


                else {
                    String direction = userInterface.parseDirection(userInput);
                    Item item = player.findItem("key card");

                    if (item != null)
                    {
                        map.openRoomTwo();
                    }


                        if (direction != null) {
                            Room result = player.move(direction);

                            if (result != null) {
                                userInterface.printHighlightedMessage("You are in " + player.getCurrentRoom().getName());
                                userInterface.printMessage(result.getDescription());
                                userInterface.printItemList(player.getCurrentRoom().getItems());
                                result.setBeenInRoomBefore();
                            }
                            else {
                                userInterface.printMessage("You can't go that way.");
                            }
                        }

                        else {
                            userInterface.printMessage("You can't go that way.");
                        }
                    }
                }
            }
        }


