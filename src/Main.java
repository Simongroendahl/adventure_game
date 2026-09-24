public class Main {
    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface();
        Adventure adventure = new Adventure(Adventure.room1);

        userInterface.runProgram();
        /*adventure.startGame();*/
    }
}

            /*else if(userInput.equalsIgnoreCase("Exit")){
                System.out.println("You have exited the room");
                runProgram = false;
            }

            else if (userInput.equalsIgnoreCase("Help")) {
                System.out.println("List of commands:");
                System.out.println("1. Look (get the description of the room)");
                System.out.println("2. Go north/east/west/south (enter a new room)");

            }
            else if (userInput.equalsIgnoreCase("Look")) {
                System.out.println(currentRoom.getDescription());
            }
            else {
                System.out.println("not allowed");
            }

        }

        userInterface.close();
    }*/


