import java.io.File;
import java.nio.file.Files;

import javax.swing.JOptionPane;

public class Sudoku7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String path = JOptionPane.showInputDialog("Enter the path");
		
		if(path == null || path.equals("")) {
			System.out.println("Path is null");
		} else {
			File file = new File(path);
			if(!file.exists()) {
				System.out.println("File does not exist");
			} else {
				SudokuFrame frame = new SudokuFrame(path);
			}
		}
	}

}
