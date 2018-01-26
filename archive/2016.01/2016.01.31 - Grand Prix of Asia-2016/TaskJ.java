package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;

public class TaskJ {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] qty = new int[4];
		for (int i = 0; i < n; i++) {
			qty[in.readInt()]++;
		}
		long answer = 1;
		for (int i = 0; i < n - 2; i++) {
			if (qty[2] > 0) {
				qty[2]--;
				answer *= n - 2 - i;
				answer %= MOD;
			} else {
				if (qty[1] == 0 || qty[3] == 0) {
					answer = 0;
					break;
				}
				answer *= qty[3];
				answer %= MOD;
				qty[1]--;
				qty[3]--;
				qty[2]++;
			}
		}
		if (qty[1] != 2) {
			out.printLine(0);
			return;
		}
		out.printLine(answer);
	}
}
