import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface(){
      scanner = new Scanner(System.in);
    }

    public String getInput(){
        return scanner.nextLine();
    }

    public void close(){
        scanner.close();
     }
}
