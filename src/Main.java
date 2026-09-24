public class Main {
    public static void main(String[] args) {

        Boolean runProgram = true;
        Adventure adventure = new Adventure(Adventure.room1);
        UserInterface userInterface = new UserInterface();
        Adventure.setRooms();
        Room currentRoom = Adventure.room1;


        String startDescription = "You awake in a cold dark room. You look around. You're all alone. You don't know where you are, you don't remember who you are. You have a pounding headache, and feel dizzy.";
        String NavigationErrorMessage = "You cannot go that way.";
        System.out.println("You are in " + currentRoom.getName());
        System.out.println(startDescription);
        System.out.println(currentRoom.getDescription());

        Adventure.room1.setBeenInRoomBefore();

        while(runProgram) {
            String userInput = userInterface.getInput();

            // NAVIGATION
            if (userInput.equalsIgnoreCase("Go north")) {
                Room next = currentRoom.getNorth();
                if(next != null) {
                    currentRoom = next;
                    System.out.println(currentRoom.getDescription());
                    currentRoom.setBeenInRoomBefore();
                }
                else {
                    System.out.println(NavigationErrorMessage);
                }
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


            else if(userInput.equalsIgnoreCase("Exit")){
                System.out.println("You have exited the room");
                runProgram = false;
            }

            else if (userInput.equalsIgnoreCase("Help")) {
                System.out.println("List of commands:");
                System.out.println("1. Look (get the description of the room)");
                System.out.println("2. Go north/east/west/south (enter a new room)");

            }
            else if (userInput.equalsIgnoreCase("Look")) {
                System.out.println(adventure.room1.getDescription());
            }
            else {
                System.out.println("not allowed");
            }

        }

        userInterface.close();
    }
}

