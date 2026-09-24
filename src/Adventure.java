

public class Adventure {

    public static void main(String[] args) {

        Boolean runProgram = true;

        String startDescription = "You awake in a cold dark room. You look around. You're all alone. You don't know where you are, you don't remember who you are. You have a pounding headache, and feel dizzy.";
        String NavigationErrorMessage = "You cannot go that way.";

        Room room1 = new Room("Room 1", "You are in a sleeping pod chamber. There are five more sleeping pods, but all of them are empty. There are two doors, one facing east, the other facing south.", "You're back in the sleeping pod chamber");
        Room room2 = new Room("Room 2", "You are in long, dark hallway. Red flickering lights shower the room. An alarm has gone off. You see a door at the end of the hallway. (go east / go west)", "You're back in the hallway.");
        Room room3 = new Room("Room 3", "Asdf", "Asdf rum 3");
        Room room4 = new Room("Room 4", "The room is bla bla bla. There's a window with a full view of the sleeping room. There are strange machines, with surveillance videos on the screens, temperature and heart rate monitoring.", "");
        Room room5 = new Room("Room 5", "Room 5 is in space", "Room 5 short description");
        Room room6 = new Room("Room 6", "Room 6 is in space", "Room 6 short description");
        Room room7 = new Room("Room 7", "Room 7 is in space", "Room 7 short description");
        Room room8 = new Room("Room 8", "Room 8 is in space", "Room 8 short description");
        Room room9 = new Room("Room 9", "Room 9 is in space", "Room 9 short description");

        // Tilføj boolean der tjekker om vi har været i rummet før eller ej.
        // Lav if statements eller while loop, der tjekker for det.

        room1.setEast(room2);
        room1.setSouth(room4);

        room2.setWest(room1);
        room2.setEast(room3);

        room3.setWest(room2);
        room3.setSouth(room6);

        room4.setNorth(room1);
        room4.setSouth(room7);

        room5.setSouth(room8);

        room6.setNorth(room3);
        room6.setSouth(room9);

        room7.setNorth(room4);
        room7.setEast(room8);

        room8.setWest(room7);
        room8.setNorth(room5);
        room8.setEast(room9);

        room9.setWest(room8);
        room9.setNorth(room6);

        Room currentRoom = room1;
        UserInterface userInterface = new UserInterface();

        System.out.println("You are in " + currentRoom.getName());
        System.out.println(startDescription);
        System.out.println(currentRoom.getDescription());
        room1.setBeenInRoomBefore();
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
                System.out.println(room1.getDescription());
            }
              else {
                System.out.println("not allowed");
            }

        }

        userInterface.close();
        }


    }

