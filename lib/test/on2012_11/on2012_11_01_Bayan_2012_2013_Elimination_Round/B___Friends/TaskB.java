package on2012_11.on2012_11_01_Bayan_2012_2013_Elimination_Round.B___Friends;



import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int pairCount = in.readInt();
		int[] attractiveness = IOUtils.readIntArray(in, count);
		Arrays.sort(attractiveness);
		int threshold = 0;
		int remaining = pairCount;
		for (int i = 29; i >= 0; i--) {
			threshold *= 2;
			Counter<Integer> counter = new Counter<Integer>();
			for (int j : attractiveness)
				counter.add(j >> i);
			int different = 0;
			for (int j : counter.keySet()) {
				int k = j ^ (threshold + 1);
				if (k > j)
					different += counter.get(j) * counter.get(k);
			}
			if (different >= remaining)
				threshold++;
			else
				remaining -= different;
		}
		long answer = 0;
//		int total = 0;
//		int totalMore = 0;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				int current = attractiveness[i] ^ attractiveness[j];
				if (current > threshold) {
					answer += current;
//					total++;
				}
//				if (current > threshold)
//					totalMore++;
			}
		}
//		if (total < pairCount || totalMore > pairCount)
//			throw new RuntimeException();
		answer += (long)remaining * threshold;
		out.printLine(answer % ((long)(1e9 + 7)));
	}
}
