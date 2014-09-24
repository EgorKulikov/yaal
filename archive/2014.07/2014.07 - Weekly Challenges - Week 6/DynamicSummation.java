package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LCA;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DynamicSummation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		DFSOrder order = new DFSOrder((BidirectionalGraph)graph);
		int[] level = new int[count];
		dfs(0, -1, 0, graph, level);
		int[][] parent = new int[20][count];
		for (int i = 1; i < count; i++) {
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				if (level[graph.destination(j)] < level[i]) {
					parent[0][i] = graph.destination(j);
					break;
				}
			}
		}
		for (int i = 1; i < 20; i++) {
			for (int j = 0; j < count; j++) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
			}
		}
		int[] primes = IntegerUtils.generatePrimes(102);
		int[] mods = new int[7];
		for (int i = 0; i < 7; i++) {
			mods[i] = 1;
			for (int j = i * 4; j < i * 4 + 4 && j < primes.length; j++) {
				int current = primes[j];
				while (current * primes[j] <= 101) {
					current *= primes[j];
				}
				mods[i] *= current;
			}
		}
		IntervalTree[] trees = new IntervalTree[7];
		for (int i = 0; i < 7; i++) {
			final int mod = mods[i];
			trees[i] = new LongIntervalTree(count) {
				@Override
				protected long joinValue(long left, long right) {
					return (left + right) % mod;
				}

				@Override
				protected long joinDelta(long was, long delta) {
					return (was + delta) % mod;
				}

				@Override
				protected long accumulate(long value, long delta, int length) {
					return (value + delta * length) % mod;
				}

				@Override
				protected long neutralValue() {
					return 0;
				}

				@Override
				protected long neutralDelta() {
					return 0;
				}
			};
		}
		int queryCount = in.readInt();
//		long[] phi = MultiplicativeFunction.PHI.calculateUpTo(102);
//		System.err.println(IntegerUtils.generatePrimes(102).length);
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == 'U') {
				int root = in.readInt() - 1;
				int current = in.readInt() - 1;
				long a = in.readLong();
				long b = in.readLong();
				if (root == current) {
					root = current = 0;
				}
				if (order.position[root] < order.position[current] || order.end[root] > order.end[current] || root == current) {
					for (int j = 0; j < 7; j++) {
						int mod = mods[j];
						long value = IntegerUtils.power(a % mod, b, mod) + IntegerUtils.power((a + 1) % mod, b, mod) +
							IntegerUtils.power((b + 1) % mod, a, mod);
						trees[j].update(order.position[current], order.end[current], value);
					}
				} else {
					int delta = level[root] - level[current] - 1;
					for (int j = 19; j >= 0; j--) {
						if (delta >= (1 << j)) {
							delta -= 1 << j;
							root = parent[j][root];
						}
					}
					for (int j = 0; j < 7; j++) {
						int mod = mods[j];
						long value = (IntegerUtils.power(a % mod, b, mod) + IntegerUtils.power((a + 1) % mod, b, mod) +
							IntegerUtils.power((b + 1) % mod, a, mod)) % mod;
						trees[j].update(0, order.position[root] - 1, value);
						trees[j].update(order.end[root] + 1, count - 1, value);
					}
				}
			} else {
				int root = in.readInt() - 1;
				int current = in.readInt() - 1;
				int mod = in.readInt();
				int curVal = 0;
				int curMod = 1;
				if (root == current) {
					root = current = 0;
				}
				if (order.position[root] < order.position[current] || order.end[root] > order.end[current] || root == current) {
					for (int j = 0; j < 7; j++) {
						int localMod = IntegerUtils.gcd(mod, mods[j]);
						if (localMod == 1) {
							continue;
						}
						int localVal = (int) (trees[j].query(order.position[current], order.end[current]) % localMod);
						for (int k = 0; k < localMod; k++) {
							if ((curMod * k + curVal) % localMod == localVal) {
								curVal += curMod * k;
								curMod *= localMod;
								break;
							}
						}
					}
				} else {
					int delta = level[root] - level[current] - 1;
					for (int j = 19; j >= 0; j--) {
						if (delta >= (1 << j)) {
							delta -= 1 << j;
							root = parent[j][root];
						}
					}
					for (int j = 0; j < 7; j++) {
						int localMod = IntegerUtils.gcd(mod, mods[j]);
						if (localMod == 1) {
							continue;
						}
						int localVal = (int) ((trees[j].query(0, order.position[root] - 1) + trees[j].query(order.end[root] + 1, count - 1)) % localMod);
						for (int k = 0; k < localMod; k++) {
							if ((curMod * k + curVal) % localMod == localVal) {
								curVal += curMod * k;
								curMod *= localMod;
								break;
							}
						}
					}
				}
				if (curMod != mod) {
					throw new RuntimeException();
				}
				out.printLine(curVal);
			}
		}
    }

	private void dfs(int current, int last, int curLevel, Graph graph, int[] level) {
		level[current] = curLevel;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last) {
				dfs(next, current, curLevel + 1, graph, level);
			}
		}
	}
}
