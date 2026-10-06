public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        Adventure adventure = new Adventure(userInterface);
        userInterface.runProgram(adventure);
    }
}

// TODO LISTE

// Room_6 skal også låses efter 3 besøg

// NICE TO-DO LISTE
// Tilføj de flere kommandoer i "Help"
    // "Give hint" - "Guide": skal give hints til nuværende puzzle
    // Lav counter til holder øje med antal hints du bruger
    // Skal der være en negativ effekt ved det?
