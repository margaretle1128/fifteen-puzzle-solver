package fifteenpuzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.PriorityQueue;

public class Solver {
	public static int SIZE; //the dimension of the board
	public static int[][] goal; //goal board

	public static int[][] generateGoalBoard() {
		int[][] board = new int[SIZE][SIZE];
		int index = 1;

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (i == SIZE - 1 && j == SIZE - 1) {
					board[i][j] = 0;
					break;
				}
				board[i][j] = index;
				index++;
			}
		}
		return board;
	}

	public static void main(String[] args) throws  IOException {

		long startTime = System.currentTimeMillis();

		System.out.println("number of arguments: " + args.length);
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}

		if (args.length < 2) {
			System.out.println("File names are not specified");
			System.out.println("usage: java " + MethodHandles.lookup().lookupClass().getName() + " input_file output_file");
			return;
		}

		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		SIZE = br.read() - '0';

		br.readLine();

		int[][] board = new int[SIZE][SIZE];
		int c1, c2;

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				c1 = br.read();
				c2 = br.read();
				br.read();// skip the space

				if (c1 == ' ')
					c1 = '0';
				if (c2 == ' ')
					c2 = '0';
				board[i][j] = 10 * (c1 - '0') + (c2 - '0');
			}
		}
		br.close();

		System.out.println("INITIAL board : " + Arrays.deepToString(board));
		goal = generateGoalBoard();
		Vertex resultState = solve(board);
		Stack<String> resultList = new Stack<>();
		while (resultState != null) {
			resultList.add(resultState.getMove());
			resultState = resultState.getParent();
		}
		resultList.pop();

		File output = new File(args[1]);
		PrintWriter outputFile = new PrintWriter(output);
		while (!resultList.isEmpty()) {
			String res = resultList.pop();
			System.out.println(res);
			outputFile.println(res);
		}
		outputFile.close();

		//calculating run time
		long endTime = System.currentTimeMillis();
		long runtime = endTime - startTime;
		System.out.println("Runtime: " + runtime + "ms");
	}

	public static Vertex solve(int[][] start) {
		HashMap<Integer,Vertex> closed = new HashMap<>();
		//Hashset is there to quick loop up element in the queue
		HashSet<Integer> inQueue = new HashSet<>();
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		Vertex startState = new Vertex(start);
		q.add(startState);
		Vertex goalVertex = new Vertex(goal);
		inQueue.add(startState.getHashCode());

		while (!q.isEmpty()) {
			Vertex node = q.remove();
			inQueue.remove(node.getHashCode());

			for (Vertex neighbor : node.generateChild()) {
				if (neighbor.getHashCode() == goalVertex.getHashCode()) {
					neighbor.setParent(node);
					return neighbor;
				}

				Vertex closedNeighbor = null;
				Vertex openNeighbor = null;
				if (inQueue.contains(neighbor.getHashCode())) {
					//Check if the neighbor is already in the queue
					openNeighbor = q.stream().filter(n -> n.equals(neighbor)).findFirst().get();
					if (openNeighbor.getDistanceFromStart() > neighbor.getDistanceFromStart()) {
						//If it is, check if the one in the queue have higher g value
						q.remove(openNeighbor);
						neighbor.setParent(node);
						q.add(neighbor);
					}
				}
				else {
					int neighborCode = neighbor.getHashCode();
					if (closed.containsKey(neighborCode)) {
						//Check if we already visited the state
						closedNeighbor = closed.get(neighbor.getHashCode());
						if(closedNeighbor.getDistanceFromStart() > neighbor.getDistanceFromStart()) {
							//If we already did, we don't visit it anymore
							closed.put(neighborCode, neighbor);
						}
					}

				}
				if (openNeighbor == null && closedNeighbor == null) {
					q.add(neighbor);
					neighbor.setParent(node);
					inQueue.add(neighbor.getHashCode());
				}

			}
			closed.put(node.getHashCode(), node); // this line is moving node to the closed set
		}
		return null;
	}
}
