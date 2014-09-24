package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int stamina = in.readInt();
		int start = in.readInt();
		int count = in.readInt();
		int[] more = new int[count];
		int[] less = new int[count];
		IOUtils.readIntArrays(in, more, less);
		for (int i = 0; i < count; i++) {
			if (i > 2) {
				stamina += less[i - 3] + more[i - 3];
			}
			if (stamina < i + start) {
				out.printLine(i + 1);
				return;
			}
			stamina -= less[i];
		}
		out.printLine("Ok");
    }
}
