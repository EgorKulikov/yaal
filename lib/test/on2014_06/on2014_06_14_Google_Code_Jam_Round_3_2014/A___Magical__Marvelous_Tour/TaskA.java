package on2014_06.on2014_06_14_Google_Code_Jam_Round_3_2014.A___Magical__Marvelous_Tour;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long p = in.readInt();
		int q = in.readInt();
		int r = in.readInt();
		int s = in.readInt();
		int[] qty = new int[count];
		for (int i = 0; i < count; i++)
			qty[i] = (int) ((i * p + q) % r + s);
		long[] partial = ArrayUtils.partialSums(qty);
		long[] suffixes = new long[count + 1];
		for (int i = 0; i <= count; i++)
			suffixes[i] = partial[count] - partial[count - i];
		long answer = partial[count];
		for (int i = 0; i < count; i++) {
			long first = partial[i] - partial[0];
			if (first > answer)
				continue;
			long remaining = partial[count] - first;
			int at = Arrays.binarySearch(suffixes, remaining >> 1);
			if (at >= 0)
				answer = Math.min(answer, Math.max(first, Math.max(suffixes[at], remaining - suffixes[at])));
			else {
				int at1 = -at - 1;
				int at2 = -at - 2;
				if (at1 >= 0 && at1 <= count)
					answer = Math.min(answer, Math.max(first, Math.max(suffixes[at1], remaining - suffixes[at1])));
				if (at2 >= 0 && at2 <= count)
					answer = Math.min(answer, Math.max(first, Math.max(suffixes[at2], remaining - suffixes[at2])));
			}
		}
		out.printLine("Case #" + testNumber + ":", 1 - (double)answer / partial[count]);
    }
}
