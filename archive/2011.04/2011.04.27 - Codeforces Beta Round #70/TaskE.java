import net.egork.collections.Pair;
import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		int time = in.readInt();
		char[][] scientists = IOUtils.readTable(in, size, size);
		char[][] rescue = IOUtils.readTable(in, size, size);
		Graph graph = new Graph(2 * size * size + 2);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Character.isDigit(scientists[i][j]))
					graph.add(new FlowEdge(2 * size * size, i * size + j, scientists[i][j] - '0'));
				if (Character.isDigit(rescue[i][j]))
					graph.add(new FlowEdge(size * size + i * size + j, 2 * size * size + 1, rescue[i][j] - '0'));
			}
		}
		int badRow = -1;
		int badColumn = -1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (rescue[i][j] == 'Z') {
					badRow = i;
					badColumn = j;
				}
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Character.isDigit(scientists[i][j]) && scientists[i][j] != '0') {
					if (rescue[i][j] != '0')
						graph.add(new FlowEdge(i * size + j, size * size + i * size + j, Integer.MAX_VALUE));
					Set<Pair<Integer, Integer>> canBe = new HashSet<Pair<Integer, Integer>>();
					canBe.add(Pair.makePair(i, j));
					Set<Pair<Integer, Integer>> caNtBe = new HashSet<Pair<Integer, Integer>>();
					caNtBe.add(Pair.makePair(badRow, badColumn));
					for (int k = 0; k < time; k++) {
						Set<Pair<Integer, Integer>> nextCanBe = new HashSet<Pair<Integer, Integer>>();
						for (Pair<Integer, Integer> cell : canBe) {
							for (int l = 0; l < 4; l++) {
								int newRow = cell.first + MiscUtils.DX4[l];
								int newColumn = cell.second + MiscUtils.DY4[l];
								if (newRow >= 0 && newColumn >= 0 && newRow < size && newColumn < size && Character.isDigit(scientists[newRow][newColumn])) {
									Pair<Integer, Integer> next = Pair.makePair(newRow, newColumn);
									if (!caNtBe.contains(next)) {
										if (rescue[newRow][newColumn] != '0')
											graph.add(new FlowEdge(i * size + j, size * size + newRow * size + newColumn, Integer.MAX_VALUE));
										nextCanBe.add(next);
									}
								}
							}
						}
						Set<Pair<Integer, Integer>> nextCaNtBe = new HashSet<Pair<Integer, Integer>>();
						for (Pair<Integer, Integer> cell : caNtBe) {
							nextCaNtBe.add(cell);
							for (int l = 0; l < 4; l++) {
								int newRow = cell.first + MiscUtils.DX4[l];
								int newColumn = cell.second + MiscUtils.DY4[l];
								if (newRow >= 0 && newColumn >= 0 && newRow < size && newColumn < size && Character.isDigit(scientists[newRow][newColumn])) {
									Pair<Integer, Integer> next = Pair.makePair(newRow, newColumn);
									nextCaNtBe.add(next);
								}
							}
						}
						canBe = nextCanBe;
						caNtBe = nextCaNtBe;
						canBe.removeAll(caNtBe);
					}
				}
			}
		}
		out.println(GraphAlgorithms.dinic(graph, 2 * size * size, 2 * size * size + 1));
	}
}

