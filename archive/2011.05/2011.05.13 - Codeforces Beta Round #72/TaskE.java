import net.egork.collections.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int length = 0;
		int[] values = new int[count];
		for (int i = 0; i < count; i++) {
			String string = in.readString();
			length = string.length();
			values[i] = Integer.parseInt(string, 2);
		}
		int[][] delta = new int[length + 1][];
		for (int i = 0; i <= length; i++)
			delta[i] = new int[1 << i];
		ArrayUtils.fill(delta, length);
		int totalLength = length;
		for (int i = 1; i < count; i++) {
			int bestShift = 0;
			int last = values[i - 1];
			int current = values[i];
			for (int k = 1; k <= length; k++) {
				if ((last & ((1 << k) - 1)) == (current >> (length - k)))
					bestShift = k;
			}
			totalLength += length - bestShift;
			int bestValue = length;
			for (int k = 0; k <= length; k++) {
				int prefix = (current >> (length - k));
				bestValue = Math.min(bestValue, delta[k][prefix] - k);
			}
			bestValue += bestShift;
			for (int k = 0; k <= length; k++) {
				int suffix = (last & ((1 << k) - 1));
				delta[k][suffix] = Math.min(delta[k][suffix], bestValue);
			}
		}
		totalLength += Math.min(0, CollectionUtils.minElement(Array.wrap(delta[length])));
		out.println(totalLength);
	}
}

