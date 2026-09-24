public class Main {
    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface();
        Adventure adventure = new Adventure(Map.room1);
        userInterface.runProgram();
    }
}


