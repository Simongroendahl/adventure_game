import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    public String highlightedText = "\u001b[1;48;2;255;169;0;30m";
    public String boldText = "\u001B[1m";
    public String boldTextReset = "\u001B[0m";
    public String resetColor = "\u001b[0m";

    // Story text
    private String startDescription = "The year is 3225. It has been a 112 years since you went to sleep. You're onboard the deep space research vessel Hermes II. \nYour job is to maintain the spaceship during the long voyage. You're the only one supposed to be awake right now. But that is not the case.\n";

    public void getIntroText() {
        printMessage(startDescription);
    }

    public UserInterface(){
      scanner = new Scanner(System.in);
    }

    public String getInput(){
        return scanner.nextLine();
    }

    public void closeScanner(){
        scanner.close();
     }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessageInline(String message) {
        System.out.print(message);
    }

    public void printHighlightedMessage(String text){
        System.out.println(highlightedText + " " + text + " " + resetColor);
    }

    public void printBoldTextOption(String textOption, String text)
    {
        System.out.println(boldText + textOption + boldTextReset + text);
    }

    public void printEnemyList(ArrayList<Enemy> enemies)
    {
        if(enemies.isEmpty()) {
            return;
        }

        printMessageInline(boldText + "Beware! Here lurks: " + boldTextReset);
        printMessageInline(enemies.getFirst().getShortName());
        for (int i = 1; i < enemies.size(); i++) {
            printMessageInline(", " + enemies.get(i).getShortName());
        }
        printMessage("");
        printMessage(enemies.getFirst().getDescription());
        printMessage("");
    }

    public void printItemList(ArrayList<Item> items) {
        if(items.isEmpty()) {
            return;
        }
        printMessageInline(boldText + "Here you see: " + boldTextReset);
        printMessageInline(items.getFirst().getIndefiniteName());
        for (int i = 1; i < items.size(); i++) {
            printMessageInline(", " + items.get(i).getIndefiniteName());
        }
        printMessage("");
    }

    public void printInventoryList(ArrayList<Item> inventory, Weapon equipped) {
        if(inventory.isEmpty()) {
            printMessage("You have no items.");
            return;
        }
        printMessageInline(boldText + "INVENTORY: " + boldTextReset);
        printMessageInline(inventory.getFirst().getIndefiniteName());
        for (int i = 1; i < inventory.size(); i++) {
            printMessageInline(", and " + inventory.get(i).getIndefiniteName());
        }
        printMessage("");
        printMessageInline(boldText + "EQUIPPED: " + boldTextReset);
        if(equipped == null)
        {
            printMessage("nothing.");
        }
        else {
            printMessage(equipped.getDefiniteName());
        }
        printMessage("");
    }

    public void printDialogueNode(DialogueNode node)
    {
        printMessageInline(highlightedText + " " + node.getSpeaker() + " " + resetColor + " ");
        printMessage(node.getText());

        ArrayList<DialogueOption> options = node.getOptions();
        for (int i = 0; i < options.size(); i++) {
            printMessage("> " + (i + 1) + ". " + options.get(i).getText());
        }
    }

    public int getChoice(int max)
    {
        while(true) {
            if(scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= max) {
                    return choice;
                }
                else {
                    scanner.nextLine();
                }

                printMessage("Choose a number between 1 and " + max + ".");
            }
        }
    }

    /*public void printPlayerAttackMove(ArrayList<Enemy> enemies) {
        printMessage("You attack the " + enemies.get(0).getShortName());
    }*/

    public String parseDirection(String input) {
        String normalized = input.trim().toLowerCase();
        return switch (normalized) {
            case "north", "go north", "n" -> "north";
            case "south", "go south", "s" -> "south";
            case "east", "go east", "e"  -> "east";
            case "west", "go west", "w"  -> "west";
            default -> null;
        };
    }

     public void showHelp() {
         printMessage("");
         printHighlightedMessage("AVAILABLE COMMANDS:");
         printBoldTextOption("> Directions - ", "You can go north, east, west, or south. Type: Go north, north, or n.");
         printBoldTextOption("> Look - ", "Type: look - in order to get a description of the current room again.");
         printBoldTextOption("> Take - ", "Type: take [item name] or take all - to loot items.");
         printBoldTextOption("> Inventory - ", "Type: inventory - checks your current items and equipped weapon.");
         printBoldTextOption("> Equip - ", "Type: equip [item name] - to equip a weapon.");
         printBoldTextOption("> Health - ", "Type: health - checks your current health status");
         printBoldTextOption("> Attack - ", "Type: attack - attack the enemy");
         printBoldTextOption("> Access Terminal - ", "Type: terminal - to access any room's terminal.");
         printBoldTextOption("> Exit - ", "Type Exit in order to close the program");
     }

     boolean runProgram = true;

     public void runProgram(Adventure adventure) {
         printHighlightedMessage("OUR SPACE GAME");
         getIntroText();
         printMessage(boldText + "Choose:" + boldTextReset);
         printMessage("> Start Game");
         printMessage("> Help");
         printMessage("> Exit");

         while(runProgram) {
             String userInput = getInput();

             if (userInput.trim().equalsIgnoreCase("Start game") || (userInput.trim().equalsIgnoreCase("Start"))) {
                 adventure.startGame();
                 runProgram = false;
             }
             else if (userInput.trim().equalsIgnoreCase("Help")) {
                showHelp();
             }
             else if (userInput.trim().equalsIgnoreCase("Exit")){
                 printMessage("Really? Boring!");
                 runProgram = false;
             }
             else {
                 printMessage("Wrong input. Try again.");
             }
         }
     }
}
