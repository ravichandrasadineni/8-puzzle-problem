package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Tester {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	try {	
		int N =   Integer.parseInt(br.readLine());
		 int[][] tiles = new int[N][N];
		   for (int i = 0; i < N; i++) {
		       for (int j = 0; j < N; j++) {
		          tiles[i][j] = Integer.parseInt(br.readLine());
		       }
		   }
		    Board initial = new Board(tiles);
		    Solver solver = new Solver(initial);
		    Iterator<Board> solution = solver.solution(); 
		    while(solution.hasNext())
		       System.out.println(solution.next());
		    if (!solver.isSolvable()) System.out.println("No solution possible");
		    else System.out.println("Minimum number of moves = " + solver.moves());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
