package on2015_10.on2015_10_31_Yekaterinbirg_GP.TaskC;



import net.egork.collections.intcollection.Range;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] answer = new int[n];
		int[] next = new int[n];
		answer[n - 1] = k;
		for (int i = n - 2; i >= 0; i--) {
			int[] order = Range.range(i + 1, n).toArray();
			ArrayUtils.sort(order, (f, s) -> answer[f] == answer[s] ? f - s : answer[f] - answer[s]);
			int required = 0;
			int upTo = (n - i - 1) / 2;
			Arrays.fill(next, i + 1, n, 0);
			for (int j = 0; j < upTo; j++) {
				next[order[j]] = answer[order[j]] + 1;
				required += next[order[j]];
			}
			if (required > k) {
				answer[i] = -1;
			} else {
				next[i] = k - required;
				System.arraycopy(next, i, answer, i, n - i);
			}
		}
		out.printLine(answer);
	}
}
