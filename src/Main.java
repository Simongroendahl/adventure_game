public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        Adventure adventure = new Adventure(userInterface);
        userInterface.runProgram(adventure);
    }
}


