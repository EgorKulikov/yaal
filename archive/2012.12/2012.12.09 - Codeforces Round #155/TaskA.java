package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] first = new int[count];
		int[] second = new int[count];
		int[] last = new int[5001];
		int j = 0;
		for (int i = 0; i < 2 * count; i++) {
			int number = in.readInt();
			if (last[number] == 0)
				last[number] = i + 1;
			else {
				first[j] = last[number];
				second[j++] = i + 1;
				last[number] = 0;
			}
		}
		if (j != count)
			out.printLine(-1);
		else {
			for (int i = 0; i < count; i++) {
				out.printLine(first[i], second[i]);
			}
		}
	}
}
