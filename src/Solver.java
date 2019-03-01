
public class Solver {
	
	static int[][] tries = new int[GameGrid.GRID_DIM*GameGrid.GRID_DIM-2][10];
	static int firstNonInitial=0;
	private static GameGrid game;
	
	public static boolean solve(GameGrid passedGame) {
		game = passedGame;
				
		//Initialise the tries array
		ininitialiseTries();
		
		//Find first position of non initial field
		findFirstNonInitial();
			
		//Actual Solver loop
		int pos = 0;
		int col = 0;
		int row = 0;
		while(pos<(GameGrid.GRID_DIM*GameGrid.GRID_DIM-2)) {		
			col = pos/9;
			row = pos%9;
		
			if(!game.isInitial(col, row)) {	
				if(triedEverything(pos)) {	
					//If no valid number in first non initial field, then it is unsolvable
					if(pos==firstNonInitial) {
						return false;
					}
					
					resetArray(pos+1);
					pos = backtrack(pos);	
					
					continue;
				} else if(!chooseValidNumber(col, row,pos)) {
				
					//If no valid number in first non initial field, then it is unsolvable
					if(pos==firstNonInitial) {
						return false;
					}
					
					resetArray(pos);
					
					game.clearField(col, row);
					
					//Skip initial values
					pos = backtrack(pos);				
						
					continue;
				}
			}
			
			pos++;
		}
		
		return true;
	}
	
	private static void ininitialiseTries() {
		for(int i = 0;i<(GameGrid.GRID_DIM*GameGrid.GRID_DIM-2);i++) {
			for(int j = 0;j<10;j++) {
				tries[i][j] = -1;
			}
		}
	}
	
	private static void findFirstNonInitial() {
		int i=0,j=0;
		boolean found=false;
		
		for(;i<GameGrid.GRID_DIM;i++) {
			for(;j<GameGrid.GRID_DIM;j++) {
				if(!game.isInitial(i, j)) {
					found=true;
					break;
				}
			}
			
			if(found) break;
		}
		
		firstNonInitial = (i*GameGrid.GRID_DIM)+j;
	}
	
	private static int backtrack(int pos) {
		do {
			if(pos==0) {
				break;
			}
			pos--;
		} while(game.isInitial(pos/9, pos%9));
		
		return pos;
	}

	private static boolean chooseValidNumber(int col, int row,int pos) {
		int val = game.getField(col, row);
		
		for(int i = 1;i<=9;i++) {
			
			if(val==i || triedAlready(i, pos)) continue;
			
			if(game.setField(col, row, i)) {
				tries[pos][game.getField(col, row)] = 1;
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean triedAlready(int val,int pos) {
		return tries[pos][val]!=-1?true:false;
	}
	
	private static void resetArray(int pos) {
		for(int i = 0;i<10;i++) {
			tries[pos][i]=-1;
		}
	}
	
	private static boolean triedEverything(int pos) {
		for(int i = 0;i<10;i++) {
			if(tries[pos][i]<0) return false;
		}
		
		return true;
	}
}
