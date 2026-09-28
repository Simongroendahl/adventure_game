public class Adventure {

    // Vores variable
    private Player player;
    private UserInterface userInterface;
    private Map map;
    private boolean gameRunning;

    String startDescription = "You awake in a cold dark room. You look around. You're all alone. \nYou don't know where you are, you don't remember who you are. You have a pounding headache, and feel dizzy.";

    // Konstruktør
    public Adventure(UserInterface userInterface) {
        this.userInterface = userInterface;
        map = new Map();
    }

        public void startGame() {
            map.buildMap();
            map.buildItems();
            player = new Player(map.getStartRoom());
            gameRunning = true;

            userInterface.printHighlightedMessage("You are in " + player.getCurrentRoom().getName());
            userInterface.printMessage(startDescription);
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

                if(userInput.equalsIgnoreCase("help")){
                    userInterface.showHelp();
                }
                else if(userInput.equalsIgnoreCase("look")){
                    userInterface.printHighlightedMessage("You are in " + player.getCurrentRoom().getName());
                    userInterface.printMessage(player.look());
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
                    else if (player.takeItem(argument)) {
                        userInterface.printMessage("You took the " + argument + ".");
                    }
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

                else if (userInput.equalsIgnoreCase("inventory")) {
                    userInterface.printInventoryList(player.getInventory());
                }


                else {
                    String direction = userInterface.parseDirection(userInput);

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


