package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AlternatingCharacters {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		char last = 0;
		int answer = 0;
		for (char c : s) {
			if (c == last) {
				answer++;
			}
			last = c;
		}
		out.printLine(answer);
    }
}
