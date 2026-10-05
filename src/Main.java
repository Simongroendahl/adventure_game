public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        Adventure adventure = new Adventure(userInterface);
        userInterface.runProgram(adventure);
    }
}

// TO-DO LISTE

// Tilføj "Equipped: " + equipped.weapon, når man skriver inventory
    // Lav getCurrentWeapon metode
// Overvej at lave Food om til en abstrakt klasse
    // Lav to nye subklasser, drink og food-something
// Få EatOutcome klassen kædet til koden
// Lav flere items

// LÅSE SYSTEM
// En boolean?
// Lås første dør i rum 1, skal kun åbne, hvis Player har key card.
// Lås rum 5, skal bruge password (Enter password: )

// Room_6 skal også låses efter 3 besøg

// NICE TO-DO LISTE

// Lav metode der kan låse bestemte døre i rum.
// Tilføj de flere kommandoer i "Help"
    // "Give hint" - "Guide": skal give hints til nuværende puzzle
    // Lav counter til holder øje med antal hints du bruger
    // Skal der være en negativ effekt ved det?
