package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] encrypted = in.readString().toCharArray();
		char[] original = in.readString().toCharArray();
		int[] qty = new int[26];
		for (char c : original)
			qty[c - 'a']++;
		int[] curQty = new int[26];
		for (int i = 0; i < original.length; i++)
			curQty[encrypted[i] - 'a']++;
		boolean[] valid = new boolean[26];
		int validCount = 0;
		for (int i = 0; i < 26; i++) {
			if (valid[i] = qty[i] == curQty[i])
				validCount++;
		}
		if (validCount == 26) {
			out.printLine("YES");
			return;
		}
		for (int i = original.length; i < encrypted.length; i++) {
			int c = encrypted[i] - 'a';
			curQty[c]++;
			if (valid[c]) {
				valid[c] = false;
				validCount--;
			} else if (curQty[c] == qty[c]) {
				valid[c] = true;
				validCount++;
			}
			c = encrypted[i - original.length] - 'a';
			curQty[c]--;
			if (valid[c]) {
				valid[c] = false;
				validCount--;
			} else if (curQty[c] == qty[c]) {
				valid[c] = true;
				validCount++;
			}
			if (validCount == 26) {
				out.printLine("YES");
				return;
			}
		}
		out.printLine("NO");
    }
}
