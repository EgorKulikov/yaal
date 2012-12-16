package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		char[] t = in.readString().toCharArray();
		int[] remaining = new int[256];
		for (char c : s)
			remaining[c]++;
		int[] delta = new int[256];
		for (char c : t)
			delta[c]++;
		for (char c : s)
			delta[c]--;
		char curLetter = 'A';
		int changes = 0;
		for (int i = 0; i < s.length; i++) {
			if (delta[s[i]] < 0) {
				while (delta[curLetter] <= 0)
					curLetter++;
				if (curLetter < s[i] || delta[s[i]] == -remaining[s[i]]) {
					delta[s[i]]++;
					remaining[s[i]]--;
					s[i] = curLetter;
					delta[curLetter]--;
					changes++;
					continue;
				}
			}
			remaining[s[i]]--;
		}
		out.printLine(changes);
		out.printLine(s);
	}
}
