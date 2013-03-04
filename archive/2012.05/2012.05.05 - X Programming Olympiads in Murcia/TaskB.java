package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int count = in.readInt();
		int[] bars = IOUtils.readIntArray(in, count);
		boolean[] can = new boolean[length + 1];
		can[0] = true;
		for (int i : bars) {
			for (int j = length; j >= i; j--) {
				if (can[j - i])
					can[j] = true;
			}
		}
		if (can[length])
			out.printLine("YES");
		else
			out.printLine("NO");
	}
}
