package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import javax.security.auth.login.AccountException;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int sum = in.readInt();
		if (sum == 0 && length > 1 || sum > 9 * length) {
			out.printLine(-1, -1);
			return;
		}
		if (sum == 0) {
			out.printLine(0, 0);
			return;
		}
		int[] min = new int[length];
		int[] max = new int[length];
		int remaining = sum;
		for (int i = 0; i < length; i++) {
			int current = Math.min(9, remaining);
			max[i] = current;
			remaining -= current;
		}
		remaining = sum;
		for (int i = length - 1; i > 0; i--) {
			int current = Math.min(9, remaining - 1);
			min[i] = current;
			remaining -= current;
		}
		min[0] = remaining;
		for (int i : min) {
			out.print(i);
		}
		out.print(' ');
		for (int i : max) {
			out.print(i);
		}
		out.printLine();
    }
}
