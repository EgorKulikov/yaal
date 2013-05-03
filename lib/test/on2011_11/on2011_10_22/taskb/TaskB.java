package on2011_11.on2011_10_22.taskb;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] remainder = new int[2000000];
		for (int i = 0; i < 1000000; i++)
			remainder[i] = remainder[i + 1000000] = i;
		int count = in.readInt();
		int[] values = IOUtils.readIntArray(in, count);
		Arrays.sort(values);
		int bound = (int) (ArrayUtils.sumArray(values) / 2);
		int[] ways = new int[bound + 1];
		ways[0] = 1;
		boolean[] can = new boolean[bound + 1];
		can[0] = true;
		int max = 0;
		for (int i : values) {
			for (int j = Math.min(max, bound - i); j >= 0; j--) {
				ways[j + i] = remainder[ways[j + i] + ways[j]];
				can[j + i] |= can[j];
			}
			max += i;
		}
		for (int i = bound; i >= 0; i--) {
			if (can[i]) {
				out.printLine(ArrayUtils.sumArray(values) - 2 * i);
				out.printLine(ways[i]);
				return;
			}
		}
	}
}
