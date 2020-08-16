package SudokuMain;

public class SudokuGrid implements SudokuInterface{
	private int[][] grid;
	private static final int SIZE = 9;
	
	// Default constructor
	public SudokuGrid(int[][] grid) {
		this.grid = grid;
	}
	
	/*
	 * This method solves the sudoku using the recursive backtracking algorithm.
	 * --- Details:
	 * First, the algorithm will go horizontally to find the blank space and fill
	 * it out by trying every number from 1 -> 9.
	 * 
	 * Recursive case: If it is a valid move, go to the next blank space.
	 * 
	 * Base case: If all blank spaces are filled out -> found solution
	 * 		Else, if after trying all numbers but none worked out -> no solution.
	 */
	@Override
	public boolean solve() {
		// We start from the first row and go to the next one in the recursive case.
		return solveHelper(0, 0);
	}
	
	private boolean solveHelper(int row, int col) {
		// Base case: because we count row from 0, if row+1 > size, it means
		// the algorithm has succeed in solving the sudoku board.
		if (row+1 > SIZE) {
			return true;
		} else if (col+1 > SIZE) {
			return solveHelper(row+1, 0);
		}
		else {
			if (grid[row][col] != 0) {
				return solveHelper(row, col+1);
			} else {
				// Choosing
				for (int n = 1; n <= 9; n++) {
					grid[row][col] = n;
					if (isValidMove(row, col)) {
						// Explore
						if (solveHelper(row, col+1) == true) {
							return true;
						}
					}
					// Unchoose
					grid[row][col] = 0;
				}
				return false;
			}
		}
	}

	/*
	 * A valid move means that number is unique horizontally and vertically.
	 * Return true if it is unique both horizontally and vertically, false otherwise.
	 * i.e: 1 3 2
	 * 		6 8 x
	 * 		3 1 5
	 */
	@Override
	public boolean isValidMove(int row, int col) {
		int checkNumb = grid[row][col];
		
		// Check for horizontally unique by comparing to every elements in that row.
		for (int c = 0; c < SIZE; c++) {
			if (c != col && grid[row][c] == checkNumb) {
				return false;
			}
		}
		// Check for vertically unique by comparing to element [col] in every rows.
		for (int r = 0; r < SIZE; r++) {
			if (r != row && grid[r][col] != 0 && grid[r][col] == checkNumb) {
				return false;
			}
		}
		// The parameter is unique both horizontally and vertically in the sudoku grid.
		return true;
	}
	
	/*
	 * Return a string represents the sudoku grid.
	 */
	@Override
	public String toString() {
		String returnStr = new String();
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (grid[row][col] == 0) {
					returnStr += "_ ";
				} else {
					returnStr += grid[row][col]+" ";
				}
				if ((col+1) % 3 == 0) {
					returnStr += "| ";
				}
			}
			if ((row+1) % 3 == 0) {
				returnStr += "\n";
			}
			returnStr += "\n";
		}
		return returnStr;
	}

}
