package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		if (first.length != second.length) {
			out.printLine("NO");
			return;
		}
		for (int i = 0; i < first.length; i++) {
			if (first[i] == second[i]) {
				out.printLine("NO");
				return;
			}
		}
		Arrays.sort(first);
		Arrays.sort(second);
		for (int i = 0; i < first.length; i++) {
			if (first[i] != second[i]) {
				out.printLine("NO");
				return;
			}
		}
		out.printLine("YES");
	}
}
