package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Poll {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		boolean[] has = new boolean[101];
		for (int i = 0; i < count; i++)
			has[in.readInt()] = true;
		boolean[] current = new boolean[101];
		for (int i = 1; ; i++) {
			Arrays.fill(current, false);
			boolean good = true;
			for (int j = 0; j <= i; j++)
				current[(j * 100 + i / 2) / i] = true;
			for (int j = 0; j <= 100; j++) {
				if (has[j] && !current[j])
					good = false;
			}
			if (good) {
				out.printLine(i);
				return;
			}
		}
    }
}
