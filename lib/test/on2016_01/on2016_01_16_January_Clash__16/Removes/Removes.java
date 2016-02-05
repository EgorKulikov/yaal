package on2016_01.on2016_01_16_January_Clash__16.Removes;



import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Removes {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int t = in.readInt();
		int n = in.readInt();
		int b = in.readInt();
		final int[][] c = IOUtils.readIntTable(in, n, n);
		List<IntIntPair> edgesOneVertex = oneVertex(n, b, c);
		List<IntIntPair> edgesDivTwo = n <= 200 ? divTwo(n, b, c) : new ArrayList<IntIntPair>();
		List<IntIntPair> edgesMin = minEdges(n, b, c);
		List<IntIntPair> edgesSpecial = special(n, b, c);
		List<IntIntPair> result = compare(n, edgesOneVertex, edgesDivTwo, edgesMin, edgesSpecial);
		out.printLine(result.size());
		for (IntIntPair pair : result) {
			out.printLine(pair.first, pair.second);
		}
		System.err.println(calcScore(n, result));
	}

	private List<IntIntPair> special(int n, int b, int[][] c) {
		final long[] weight = new long[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				weight[i] += c[i][j];
				weight[j] -= c[i][j];
			}
		}
		Integer[] order = new Integer[n];
		for (int i = 0; i < n; i++) {
			order[i] = i;
		}
		Arrays.sort(order, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Long.compare(weight[o1], weight[o2]);
			}
		});
		return go(n, b, c, order);
	}

	private List<IntIntPair> go(int n, int b, int[][] c, Integer[] order) {
		long cost = 0;
		long best = b + 1;
		double bVal = Double.POSITIVE_INFINITY;
		int at = -1;
		for (int k = 0; k < order.length - 1; k++) {
			int i = order[k];
			boolean was = false;
			for (int j : order) {
				if (i == j) {
					was = true;
				} else if (was) {
					cost += c[i][j];
				} else {
					cost -= c[j][i];
				}
			}
			double cVal = cost * (Math.sqrt(k + 1) + Math.sqrt(order.length - k - 1));
			if (cost <= b && cVal < bVal) {
				best = cost;
				bVal = cVal;
				at = k;
			}
		}
		if (at == -1) {
			return new ArrayList<IntIntPair>();
		}
		List<IntIntPair> result;
		if ((at + 1) * 2 > order.length) {
			result = go(n, (int) (b - best), c, Arrays.copyOfRange(order, 0, at + 1));
		} else {
			result = go(n, (int) (b - best), c, Arrays.copyOfRange(order, at + 1, order.length));
		}
		for (int k = 0; k <= at; k++) {
			for (int l = at + 1; l < order.length; l++) {
				result.add(new IntIntPair(order[k], order[l]));
			}
		}
		return result;
	}

	private List<IntIntPair> compare(int n, List<IntIntPair>...edges) {
		double score = 0;
		List<IntIntPair> result = new ArrayList<>();
		for (List<IntIntPair> current : edges) {
			double cScore = calcScore(n, current);
			if (cScore > score) {
				score = cScore;
				result = current;
			}
		}
		return result;
	}

	protected List<IntIntPair> minEdges(int n, int b, final int[][] c) {
		List<IntIntPair> result = new ArrayList<>();
		IntIntPair[] edges = new IntIntPair[n * (n - 1)];
		int at = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					edges[at++] = new IntIntPair(i, j);
				}
			}
		}
		Arrays.sort(edges, new Comparator<IntIntPair>() {
			@Override
			public int compare(IntIntPair o1, IntIntPair o2) {
				return c[o1.first][o1.second] - c[o2.first][o2.second];
			}
		});
		boolean[][] removed = new boolean[n][n];
		for (IntIntPair pair : edges) {
			if (removed[pair.first][pair.second]) {
				continue;
			}
			if (b >= c[pair.first][pair.second]) {
				result.add(pair);
				b -= c[pair.first][pair.second];
			} else {
				break;
			}
			removed[pair.second][pair.first] = true;
		}
		return result;
	}

	protected List<IntIntPair> divTwo(int n, int b, int[][] c) {
		List<IntIntPair> result = new ArrayList<>();
		boolean[][] removed = new boolean[n][n];
		final int[] dIn = new int[n];
		final int[] dOut = new int[n];
		final double[][] cst = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cst[i][j] = c[i][j];
			}
		}
		NavigableSet<IntIntPair> set = new TreeSet<IntIntPair>(new Comparator<IntIntPair>() {
			@Override
			public int compare(IntIntPair o1, IntIntPair o2) {
				double left = cst[o1.first][o1.second];
				double right = cst[o2.first][o2.second];
				return Double.compare(left, right);
			}
		});
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					set.add(new IntIntPair(i, j));
				}
			}
		}
		while (!set.isEmpty()) {
			IntIntPair current = set.pollFirst();
			if (removed[current.first][current.second] || c[current.first][current.second] > b) {
				removed[current.first][current.second] = true;
				continue;
			}
			int from = current.first;
			int to = current.second;
			result.add(new IntIntPair(from, to));
			dIn[to]++;
			dOut[from]++;
			removed[to][from] = removed[from][to] = true;
			for (int i = 0; i < n; i++) {
				if (i != to && !removed[i][to]) {
					IntIntPair key = new IntIntPair(i, to);
					set.remove(key);
					cst[i][to] /= 1.1;
					set.add(key);
				}
			}
			for (int i = 0; i < n; i++) {
				if (i != from && !removed[from][i]) {
					IntIntPair key = new IntIntPair(from, i);
					set.remove(key);
					cst[from][i] /= 1.1;
					set.add(key);
				}
			}
			b -= c[from][to];
		}
		return result;
	}

	protected List<IntIntPair> oneVertex(int n, int b, int[][] c) {
		long[] cIn = new long[n];
		long[] cOut = new long[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cIn[j] += c[i][j];
				cOut[i] += c[i][j];
			}
		}
		List<IntIntPair> edges = new ArrayList<>();
		boolean[] removed = new boolean[n];
		for (int i = 0; i < n; i++) {
			long best = Long.MAX_VALUE;
			int at = -1;
			for (int j = 0; j < n; j++) {
				if (removed[j]) {
					continue;
				}
				long current = Math.min(cIn[j], cOut[j]);
				if (current < best) {
					best = current;
					at = j;
				}
			}
			if (best > b) {
				break;
			}
			b -= best;
			removed[at] = true;
			for (int j = 0; j < n; j++) {
				if (!removed[j]) {
					if (cIn[at] < cOut[at]) {
						edges.add(new IntIntPair(j, at));
					} else {
						edges.add(new IntIntPair(at, j));
					}
					cOut[j] -= c[j][at];
					cIn[j] -= c[at][j];
				}
			}
		}
		return edges;
	}

	double calcScore(int n, List<IntIntPair> edges) {
		boolean[][] graph = new boolean[n][n];
		ArrayUtils.fill(graph, true);
		for (IntIntPair edge : edges) {
			graph[edge.first][edge.second] = false;
		}
		NavigableSet<Integer> set = new TreeSet<>();
		int[] queue = new int[n];
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited, false);
			queue[0] = i;
			visited[i] = true;
			int size = 1;
			for (int j = 0; j < size; j++) {
				int cur = queue[j];
				for (int k = 0; k < n; k++) {
					if (graph[cur][k] && !visited[k]) {
						visited[k] = true;
						queue[size++] = k;
					}
				}
			}
			set.add(size);
		}
		double score = -1;
		int last = 0;
		for (int i : set) {
			int delta = i - last;
			last = i;
			score += Math.sqrt((double)delta / n);
		}
		return score;
	}
}
