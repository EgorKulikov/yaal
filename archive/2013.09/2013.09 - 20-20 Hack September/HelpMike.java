package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HelpMike {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int total = in.readInt();
		int divisibleBy = in.readInt();
		long answer = 0;
		for (int i = 1; i * 2 < divisibleBy; i++)
			answer += (long)((total - i) / divisibleBy + 1) * ((total - divisibleBy + i) / divisibleBy + 1);
		answer += (long)(total / divisibleBy) * (total / divisibleBy - 1) / 2;
		if (divisibleBy % 2 == 0)
			answer += (long)((total - divisibleBy / 2) / divisibleBy) * ((total - divisibleBy / 2) / divisibleBy + 1) / 2;
		out.printLine(answer);
    }
}
