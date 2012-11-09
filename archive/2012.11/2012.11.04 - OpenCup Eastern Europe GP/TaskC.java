package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int step = in.readInt();
		int[] sequence = IOUtils.readIntArray(in, count);
		int[] odd = new int[step];
		int[] even = new int[step];
		for (int i = 0; i < step; i++) {
			for (int j = i; j < count; j += step) {
				if ((sequence[j] & 1) == 0)
					even[i]++;
				else
					odd[i]++;
			}
		}
		int answer = 0;
		int oddity = 0;
		for (int i = 0; i < step; i++) {
			if (odd[i] > even[i])
				oddity++;
			answer += Math.min(odd[i], even[i]);
		}
		if ((oddity & 1) == 1) {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < step; i++)
				min = Math.min(min, Math.max(odd[i], even[i]) - Math.min(odd[i], even[i]));
			answer += min;
		}
		out.printLine(answer);
	}
}
