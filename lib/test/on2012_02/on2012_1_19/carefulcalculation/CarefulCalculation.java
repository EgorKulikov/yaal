package on2012_02.on2012_1_19.carefulcalculation;

import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CarefulCalculation {
	final int[] primes = IntegerUtils.generatePrimes(100000);
	final int[][] edges = new int[primes.length][];

	{
		for (int i = 0; i < primes.length; i++) {
			int current = primes[i] - 1;
			int count = 0;
			for (int j = 2; j * j <= current; j++) {
				while (current % j == 0) {
					count++;
					current /= j;
				}
			}
			if (current != 1)
				count++;
			edges[i] = new int[count];
			current = primes[i] - 1;
			int index = 0;
			for (int j = 2; j * j <= current; j++) {
				while (current % j == 0) {
					edges[i][index++] = j;
					current /= j;
				}
			}
			if (current != 1)
				edges[i][index] = current;
		}
		for (int[] vertex : edges) {
			for (int i = 0; i < vertex.length; i++)
				vertex[i] = Arrays.binarySearch(primes, vertex[i]);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		@SuppressWarnings({"unchecked"})
		List<Pair<Long, Long>>[] segments = new List[primes.length];
		for (int i = 0; i < segments.length; i++)
			segments[i] = new ArrayList<Pair<Long, Long>>();
		for (int i = 0; i < count; i++) {
			int p = in.readInt();
			int exponent = in.readInt();
			segments[Arrays.binarySearch(primes, p)].add(Pair.makePair(0L, (long)exponent));
		}
		long answer = 0;
		for (int i = primes.length - 1; i >= 0; i--) {
			if (segments[i].isEmpty())
				continue;
			Collections.sort(segments[i]);
			long curStart = segments[i].get(0).first;
			long curFinish = segments[i].get(0).second;
			for (int j = 1; j <= segments[i].size(); j++) {
				Pair<Long, Long> segment = j != segments[i].size() ? segments[i].get(j) : null;
				if (segment != null && segment.first <= curFinish)
					curFinish += segment.second - segment.first;
				else {
					for (int k : edges[i])
						segments[k].add(Pair.makePair(curStart + 1, curFinish + 1));
					if (segment != null) {
						curStart = segment.first;
						curFinish = segment.second;
					}
				}
			}
			if (i == 0)
				answer = curFinish;
		}
		out.printLine(answer);
	}
}
