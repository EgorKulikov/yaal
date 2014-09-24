package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.collections.map.Indexer;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Map;

public class AlexVsFedor {
	int[] mods = new int[2];

	{
		int j = 0;
		for (int i = (int) 1e9; j < mods.length; i++) {
			if (IntegerUtils.isPrime(i)) {
				mods[j++] = i;
			}
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] weight = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, weight);
		ArrayUtils.orderBy(weight, from, to);
		MiscUtils.decreaseByOne(from, to);
		int start = 0;
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		long numerator = 1;
		for (int i = 1; i <= edgeCount; i++) {
			if (i == edgeCount || weight[i] != weight[i - 1]) {
				Graph graph = new BidirectionalGraph(count);
				for (int j = start; j < i; j++) {
					if (setSystem.get(from[j]) != setSystem.get(to[j])) {
						graph.addSimpleEdge(setSystem.get(from[j]), setSystem.get(to[j]));
					}
				}
				boolean[] visited = new boolean[count];
				for (int j = 0; j < count; j++) {
					if (!visited[j] && graph.firstOutbound(j) != -1) {
						numerator *= count(build(j, graph, visited));
					}
				}
				for (int j = start; j < i; j++) {
					setSystem.join(from[j], to[j]);
				}
				start = i;
			}
		}
		long denominator = count(build(0, BidirectionalGraph.createGraph(count, from, to), new boolean[count]));
		out.printLine(new Rational(numerator, denominator));
    }

	private int[][] build(int id, Graph graph, boolean[] visited) {
		Indexer<Integer> indexer = new Indexer<>();
		dfs(id, graph, visited, indexer);
		int[][] matrix = new int[indexer.size()][indexer.size()];
		for (Map.Entry<Integer, Integer> entry : indexer.entrySet()) {
			int from = entry.getValue();
			for (int i = graph.firstOutbound(entry.getKey()); i != -1; i = graph.nextOutbound(i)) {
				int to = indexer.get(graph.destination(i));
				matrix[from][from]--;
				matrix[to][to]--;
				matrix[from][to]++;
				matrix[to][from]++;
			}
		}
		return matrix;
	}

	private void dfs(int id, Graph graph, boolean[] visited, Indexer<Integer> indexer) {
		if (visited[id]) {
			return;
		}
		indexer.get(id);
		visited[id] = true;
		for (int i = graph.firstOutbound(id); i != -1; i = graph.nextOutbound(i)) {
			dfs(graph.destination(i), graph, visited, indexer);
		}
	}

	long count(int[][] matrix) {
		long[][] m = new long[matrix.length - 1][matrix.length - 1];
		long result = 0;
		long mod = 1;
		for (int cMod : mods) {
			for (int j = 0; j < m.length; j++) {
				for (int k = 0; k < m.length; k++) {
					m[j][k] = matrix[j][k] >> 1;
				}
			}
			for (int j = 0; j < m.length; j++) {
				boolean found = false;
				for (int k = j; k < m.length; k++) {
					if (m[k][j] != 0) {
						found = true;
						for (int l = j; l < m.length; l++) {
							long temp = m[j][l];
							m[j][l] = m[k][l];
							m[k][l] = temp;
						}
						break;
					}
				}
				if (!found) {
					break;
				}
				long reverse = IntegerUtils.reverse(m[j][j], cMod);
				for (int k = j + 1; k < m.length; k++) {
					for (int l = m.length - 1; l >= j; l--) {
						m[k][l] -= m[j][l] * m[k][j] % cMod * reverse;
						m[k][l] %= cMod;
					}
				}
			}
			long cResult = m.length % 2 == 0 ? 1 : -1;
			for (int j = 0; j < m.length; j++) {
				cResult *= m[j][j];
				cResult %= cMod;
			}
			if (cResult < 0) {
				cResult += cMod;
			}
			result = IntegerUtils.findCommon(result, mod, cResult, cMod);
			mod *= cMod;
		}
		return result;
	}
}
