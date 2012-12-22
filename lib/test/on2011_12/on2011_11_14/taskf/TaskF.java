package on2011_12.on2011_11_14.taskf;



import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Collections;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int capacity = in.readInt();
		int[] weight = new int[count];
		int[] usefulness = new int[count];
		IOUtils.readIntArrays(in, weight, usefulness);
		Collections.reverse(Array.wrap(weight));
		Collections.reverse(Array.wrap(usefulness));
		long[] firstWeight = new long[1024];
		long[] firstUsefulness = new long[1024];
		long[] secondWeight = new long[1024];
		long[] secondUsefulness = new long[1024];
		for (int i = 0; i < 1024; i++) {
			for (int j = 0; j < 10 && j < count; j++) {
				if ((i >> j & 1) == 1) {
					firstWeight[i] += weight[j];
					firstUsefulness[i] += usefulness[j];
				}
			}
			for (int j = 10; j < count; j++) {
				if ((i >> (j - 10) & 1) == 1) {
					secondWeight[i] += weight[j];
					secondUsefulness[i] += usefulness[j];
				}
			}
		}
		long maxUsefulness = 0;
		int mask = 0;
		for (int i = 1; i < (1 << count); i++) {
			long currentWeight = firstWeight[i & 1023] + secondWeight[i >> 10];
			long currentUsefulness = firstUsefulness[i & 1023] + secondUsefulness[i >> 10];
			if (currentWeight > capacity)
				continue;
			if (currentUsefulness > maxUsefulness || currentUsefulness == maxUsefulness &&
				Integer.bitCount(i) <= Integer.bitCount(mask))
			{
				maxUsefulness = currentUsefulness;
				mask = i;
			}
		}
		out.printLine(Integer.bitCount(mask), maxUsefulness);
		int[] answer = new int[Integer.bitCount(mask)];
		int index = 0;
		for (int i = 0; i < count; i++) {
			if ((mask >> i & 1) == 1)
				answer[index++] = count - i;
		}
		Arrays.sort(answer);
		out.printLine(Array.wrap(answer).toArray());
	}
}
