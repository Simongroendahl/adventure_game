public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        Audio audio = new Audio();
        Adventure adventure = new Adventure(userInterface, audio);
        userInterface.runProgram(adventure);
    }
}