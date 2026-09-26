import java.util.Scanner;

public class UserInterface {

    private static Scanner scanner;


    public UserInterface(){
      scanner = new Scanner(System.in);

    }

    public  String getInput(){
        return scanner.nextLine();
    }

    public void close(){
        scanner.close();
     }

     public static void showHelp() {
         System.out.println("Overview of commands:");
         System.out.println("1. Directions: You can go north, east, west, or south. Type in - Go North, for instance.");
         System.out.println("2. Look: Type Look, in order to get a description of the current room again.");
         System.out.println("3. Exit: Type Exit in order to close the program");
     }

     static boolean runProgram = true;

     public void runProgram() {
         System.out.println("1. Start Game");
         System.out.println("2. Help");
         System.out.println("3. Exit");



             }
         }


