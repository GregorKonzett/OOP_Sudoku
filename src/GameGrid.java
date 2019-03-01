
public class GameGrid {
	public static final int GRID_DIM = 9;
	public static final int SUBGRID_DIM = GRID_DIM / 3;
	public static final int MAX_VAL = 9;
	public static final int MIN_VAL = 1;
	public static final int EMPTY_VAL = 0;
	
	private final Field[][] grid;
	
	public GameGrid(int[][] grid) {
		this.grid = initialiseGrid(grid);
	}
	
	public GameGrid(String path) {
		this.grid = initialiseGrid(IOUtils.loadFromFile(path));
	}
	
	public GameGrid(GameGrid game) {
		Field[][] tmpGrid = new Field[GRID_DIM][GRID_DIM];
		
		for(int i = 0;i<GRID_DIM;i++) {
			for(int j = 0;j<GRID_DIM;j++) {
				tmpGrid[i][j] = new Field(game.getField(i, j),game.isInitial(i, j));
			}
		}
			
		this.grid = tmpGrid;
	}

	private Field[][] initialiseGrid(int[][] vals) {
		Field[][] grid = new Field[vals.length][vals[0].length];
		
		for(int i = 0;i<vals.length;i++) {
			for(int j = 0;j<vals[0].length;j++) {
				boolean isInitial=false;
				if(vals[i][j]>0) isInitial=true;
				grid[i][j] = new Field(vals[i][j],isInitial);
				
				
			}
		}
		
		return grid;
	}
	
	public boolean isInitial(int col,int row) {
		if(col < 0 || col > grid.length || row < 0 || row > grid[0].length) {
			throw new IllegalArgumentException("Outside bounds");
		}
		
		return grid[col][row].isInitial();
	}
	
	public int getField(int column, int row) {
		return grid[column][row].getValue();
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
	
	private boolean checkRow(int col, int row, int num) {
		
		for(int x = 0;x < GRID_DIM;x++) {
			if(grid[x][row].getValue() == num) 
			{
				//System.out.println(x+" "+row+" "+num+" is the other val row invalid");
				return false;
			}
		}
		
		return true;
	}

	private boolean checkColumn(int col, int row, int num) {
		for(int y = 0;y < GRID_DIM;y++) {
			if(grid[col][y].getValue() == num) 
			{
				//System.out.println(col+" "+y+" "+num+" col invalid");
				return false;
			}
		}
		
		return true;
	}	
	
	private boolean checkSubGrid(int col, int row, int num) {
		int x = col%3;
		int y = row%3;
		
		int valX = col + (3-x);
		int valY = row + (3-y);
		
		for(int z=col-x;z<valX;z++) {
			for(int u = row-y;u<valY;u++) {	
				if(z!=col && u!=row && grid[z][u].getValue()==num) {
					//System.out.println(z+" "+u+" "+num+" square invalid");
					return false;
				}
			}
		}
	
		return true;
	}

	private boolean isValid(int i, int j, int num) {
		return checkRow(i,j,num) && checkColumn(i,j,num) && checkSubGrid(i,j,num);
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
