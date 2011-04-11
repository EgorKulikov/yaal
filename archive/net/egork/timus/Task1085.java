package net.egork.timus;

import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.SimpleEdge;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1085 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		int pathCount = in.readInt();
		boolean[][] connected = new boolean[vertexCount][vertexCount];
		for (int i = 0; i < pathCount; i++) {
			int[] path = in.readIntArray(in.readInt());
			for (int j : path) {
				for (int k : path)
					connected[j - 1][k - 1] = true;
			}
		}
		Graph graph = new Graph(vertexCount);
		for (int i = 0; i < vertexCount; i++) {
			for (int j = 0; j < vertexCount; j++) {
				if (i != j && connected[i][j])
					graph.add(new SimpleEdge(i, j));
			}
		}
		int peopleCount = in.readInt();
		int[] money = new int[peopleCount];
		int[] location = new int[peopleCount];
		int[] pass = new int[peopleCount];
		in.readIntArrays(money, location, pass);
		int stop = -1;
		long moneyRequired = Long.MAX_VALUE;
		for (int i = 0; i < vertexCount; i++) {
			GraphAlgorithms.DistanceResult result = GraphAlgorithms.leviteAlgorithm(graph, i);
			boolean can = true;
			long currentMoneyRequired = 0;
			for (int j = 0; can && j < peopleCount; j++) {
				long distance = result.getDistances()[location[j] - 1];
				if (distance == Long.MAX_VALUE || pass[j] == 0 && distance * 4 > money[j])
					can = false;
				if (pass[j] == 0)
					currentMoneyRequired += distance * 4;
			}
			if (can && currentMoneyRequired < moneyRequired) {
				stop = i;
				moneyRequired = currentMoneyRequired;
			}
		}
		if (stop == -1)
			out.println(0);
		else
			out.println((stop + 1) + " " + moneyRequired);
	}
}

