package on2015_03.on2015_03_06_March_Challenge_2015.Count_Substrings;



import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CountSubstrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int limit = in.readInt();
		int queryCount = in.readInt();
		char[] sequence = IOUtils.readCharArray(in, count);
		int[] ends = new int[count];
		int current = 0;
		int ones = 0;
		int zeroes = 0;
		for (int i = 0; i < count; i++) {
			if (i != 0) {
				if (sequence[i - 1] == '0') {
					zeroes--;
				} else {
					ones--;
				}
			}
			while (current < count && ones <= limit && zeroes <= limit) {
				if (sequence[current++] == '0') {
					zeroes++;
				} else {
					ones++;
				}
			}
			ends[i] = current - (ones <= limit && zeroes <= limit ? 0 : 1);
		}
		SumIntervalTree tree = new SumIntervalTree(ArrayUtils.asLong(ends));
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt();
			int at = Arrays.binarySearch(ends, to);
			if (at < 0) {
				at = -at - 2;
			}
			at = Math.max(at, from - 1);
			at = Math.min(at, to - 1);
			long result = tree.query(from, at) + (to - at - 1) * (long)to;
			result -= (from + to - 1L) * (to - from) / 2;
			out.printLine(result);
		}
	}
}
