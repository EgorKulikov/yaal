package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] money = IOUtils.readIntArray(in, count);
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < count - 1; i++) {
			for (int j = 0; j < money[i] - 1; j++)
				answer.append("PRL");
			if (money[i] != 0)
				answer.append('P');
			answer.append('R');
		}
		for (int i = 0; i < money[count - 1] - 1; i++)
			answer.append("PLR");
		if (money[count - 1] != 0)
			answer.append("P");
		out.printLine(answer);
    }
}
