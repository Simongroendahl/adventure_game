public class Main {
    public static void main(String[] args) {
        Audio audio = new Audio();
        UserInterface userInterface = new UserInterface();
        Adventure adventure = new Adventure(userInterface, audio);
        userInterface.runProgram(adventure);
    }
}