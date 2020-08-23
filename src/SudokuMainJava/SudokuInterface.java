package SudokuMain;

public interface SudokuInterface {
	
	public boolean isValidMove(int row,int col);
	
	public boolean solve();
	
	public String toString();
}
