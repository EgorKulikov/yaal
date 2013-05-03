package net.egork;

import net.egork.collections.map.Counter;
import net.egork.collections.map.EHashMap;
import net.egork.collections.map.Indexer;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskC {
	Map<Long, Counter<Long>> probability = new EHashMap<Long, Counter<Long>>();
	int[] digits;
	Indexer<Long> allKeys = new Indexer<Long>();
	long[] keys = new long[5915];
	Map<Long, Long> initial = new EHashMap<Long, Long>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int setCount = in.readInt();
		int size = in.readInt();
		int limit = in.readInt();
		int count = in.readInt();
		digits = new int[size];
		init(size, limit, 0L, 1L);
		out.printLine("Case #1:");
//		out.printLine(allKeys.size(), probability.size());
		long[] digits = new long[probability.size()];
		int[][] delta = new int[5915][probability.size()];
//		ArrayUtils.fill(delta, -100500);
		double[] base = new double[probability.size()];
		{
			int j = 0;
			for (Map.Entry<Long, Counter<Long>> entry : probability.entrySet()) {
				digits[j] = entry.getKey();
				for (Map.Entry<Long, Long> longEntry : entry.getValue().entrySet()) {
					delta[allKeys.get(longEntry.getKey())][j] = (int)(long)longEntry.getValue();
				}
				base[j] = initial.get(entry.getKey());
				j++;
			}
		}
		double[] scores = new double[digits.length];
		for (int i = 0; i < setCount; i++) {
			System.err.println(i);
			long[] results = IOUtils.readLongArray(in, count);
			double bestScore = 0;
			long answer = -1;
			System.arraycopy(base, 0, scores, 0, scores.length);
			for (long j : results) {
				int index = allKeys.get(j);
				for (int k = 0; k < scores.length; k++)
					scores[k] *= delta[index][k];
			}
			for (int j = 0; j < scores.length; j++) {
				if (scores[j] > bestScore) {
					bestScore = scores[j];
					answer = digits[j];
				}
			}
			out.printLine(answer);
		}
    }

	long[][] c = IntegerUtils.generateBinomialCoefficients(13);

	private void init(int remaining, int limit, long encoded, long p) {
		if (remaining == 0) {
			Counter<Long> counter = new Counter<Long>();
			long copy = encoded;
			for (int i = 0; i < digits.length; i++) {
				digits[i] = (int) (encoded % 10);
				encoded /= 10;
			}
			for (int i = 0; i < (1 << digits.length); i++) {
				long result = 1;
				for (int j = 0; j < digits.length; j++) {
					if ((i >> j & 1) == 1)
						result *= digits[j];
				}
				counter.add(result);
				keys[allKeys.get(result)] = result;
			}
			probability.put(copy, counter);
			initial.put(copy, p);
			return;
		}
		if (limit == 1)
			return;
		for (int i = 0; i <= remaining; i++) {
			init(remaining - i, limit - 1, encoded, p * c[remaining][i]);
			encoded *= 10;
			encoded += limit;
		}
	}
}
