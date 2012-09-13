package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		int sum = 0;
		int index = 0;
		for (int i = 0; i < second.length && index < first.length; i++) {
			if (second[i] == first[index]) {
				sum += i;
				index++;
			}
		}
		if (index == first.length) {
			out.printLine("YES");
			out.printLine(sum);
		} else
			out.printLine("NO");
	}
}
