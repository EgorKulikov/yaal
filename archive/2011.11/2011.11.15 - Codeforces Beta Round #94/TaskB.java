package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int index = in.readInt() - 1;
		long total = (long)s.length * (s.length + 1) / 2;
		if (index >= total) {
			out.printLine("No such line.");
			return;
		}
		int[] validPositions = new int[s.length];
		for (int i = 0; i < s.length; i++)
			validPositions[i] = i;
		int validLength = s.length;
		for (int length = 0; length < s.length; length++) {
			for (char c = 'a'; c <= 'z'; c++) {
				int count = 0;
				long sumLength = 0;
				int any = -1;
				for (int i = 0; i < validLength; i++) {
					int j = validPositions[i];
					if (j + length < s.length && s[j + length] == c) {
						count++;
						sumLength += s.length - j - length;
						any = j;
					}
				}
				if (index < count) {
					printSubstring(s, any, any + length, out);
					return;
				}
				if (index < sumLength) {
					index -= count;
					int newValidLength = 0;
					for (int i = 0; i < validLength; i++) {
						int j = validPositions[i];
						if (j + length < s.length && s[j + length] == c) {
							validPositions[newValidLength++] = j;
						}
					}
					validLength = newValidLength;
					break;
				}
				index -= sumLength;
			}
		}
		throw new RuntimeException();
	}

	private void printSubstring(char[] s, int from, int to, OutputWriter out) {
		for (int i = from; i <= to; i++)
			out.print(s[i]);
		out.printLine();
	}
}
