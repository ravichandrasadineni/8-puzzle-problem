package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Board {
	int tiles[][];
	Board parentBoard = null;

	ArrayList<Board> neighbours;

	// construct a board from an N-by-N array of tiles	
	public Board(int[][] tiles)  {
		this.tiles = tiles;
	}

	//return number of blocks out of place
	public int hamming()    {
		int hammingDistance = 0;
		int columnLength  = tiles[0].length;
		for(int i =0; i < tiles.length; i ++)
		{
			for(int j =0; j < tiles[i].length; j++) 
			{
				int expectedNumber = columnLength*i + 1;
				if((tiles[i][j] != expectedNumber) && (tiles[i][j] != 0) ) {
					hammingDistance += 1;
				}
			}
		}
		return hammingDistance;

	}
	//return sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int manhattanDistance =0;
		int columnLength  = tiles[0].length;
		for(int i =0; i < tiles.length; i ++)
		{
			for(int j =0; j < tiles[i].length; j++) {
				if(tiles[i][j] != 0) {
					int displacement =  columnLength*i  + j + 1 - tiles[i][j];
					int rowDistance = displacement/columnLength;
					int columnDistance = displacement%columnLength;
					manhattanDistance = rowDistance + columnDistance;  
				}
			}
		}
		return manhattanDistance;
	}

	//does this board position equal y
	public boolean equals(Board newBoard) 
	{
		int[][] tilesToBeCompared = newBoard.getTiles();
		for(int i =0; i < tiles.length; i ++)
		{
			for(int j =0; j < tiles[i].length; j++) {
				if (tiles[i][j] != tilesToBeCompared[i][j] ) {
					return false;
				}
			}
		}

		return true;
	}

	// return an Iterator of all neighboring board positions	   
	public Iterator<Board> neighbors() {
		neighbours = new ArrayList<Board>();
		Position blankPosition = getBlankPosition();
		int rowLength = tiles.length;
		int columnsLength = tiles[0].length;
		if(blankPosition.getColumn() < columnsLength - 1) {
			int[][] newTiles = copy();
			newTiles[blankPosition.getRow()][blankPosition.getColumn()]=  tiles[blankPosition.getRow()][blankPosition.getColumn() + 1];
			newTiles[blankPosition.getRow()][blankPosition.getColumn() + 1] = 0;
			Board newBoard = new Board(newTiles);
			newBoard.setParentBoard(this);
			neighbours.add(newBoard);
		}

		if(blankPosition.getColumn() > 0) {
			int[][] newTiles = copy();
			newTiles[blankPosition.getRow()][blankPosition.getColumn()]=  tiles[blankPosition.getRow()][blankPosition.getColumn() - 1];
			newTiles[blankPosition.getRow()][blankPosition.getColumn() - 1] = 0;

			Board newBoard = new Board(newTiles);
			newBoard.setParentBoard(this);
			neighbours.add(newBoard);
		}

		if(blankPosition.getRow() > 0) {
			int[][] newTiles = copy();
			newTiles[blankPosition.getRow()][blankPosition.getColumn()]=  tiles[blankPosition.getRow() -1][blankPosition.getColumn()];
			newTiles[blankPosition.getRow() -1][blankPosition.getColumn()] = 0;
			Board newBoard = new Board(newTiles);
			newBoard.setParentBoard(this);
			neighbours.add(newBoard);
		}


		if(blankPosition.getRow() < rowLength - 1) {
			int[][] newTiles = copy();
			newTiles[blankPosition.getRow()][blankPosition.getColumn()]=  tiles[blankPosition.getRow() + 1][blankPosition.getColumn()];
			newTiles[blankPosition.getRow() + 1][blankPosition.getColumn()] = 0;
			Board newBoard = new Board(newTiles);
			newBoard.setParentBoard(this);
			neighbours.add(newBoard);
		}
		return neighbours.iterator();
	}


	// return a string representation of the board
	public String toString() {
		String stringRepresentation  = "\n";
		for(int i =0; i < tiles.length; i ++)
		{
			for(int j =0; j < tiles[i].length; j++) {
				if(tiles[i][j] != 0) {
					stringRepresentation  += tiles[i][j] + "\t";
				}
				else
					stringRepresentation  +=  "\t";
			}
			stringRepresentation   += "\n";
		}
		return stringRepresentation  ;
	}



	public int[][] getTiles() {
		return tiles;
	}

	public void setTiles(int[][] tiles) {
		this.tiles = tiles;
	}

	private Position getBlankPosition () {
		for(int i =0; i < tiles.length; i ++)
		{
			for(int j =0; j < tiles[i].length; j++) {

				if( tiles[i][j]== 0) {
					Position position = new Position(i,j);
					return position;
				}
			}

		}
		return null;
	}


	int[][] copy() {
		int newTiles[][] = new int[tiles.length][tiles[0].length]; 
		for (int i = 0; i< tiles.length; i ++) {
			newTiles[i]= Arrays.copyOf(tiles[i], tiles[i].length);
		}
		return newTiles;
	}

	public Board getParentBoard() {
		return parentBoard;
	}

	public void setParentBoard(Board parentBoard) {
		this.parentBoard = parentBoard;
	}



}

