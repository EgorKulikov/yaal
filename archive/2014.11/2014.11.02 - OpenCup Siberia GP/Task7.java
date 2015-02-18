package net.egork;



import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task7 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = IOUtils.readTable(in, rowCount, columnCount);
		Graph graph = new Graph(rowCount * columnCount + 54);
		int source = rowCount * columnCount + 52;
		int sink = source + 1;
		int totalSquares = 0;
		IntSet presentColors = new IntHashSet();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (table[i][j] == '.') {
					continue;
				}
				if (table[i][j] == '*') {
					totalSquares++;
				} else {
					presentColors.add(table[i][j]);
				}
				if ((i + j) % 2 == 0) {
					if (table[i][j] == '*') {
						graph.addFlowEdge(source, i * columnCount + j, 1);
					} else {
						graph.addFlowEdge(rowCount * columnCount + table[i][j] - 'A', i * columnCount + j, 1);
					}
					for (int k = 0; k < 4; k++) {
						int row = i + MiscUtils.DX4[k];
						int column = j + MiscUtils.DY4[k];
						if (MiscUtils.isValidCell(row, column, rowCount, columnCount)) {
							graph.addFlowEdge(i * columnCount + j, row * columnCount + column, 1);
						}
					}
				} else {
					if (table[i][j] == '*') {
						graph.addFlowEdge(i * columnCount + j, sink, 1);
					} else {
						graph.addFlowEdge(i * columnCount + j, rowCount * columnCount + table[i][j] - 'A' + 26, 1);
					}
				}
			}
		}
		for (int i = 0; i < 26; i++) {
			graph.addFlowEdge(source, rowCount * columnCount + i, 1);
			graph.addFlowEdge(rowCount * columnCount + i + 26, sink, 1);
		}
		int total = totalSquares + presentColors.size();
		long flow = MaxFlow.dinic(graph, source, sink);
		if (2 * flow != total) {
			out.printLine("No");
			return;
		}
		out.printLine("Yes");
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (table[i][j] == '.') {
					continue;
				}
				if (Character.isLetter(table[i][j]) && table[i][j] != 'v') {
					table[i][j] = '@';
				}
				if ((i + j) % 2 == 1) {
					continue;
				}
				for (int k = graph.firstOutbound(i * columnCount + j); k != -1; k = graph.nextOutbound(k)) {
					if (graph.flow(k) == 1 && graph.destination(k) < rowCount * columnCount) {
						int row = graph.destination(k) / columnCount;
						int column = graph.destination(k) % columnCount;
						if (row == i - 1) {
							table[row][column] = '^';
							table[i][j] = 'v';
						} else if (row == i + 1) {
							table[i][j] = '^';
							table[row][column] = 'v';
						} else if (column == j - 1) {
							table[row][column] = '<';
							table[i][j] = '>';
						} else {
							table[i][j] = '<';
							table[row][column] = '>';
						}
					}
				}
			}
		}
		for (char[] row : table) {
			out.printLine(row);
		}
    }
}
