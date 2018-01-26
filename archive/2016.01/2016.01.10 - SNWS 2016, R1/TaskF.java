package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] qty = new int[26];
		for (char c : in.readString().toCharArray()) {
			qty[c - 'a']++;
		}
		int total = (int) ArrayUtils.sumArray(qty);
		for (int i : qty) {
			if (2 * i > total + 1) {
				out.printLine("IMPOSSIBLE");
				return;
			}
		}
		StringBuilder answer = new StringBuilder();
		int last = -1;
		for (int i = 0; i < total; i++) {
			boolean found = false;
			for (int j = 0; j < 26; j++) {
				if (qty[j] * 2 == total - i + 1) {
					answer.append((char)('a' + j));
					qty[j]--;
					found = true;
					last = j;
					break;
				}
			}
			if (found) {
				continue;
			}
			for (int j = 0; j < 26; j++) {
				if (qty[j] != 0 && j != last) {
					answer.append((char)('a' + j));
					last = j;
					qty[j]--;
					break;
				}
			}
		}
		out.printLine(answer);
	}
}
