public class Adventure {

    // Vores variable
    private Player player;
    private UserInterface userInterface;
    private Map map;
    private Item item;
    private Room room;
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
            userInterface.printMessage("Here you see: " + player.getCurrentRoom().getItems());

            player.getCurrentRoom().setBeenInRoomBefore();

            while(gameRunning) {
                String userInput = userInterface.getInput();

                if(userInput.equalsIgnoreCase("help")){
                    userInterface.showHelp();
                }
                else if(userInput.equalsIgnoreCase("look")){
                    userInterface.printMessage(player.getCurrentRoom().getDescription());
                }
                else if (userInput.equalsIgnoreCase("exit")){
                    userInterface.printMessage("Really? Boring!");
                    userInterface.closeScanner();
                    gameRunning = false;
                }
                else if (userInput.equalsIgnoreCase("inventory")) {
                    player.getInventory();
                }


                else {
                    String direction = userInterface.parseDirection(userInput);

                        if (direction != null) {
                            Room result = player.move(direction);
                            if (result != null) {
                                userInterface.printHighlightedMessage("You are in " + player.getCurrentRoom().getName());
                                userInterface.printMessage(result.getDescription());
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


