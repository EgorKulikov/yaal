import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		long[] weights = IOUtils.readLongArray(in, count);
		int queryCount = in.readInt();
		int[] a = new int[queryCount];
		int[] b = new int[queryCount];
		IOUtils.readIntArrays(in, a, b);
		Integer[] order = SequenceUtils.order(Array.wrap(b));
		int lastB = 0;
		long[] temp = new long[count];
		long[] answer = new long[queryCount];
		int max = (int) (Math.sqrt(count) + .5);
		for (int i : order) {
			if (b[i] != lastB) {
				if (b[i] > max) {
					for (int j = a[i] - 1; j < count; j += b[i])
						answer[i] += weights[j];
					continue;
				} else {
					for (int j = count - 1; j >= 0; j--) {
						if (j >= count - b[i])
							temp[j] = weights[j];
						else
							temp[j] = weights[j] + temp[j + b[i]];
					}
					lastB = b[i];
				}
			}
			answer[i] = temp[a[i] - 1];
		}
		for (long value : answer)
			out.println(value);
	}
}

