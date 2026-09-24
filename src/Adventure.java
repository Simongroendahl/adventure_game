import java.sql.SQLOutput;

public class Adventure {

    // Vores variable
    private Player player;
    private Map map;

    public boolean startGame;

    // Konstruktør
    /*public Adventure(Room currentRoom) {
        this.currentRoom = currentRoom;
    }*/

        public static void startGame() {
            UserInterface userInterface = new UserInterface();
            boolean startGame = true;
            Map.buildMap();

            Room currentRoom = Map.room1;


            String startDescription = "You awake in a cold dark room. You look around. You're all alone. You don't know where you are, you don't remember who you are. You have a pounding headache, and feel dizzy.";
            String NavigationErrorMessage = "You cannot go that way.";
            System.out.println("You are in " + currentRoom.getName());
            System.out.println(startDescription);
            System.out.println(currentRoom.getDescription());

            Map.room1.setBeenInRoomBefore();

            while(startGame) {
                String userInput = userInterface.getInput();
                Player.move(userInput);


                // NAVIGATION
                /*if (userInput.equalsIgnoreCase("Go north")) {
                    *//*Room nextRoom = currentRoom.getNorth();
                    if(nextRoom != null) {
                        currentRoom = nextRoom;
                        System.out.println(currentRoom.getDescription());
                        currentRoom.setBeenInRoomBefore();
                    }
                    else {
                        System.out.println(NavigationErrorMessage);
                    }*//*
                    Player.move("west");
                }
                else if (userInput.equalsIgnoreCase("Go east")) {
                    Room next = currentRoom.getEast();
                    if(next != null) {
                        currentRoom = next;
                        System.out.println(currentRoom.getDescription());
                        currentRoom.setBeenInRoomBefore();
                    }
                    else {
                        System.out.println(NavigationErrorMessage);
                    }
                }

                else if (userInput.equalsIgnoreCase("Go west")) {
                    Room next = currentRoom.getWest();
                    if(next != null) {
                        currentRoom = next;
                        System.out.println(currentRoom.getDescription());
                        currentRoom.setBeenInRoomBefore();
                    }
                    else {
                        System.out.println(NavigationErrorMessage);
                    }
                }

                else if (userInput.equalsIgnoreCase("Go south")) {
                    Room next =  currentRoom.getSouth();
                    if (next != null) {
                        currentRoom = next;
                        System.out.println(currentRoom.getDescription());
                        currentRoom.setBeenInRoomBefore();
                    }

                }
                else if (userInput.equalsIgnoreCase("Help")) {
                    UserInterface.showHelp();
                }

                else if (userInput.equalsIgnoreCase("Exit")) {
                    System.out.println("Really? Boring!");
                    startGame = false;
                }*/
            }
        }
}

