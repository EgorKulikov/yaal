package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] weights = IOUtils.readIntArray(in, count);
		int[] order = ArrayUtils.order(weights);
		int[] last = new int[count];
		int[] next = new int[count];
		int[] ansLeft = new int[count];
		int[] ansRight = new int[count];
		for (int i = 0; i < count; i++) {
			last[i] = i - 1;
			next[i] = i + 1;
		}
		int answer = 0;
		for (int i : order) {
			answer = Math.min(ansLeft[i], ansRight[i]) + 1;
			if (last[i] != -1) {
				next[last[i]] = next[i];
				ansRight[last[i]] = answer;
			}
			if (next[i] != count) {
				last[next[i]] = last[i];
				ansLeft[next[i]] = answer;
			}
		}
		out.printLine(answer);
    }
}
