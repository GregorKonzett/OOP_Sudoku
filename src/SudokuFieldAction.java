import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SudokuFieldAction implements ActionListener {

	private final int row;
	private final int col;
	private final GameGrid grid;
	
	public SudokuFieldAction(int col, int row, GameGrid grid) {
		this.row=row;
		this.col=col;
		this.grid=grid;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton button = (JButton)e.getSource();
			
			String num = JOptionPane.showInputDialog("Enter your number");
			
			try {
				int num1 = Integer.parseInt(num);
				
				if(num1<0 || num1>9) return;
				
				if(grid.setField(col, row, num1)) {
					button.setText(num);
				}
				
				
			} catch(Exception exc) {
				exc.printStackTrace();
			}		
		}
		
		
	}

}
