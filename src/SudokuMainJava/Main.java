/*
 * Author: Hung Le Ba -UofA
 * Purpose: A small program helps to solve sudoku puzzle by using recursive
 * 	backtracking algorithm.
 * Last edit: August 12th 2020.
 */

package SudokuMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private final static int SIZE = 9;
	
	public static void main(String[] args) {
		SudokuGrid myBoard = new SudokuGrid(gridBuilder(args[0]));
		// Print initial board before solving
		System.out.println(myBoard.toString());
		
		// Try to solve the sudoku board.
		boolean solvable = myBoard.solve();
		if (solvable) {
			System.out.println("After solving: ---------");
			System.out.println(myBoard.toString());
		} else {
			System.out.println("Sorry this sudoku board is unsolvable");
		}
	}
	
	private static int[][] gridBuilder(String fileName) {
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Start reading grid
		int[][] grid = new int[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			String[] rowElements = fileReader.nextLine().split(",");
			for (int col = 0; col < SIZE; col++) {
				if (!rowElements[col].equals(" ")) {
					grid[row][col] = Integer.parseInt(rowElements[col]);
				}
			}
		}
		fileReader.close();
		return grid;
	}
}
