import net.egork.graph.Edge;
import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class Knights implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		char[][] field = IOUtils.readTable(in, size, size);
		Graph graph = new Graph(size * size + 2);
		boolean[] exists = new boolean[size * size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				exists[i * size + j] = field[i][j] == '.';
		}
		for (int i = 0; i < size * size; i++) {
			if (!exists[i])
				continue;
			int row = i / size;
			int column = i % size;
			if ((row + column) % 2 == 1)
				continue;
			graph.add(new FlowEdge(size * size, i, 1));
			for (int j = -2; j <= 2; j++) {
				for (int k = -2; k <= 2; k++) {
					if (Math.abs(j) + Math.abs(k) == 3) {
						int nextRow = row + j;
						int nextColumn = column + k;
						int index = nextRow * size + nextColumn;
						if (nextRow >= 0 && nextRow < size && nextColumn >= 0 && nextColumn < size && exists[index])
							graph.add(new FlowEdge(i, index, 1));
					}
				}
			}
		}
		for (int i = 0; i < size * size; i++) {
			if (exists[i] && ((i / size + i % size) % 2 == 1))
				graph.add(new FlowEdge(i, size * size + 1, 1));
		}
		GraphAlgorithms.dinic(graph, size * size, size * size + 1);
		for (Edge edge : graph.getIncident(size * size)) {
			if (edge.getFlow() == 1)
				field[edge.getDestination() / size][edge.getDestination() % size] = 'K';
		}
		for (char[] row : field)
			out.println(row);
	}
}

