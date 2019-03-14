
public class RGameGrid extends GameGrid{
	public RGameGrid(int[][] grid) {
		super(grid);
	}
	
	public RGameGrid(String path) {
		super(path);
	}
	
	public RGameGrid(GameGrid game) {
		super(game);
	}
		
	public boolean setField(int column, int row, int val) {
		
		if (!isValid(column, row, val) || isInitial(column,row)) {
			//System.out.println(column+" "+row+" "+val+" invalid");
			return false;
		}
		
		if(column < 0 || column > grid.length || row < 0 || row > grid[0].length) {
			throw new IllegalArgumentException("Outside bounds");
		}
		
		grid[column][row].setValue(val);
		
		return true;
	}
	
	@Override
	public String toString() {
		
		String gridString="";
		
		for(int i = 0;i<grid.length;i++) {
			if(i>0 && i%3==0) {
				gridString+="\n\n";
				//System.out.println("\n");
			} else if(i>0){
				gridString+="\n";
				//System.out.println("");
			}
			
			for(int j = 0;j<grid[i].length;j++) {
				if(j>0 && j%3==0) {
					gridString+=" ";
					//System.out.print(" ");
				}
				gridString+=""+grid[i][j];
				
			}			
		}
		
		return gridString;
    }
	
	public void clearField(int column,int row) {
		if(column < 0 || column > grid.length || row < 0 || row > grid[0].length) {
			throw new IllegalArgumentException("Outside bounds");
		}
		
		grid[column][row].setValue(0);
	}
}
