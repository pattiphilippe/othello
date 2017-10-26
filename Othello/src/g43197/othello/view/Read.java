package g43197.othello.view;

import g43197.othello.model.Coordinates;
import java.util.Scanner;

/**
 *
 * @author g43197
 */
public class Read {
    
    private static Scanner clavier = new Scanner(System.in);
    
    public static Coordinates readPos(){
        System.out.println("Enter the position where you wanna put a piece.");
        int row = readRow(), col = readCol();
    }
    
    private static int readRow(){
        boolean validInput = false;
        while(!validInput){
            
        }
    }
}
