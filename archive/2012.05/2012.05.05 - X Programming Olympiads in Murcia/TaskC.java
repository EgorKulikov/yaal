package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] bases = IOUtils.readIntArray(in, count);
		long answer = 1;
		for (int i : bases)
			answer *= i;
		long diff = 1;
		for (int i = 0; i < (count + 1) / 2; i++)
			diff *= Math.min(bases[i], bases[count - i - 1]);
		answer -= diff;
		out.printLine(answer);
	}
}
