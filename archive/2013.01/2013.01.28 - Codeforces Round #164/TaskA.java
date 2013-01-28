package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] home = new int[count];
		int[] away = new int[count];
		IOUtils.readIntArrays(in, home, away);
		int result = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (home[i] == away[j])
					result++;
			}
		}
		out.printLine(result);
	}
}
