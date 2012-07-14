package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CielAndEggs {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		int[] beating = IOUtils.readIntArray(in, count);
		Arrays.sort(beating);
		int answer = Integer.MAX_VALUE;
		for (int k = -1; k < required; k++) {
			int delta = 0;
			if (k != -1) {
				for (int i : beating)
					delta += Math.min(i, beating[k]);
			}
			for (int i = required - 1; i < count; i++) {
				int current = delta;
				int broken = k + 1;
				for (int j = count - 1; broken < required; j--) {
					current += Math.min(beating[i], beating[j]);
					if (k != -1)
						current -= beating[k];
					if (beating[i] >= beating[j])
						broken++;
				}
				answer = Math.min(answer, current);
			}
		}
		out.printLine(answer);
	}
}
