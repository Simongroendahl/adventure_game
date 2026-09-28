import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    public String highlightedText = "\u001b[1;48;2;255;169;0;30m";
    public String resetColor = "\u001b[0m";

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

    public void printHighlightedMessage(String text){
        System.out.println(highlightedText + " " + text + " " + resetColor);
    }

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
         System.out.println("Overview of commands:");
         System.out.println("1. Directions: You can go north, east, west, or south. Type in - Go North, for instance.");
         System.out.println("2. Look: Type Look, in order to get a description of the current room again.");
         System.out.println("3. Exit: Type Exit in order to close the program");
     }

     boolean runProgram = true;

     public void runProgram(Adventure adventure) {
         printHighlightedMessage("OUR SPACE GAME");
         System.out.println("1. Start Game");
         System.out.println("2. Help");
         System.out.println("3. Exit");

         while(runProgram) {
             String userInput = getInput();

             if (userInput.equalsIgnoreCase("Start game")) {
                 adventure.startGame();
                 runProgram = false;
             }
             else if (userInput.equalsIgnoreCase("Help")) {
                showHelp();
             }
             else if (userInput.equalsIgnoreCase("Exit")){
                 System.out.println("Really? Boring!");
                 runProgram = false;
             }
             else {
                 System.out.println("Wrong input. Try again.");
             }
         }
     }
}
