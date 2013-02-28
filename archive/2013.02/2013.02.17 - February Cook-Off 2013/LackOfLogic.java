package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LackOfLogic {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String question = in.readLine(false);
		boolean[] used = new boolean[26];
		for (int i = 0; i < question.length(); i++) {
			if (Character.isLetter(question.charAt(i)))
				used[Character.toLowerCase(question.charAt(i)) - 'a'] = true;
		}
		for (int i = 0; i < 26; i++) {
			if (!used[i]) {
				out.printLine((char)('a' + i));
				return;
			}
		}
		out.printLine("~");
    }
}
