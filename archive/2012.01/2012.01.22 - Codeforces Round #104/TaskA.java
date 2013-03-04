package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		int count47 = 0;
		int count74 = 0;
		for (int i = 0; i < first.length; i++) {
			if (first[i] == '4' && second[i] == '7')
				count47++;
			else if (first[i] == '7' && second[i] == '4')
				count74++;
		}
		out.printLine(Math.max(count47, count74));
	}
}
