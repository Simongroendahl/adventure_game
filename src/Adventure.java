import java.sql.SQLOutput;

public class Adventure {

    // Vores variable
    private Player player;
    private Map map;
    private UserInterface userInterface;
    public Adventure() {
        map = new Map();
        player = new Player(map.getStartRoom());
        userInterface = new UserInterface();
    }

        public void startGame() {
            boolean runProgram = true;
            String startDescription = "You awake in a cold dark room. You look around. You're all alone. You don't know where you are, you don't remember who you are. You have a pounding headache, and feel dizzy.";
            String NavigationErrorMessage = "You cannot go that way.";
            System.out.println("You are in " + player.getCurrentRoom().getName());
            System.out.println(startDescription);
            System.out.println(player.getCurrentRoom().getDescription());

            player.getCurrentRoom().setBeenInRoomBefore();

            while (runProgram) {
                String userInput = userInterface.getInput();


                // NAVIGATION
                if (userInput.equalsIgnoreCase("Go north")) {

                  player.move("north");
                } else {
                    System.out.println(NavigationErrorMessage);
                }

                if  (userInput.equalsIgnoreCase("Go east")) {
                    player.move("east");
                } else {
                    System.out.println(NavigationErrorMessage);
                }


                 if (userInput.equalsIgnoreCase("Go west")) {
                    player.move("west");
                } else {
                    System.out.println(NavigationErrorMessage);
                }


                if (userInput.equalsIgnoreCase("Go south")) {
                    player.move("south");
                } else {
                    System.out.println(NavigationErrorMessage);
                }

                if (userInput.equalsIgnoreCase("Help")) {
                    UserInterface.showHelp();
                } else if (userInput.equalsIgnoreCase("Exit")) {
                    System.out.println("Really? Boring!");
                    runProgram = false;
                }
            }
        }

    }
