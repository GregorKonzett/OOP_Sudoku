import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SudokuFrame extends JFrame{
	private String path;
	private final GameGrid grid;
	
	public SudokuFrame(String str) {
		path = str;
		this.setSize(500, 500);
		this.setTitle(str);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		grid = new RGameGrid(str);
		
		GridLayout layout = new GridLayout(9, 9);
		
		this.setLayout(layout);
		
		createButtonGrid();
		
		this.setVisible(true);
	}
	
	private void createButtonGrid() {
		
		for(int i = 0;i<GameGrid.GRID_DIM;i++) {
			for(int j = 0;j<GameGrid.GRID_DIM;j++) {
				int field = grid.getField(i, j);
				String str ="";
				if(field>0) str=field+"";
				
				JButton button = new JButton(str);
				button.addActionListener(new SudokuFieldAction(i, j, grid));
				this.add(button);
			}
		}
	}
}
