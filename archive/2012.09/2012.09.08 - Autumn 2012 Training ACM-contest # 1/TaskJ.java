package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt() - 1;
		long total = 1;
		int length;
		for (length = 1; ; length++) {
			if (length % 2 == 1)
				total *= 5;
			else
				total *= 3;
			if (count < total)
				break;
			count -= total;
		}
		char[] answer = new char[length];
		char[][] letters = {"hkmrt".toCharArray(), "aou".toCharArray()};
		for (int i = 0; i < length; i++) {
			total /= letters[i % 2].length;
			for (int j = 0; j < letters[i % 2].length; j++) {
				if (count < total) {
					answer[i] = letters[i % 2][j];
					break;
				}
				count -= total;
			}
		}
		out.printLine("Case #" + testNumber + ":", new String(answer));
	}
}
