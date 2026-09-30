public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        Adventure adventure = new Adventure(userInterface);
        userInterface.runProgram(adventure);
    }
}


// "start" skal kunne starte spille
// "Take all" skal kunne tage alt loot
// eat command?
// Look: skal også vise items i rummet igen