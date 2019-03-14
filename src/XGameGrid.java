
public class XGameGrid extends RGameGrid {

	public XGameGrid(int[][] grid) {
		super(grid);
	}
	
	public XGameGrid(String path) {
		super(path);
	}
	
	public XGameGrid(RGameGrid game) {
		super(game);
	}
	
	private boolean isOnDiagonal(int col, int row) {
		 return (col == row || ((GRID_DIM-1)-col == row))? true:false;
	}
	
	private boolean checkDiagonal(int col, int row, int num) {
		if(!isOnDiagonal(col, row)) {
			return true;
		}
				
		for(int x = 0; x < GRID_DIM;x++) {
			for(int y = 0;y<GRID_DIM;y++) {
				if(x==col && y == row) {
					continue;
				}
				
				if(grid[x][y].getValue()==num) {
					return false;
				}
			}
		}
			
		return true;
	}
	
	@Override 
	protected boolean isValid(int col, int row, int num) {
		boolean result = super.isValid(col, row, num);
	
		return result&&checkDiagonal(col, row, num);
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
				
				if(j==i) {
					gridString+="["+grid[i][j]+"]";
				} else {
					gridString+=""+grid[i][j];
				}
			}			
		}
		
		return gridString;
	}
	
}
