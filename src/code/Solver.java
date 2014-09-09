package code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Solver {
	// find a solution to the initial board
	ArrayList<Board> boardsVisited = new ArrayList<Board>();
	ArrayList<Board> goalBoard;
	Board finalBoard;
	BoardProirityQueue boardProirityQueue;
	ArrayList<Board> solutionPath;
	public Solver(Board initial) {

		boardProirityQueue = new BoardProirityQueue();
		boardProirityQueue.add(initial);
		populateGoalBoard();
		solve();

	}
	private void solve () {
		Iterator<Board> boardIterator = boardProirityQueue.getIterator();
		while(boardIterator.hasNext()) {
			Board currentBoard = boardIterator.next();
			boardIterator.remove();
			if(isFinalBoard(currentBoard))
			{
				finalBoard= currentBoard;
				populateFinalSolution();
				return;
			}
			if(isAlreadyVisited(currentBoard)) {
				continue;
			}
			Iterator<Board> neighbors =currentBoard.neighbors();  
			while(neighbors.hasNext())
			{
				boardProirityQueue.add(neighbors.next());
			}
			addToAlreadyVisitedNodes(currentBoard);
			boardIterator = boardProirityQueue.getIterator();
		}
	}
	// return min number of moves to solve initial board; -1 if no solution
	public int moves()   {
		return solutionPath.size() -1;
	}
	// return an Iterable of board positions
	public Iterator<Board> solution() {
		return solutionPath.iterator();
	}

	private boolean isAlreadyVisited (Board currentBoard) {
		for (Board board : boardsVisited) {
			if(board.equals(currentBoard)) {
				return true;
			}
		}
		return false;
	}

	private void addToAlreadyVisitedNodes(Board currentBoard) {
		boardsVisited.add(currentBoard);
	}

	// is the initial board solvable?
	public boolean isSolvable()   {
		return true;
	}

	private void populateGoalBoard () 
	{
		int[][]finalBoard1Tiles = {{1,2,3} ,{4,5,6}, {7,8,0}};
		int[][]finalBoard2Tiles = {{1,2,3}, {8,0,4}, {7,6,5}};
		goalBoard = new ArrayList<Board>();
		goalBoard.add(new Board(finalBoard1Tiles));
		goalBoard.add(new Board(finalBoard2Tiles));

	}

	private void populateFinalSolution() {
		Stack<Board> finalSolutionStack = new Stack<Board>();
		solutionPath = new ArrayList<Board>();
		Board finalBoardPath = finalBoard;
		while(finalBoardPath != null) {
			finalSolutionStack.push(finalBoardPath);
			finalBoardPath = finalBoardPath.getParentBoard();
		}
		while(!finalSolutionStack.isEmpty()) {
			solutionPath.add(finalSolutionStack.pop());
		}
	}


	public boolean isFinalBoard(Board b) {
		for(Board solBoard : goalBoard) {
			if (solBoard.equals(b)) {
				return true;
			}
		}
		return false;
	}
}