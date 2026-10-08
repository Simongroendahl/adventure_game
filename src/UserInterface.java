import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Audio audio;
    private Scanner scanner;
    private static int TYPEWRITER_DELAY_MS = 10;

    // Tekst styling
    public String highlightedText = "\u001b[1;48;2;255;169;0;30m";
    public String boldText = "\u001B[1m";
    public String cursiveText = "\u001B[3m";
    public String cursiveTextReset = "\u001B[23m";
    public String whiteText = "\033[0;97m";
    public String grayText = "\033[0;38;5;254;49m";
    public String boldTextReset = "\u001B[0m";
    public String resetColor = "\u001b[0m";
    public String clearScreen = "\033[2J\033[3J\033[H";

    public UserInterface (Audio audio) {
        this.audio = audio;
        scanner = new Scanner(System.in);
    }

    // Story text
    private String startDescription = whiteText + cursiveText + "The year is 3225. It has been a 112 years since you went into hibernation. You're onboard the deep space research vessel Hermes II. \nYour job is to maintain the spaceship during the long voyage, but there's one problem: You're not supposed to be awake right now.\n" + cursiveTextReset + boldTextReset;
    private String introInstructions = grayText + boldText + "[HOW TO PLAY]" + boldTextReset + whiteText + "\n> This is a text-based adventure.\n> Explore your surroundings, interact with the ship's systems, but most importantly - stay alive.\n> When presented with a choice, type the corresponding number or command.\n> Pay attention to what you see and read. The game won't always tell you what to do.\n> Type 'help' for a list of commands.\n" + boldTextReset;

    public void getIntroText() {
        /*printTypewriter(startDescription);*/
        printMessage(startDescription);
        printMessage(introInstructions);
    }

    public String getInput() {
        printMessageInline("> ");
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void clearScreen() {
        /*System.out.print(clearScreen);*/
        System.out.printf("\033[2J\033[H");
        System.out.flush();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessageInline(String message) {
        System.out.print(message);
    }

    public void printHighlightedMessage(String text) {
        System.out.println(highlightedText + " " + text + " " + resetColor);
    }

    public void printBoldTextOption(String textOption, String text) {
        System.out.println(boldText + textOption + boldTextReset + text);
    }

    public void printTypewriter(String text) {
        int i = 0;

        while (i < text.length()) {
            char c = text.charAt(i);

            if (c == '\u001B') {
                int end = text.indexOf('m', i);
                if (end == -1) {
                    end = text.length() - 1;
                }
                printMessage(text.substring(i, end + 1));
                i = end + 1;
            } else {
                System.out.print(c);
                /*printMessage(c);*/
                System.out.flush();
                sleep(c == '.' || c == ',' ? TYPEWRITER_DELAY_MS * 8 : TYPEWRITER_DELAY_MS);
                i++;
            }
        }
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {

        }
    }

    public void printEnemyList(ArrayList<Enemy> enemies) {
        if (enemies.isEmpty()) {
            return;
        }

        printMessageInline(boldText + "Beware! Here lurks: " + boldTextReset + whiteText);
        printMessageInline(enemies.getFirst().getShortName());
        for (int i = 1; i < enemies.size(); i++) {
            printMessageInline(", " + enemies.get(i).getShortName());
        }
        printMessage(whiteText + "");
        printMessage(enemies.getFirst().getDescription());
        printMessage("" + boldTextReset);
    }

    public void printItemList(ArrayList<Item> items) {
        if (items.isEmpty()) {
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
        if (inventory.isEmpty()) {
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
        if (equipped == null) {
            printMessage("nothing.");
        } else {
            printMessage(equipped.getDefiniteName());
        }
        printMessage("");
    }

    public void printDialogueNode(DialogueNode node) {
        printMessageInline(highlightedText + " " + node.getSpeaker() + " " + resetColor + " ");
        printMessage(node.getText());

        ArrayList<DialogueOption> options = node.getOptions();
        printMessage("");
        for (int i = 0; i < options.size(); i++) {
            printMessage("\033[0;97m> " + (i + 1) + ".\u001b[0m " + options.get(i).getText());
        }
    }

    public void printEatResult(EatResult result, String name) {
        switch (result) {
            case EATEN -> printMessage("You ate the " + name + ".");
            case NOT_FOOD -> printMessage("That " + name + " can definitely not be eaten.");
            case NOT_FOUND -> printMessage("There is no " + name + " to eat here.");
        }
    }

    public void printEquipResult(WeaponResult result, String name) {
        switch (result) {
            case IS_WEAPON -> printMessage("You equipped the " + name + ".");
            case NOT_WEAPON -> printMessage("That " + name + " is definitely not meant to be equipped.");
            case NOT_FOUND -> printMessage("There is no " + name + " to equip here.");
        }
    }

    // TODO: Jeg retter error handling i combat sekvens ved String input
    /*public int getChoice(int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= max) {
                    return choice;
                } else {
                    scanner.nextLine();
                }

                printMessage("Choose a number between 1 and " + max + ".");
            }
        }
    }*/

    public int getChoice(int max) {
        while (true) {
            String input = getInput();
            if(isNumber(input)) {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= max) {
                    return choice;
                }
            }
            printMessage("Choose a number between 1 and " + max + ".");
        }
    }

    private boolean isNumber(String text) {
        if(text.isEmpty() || text.length() > 3) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if(!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String parseDirection(String input) {
        String normalized = input.trim().toLowerCase();
        return switch (normalized) {
            case "north", "go north", "go n", "n" -> "north";
            case "south", "go south", "go s", "s" -> "south";
            case "east", "go east", "go e", "e" -> "east";
            case "west", "go west", "go w", "w" -> "west";
            default -> null;
        };
    }

    public void showHelp() {
        printMessage("");
        printHighlightedMessage("AVAILABLE COMMANDS:");
        printBoldTextOption("> DIRECTIONS: ", whiteText + "You can go north, east, west, or south. Type: 'go north', 'go n', 'north', or 'n'." + boldTextReset);
        printBoldTextOption("> LOOK: ", whiteText + "Type 'look' to get a description of the current room." + boldTextReset);
        printBoldTextOption("> TAKE: ", whiteText + "Type 'take [item name]' or 'take all' to take items." + boldTextReset);
        printBoldTextOption("> INVENTORY: ", whiteText + "Type 'inventory' to check your current items and equipped weapon." + boldTextReset);
        printBoldTextOption("> EQUIP: ", whiteText + "Type 'equip [item name]' to equip a weapon." + boldTextReset);
        printBoldTextOption("> HEALTH: ", whiteText + "Type 'health' to check your current health status." + boldTextReset);
        printBoldTextOption("> ATTACK: ", whiteText + "Type 'attack' to initiate combat." + boldTextReset);
        printBoldTextOption("> TERMINAL: ", whiteText + "Type 'terminal' to access any room's terminal." + boldTextReset);
        printBoldTextOption("> EXIT: ", whiteText + "Type 'exit' in order to close the program." + boldTextReset);
        printMessage("");
    }

    boolean runProgram = true;

    public void runProgram(Adventure adventure) {
        audio.startAmbientMainMenu();
        printHighlightedMessage("==== THE LAST VOYAGE OF HERMES II ====");
        getIntroText();
        printMessage(boldText + "Choose:" + boldTextReset);
        printMessage("> Start Game");
        printMessage("> Help");
        printMessage("> Exit");

        while (runProgram) {
            String userInput = getInput();

            if (userInput.trim().equalsIgnoreCase("Start game") || (userInput.trim().equalsIgnoreCase("Start"))) {
                audio.stopAmbientMainMenu();
                clearScreen();
                adventure.startGame();
                runProgram = false;
            } else if (userInput.trim().equalsIgnoreCase("Help")) {
                showHelp();
            } else if (userInput.trim().equalsIgnoreCase("Exit")) {
                printMessage("Really? Boring!");
                runProgram = false;
            } else {
                printMessage("Wrong input. Try again.");
            }
        }
    }
}
