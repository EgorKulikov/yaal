package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] words = IOUtils.readStringArray(in, count);
		boolean[][] relation = new boolean[26][26];
		for (int i = 1; i < words.length; i++) {
			if (words[i].equals(words[i - 1]))
				continue;
			if (words[i - 1].startsWith(words[i])) {
				out.printLine("no");
				return;
			}
			for (int j = 0; j < words[i - 1].length(); j++) {
				int first = words[i - 1].charAt(j) - 'a';
				int second = words[i].charAt(j) - 'a';
				if (first != second) {
					relation[first][second] = true;
					break;
				}
			}
		}
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < 26; k++)
					relation[j][k] |= relation[j][i] && relation[i][k];
			}
		}
		for (int i = 0; i < 26; i++) {
			if (relation[i][i]) {
				out.printLine("no");
				return;
			}
		}
		out.printLine("yes");
	}
}
