package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] qty = IOUtils.readIntArray(in, count);
		int[] willParticipate = new int[(int) (2e6 + 1)];
		for (int i : qty)
			willParticipate[i]++;
		long answer = count;
		for (int i = 2; i <= 2000000; i++) {
			int current = willParticipate[i];
			for (int j = 2 * i; j <= 2000000; j += i)
				current += willParticipate[j];
			if (current > 1)
				answer = Math.max(answer, (long)current * i);
		}
		out.printLine(answer);
    }
}
