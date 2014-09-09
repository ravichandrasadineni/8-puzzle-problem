package code;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class BoardProirityQueue {

	Queue<Board> boardPriorityQueue ;
	//Comparator anonymous class implementation
	public static Comparator<Board> boardComparator = new Comparator<Board>(){

		@Override
		public int compare(Board b1, Board b2) {
			return (int) (b1.hamming() - b2.hamming());
		}
	};

	BoardProirityQueue() {
		boardPriorityQueue = new PriorityBlockingQueue<Board>(100, boardComparator);
	}
	void add(Board board) {
		boardPriorityQueue.add(board);
	}
	Board getNextPriorityBoard() 
	{
		return boardPriorityQueue.poll();
	}
	Iterator<Board> getIterator() 
	{
		return boardPriorityQueue.iterator();
	}

}
