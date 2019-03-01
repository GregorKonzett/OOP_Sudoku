import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Objects;

public class Sudoku01 {

    /**
     * Print a game menu message to the console.
     */
    public static void printMenu() {
        System.out.print("\n" +
        			"1. Set field\n" +
        			"2. Clear field\n" +
                "3. Print game\n" +
        		"4. Solve\n\n" +
                "5. Exit\n\n" +
                "Select an action [1-5]: ");
    }   

    /**
     * Read a single integer value from the console and return it.
     * This function blocks the program's execution until a user
     * entered a value into the command line and confirmed by pressing
     * the Enter key.
     * @return The user's input as integer or -1 if the user's input was invalid.
     */
    public static int parseInput() {
        Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (InputMismatchException missE) {
            in.next(); // discard invalid input
            return -1;
        }
    }   

    /**
     * Display a dialog requesting a single integer which is returned
     * upon completion.
     *
     * The dialog is repeated in an endless loop if the given input 
     * is not an integer or not within min and max bounds.
     *
     * @param msg: a name for the requested data.
     * @param min: minium accepted integer.
     * @param max: maximum accepted integer.
     * @return The user's input as integer.
     */
    public static int requestInt(String msg, int min, int max) {
        Objects.requireNonNull(msg);

        while(true) {
            System.out.print("Please provide " + msg + ": ");
            int input = parseInput();
            if (input >= min && input <= max) return input;
            else {
                System.out.println("Invalid input. Must be between " + min + " and " + max);
            }
        }
    }

    // TODO write a static function printGrid here 

    public static void main(String[] args) {
        int[][] grid = {
            {9,4,0,1,0,2,0,5,8},
            {6,0,0,0,5,0,0,0,4},
            {0,0,2,4,0,3,1,0,0},
            {0,2,0,0,0,0,0,6,0},
            {5,0,8,0,2,0,4,0,1},
            {0,6,0,0,0,0,0,8,0},
            {0,0,1,6,0,8,7,0,0},
            {7,0,0,0,4,0,0,0,3},
            {4,3,0,5,0,9,0,1,2}
        };
        
        GameGrid game = new GameGrid("./games/sudoku1.sd");
  
        // TODO print the grid here
        System.out.println(game.toString());
        
        // TODO implement the game loop here
        while(true) {
        	
        		System.out.println("---Menu---");
        		printMenu();
        		
        		int input = parseInput();
        		
        		if(input==1) { //Set field
        			game.setField(requestInt("Enter the X coord", 0, grid[0].length-1),requestInt("Enter the Y coord", 0, grid.length-1),requestInt("Enter the value", 0, 9));
        			System.out.println(game.toString());
        		} else if(input == 2) { //Clear field
        			game.clearField(requestInt("Enter the X coord", 0, grid[0].length-1),requestInt("Enter the Y coord", 0, grid.length-1));
        			System.out.println(game.toString());
        		} else if(input == 3) { //Print game 
        			System.out.println(game.toString());
        		} else if(input == 4) {
        			GameGrid copiedGame = new GameGrid(game);
        			if(Solver.solve(copiedGame)) {
        				System.out.println(copiedGame.toString());
        			} else {
        				System.out.println("No solution found");
        			}
        		} else if(input == 5) {
        			break;
        		} else {
        			System.out.println("Wrong input");
        		}
        }

    }
}
