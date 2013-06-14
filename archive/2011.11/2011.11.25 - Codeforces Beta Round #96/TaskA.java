package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] word = in.readString().toCharArray();
		for (int i = 1; i < word.length; i++) {
			if (Character.isLowerCase(word[i])) {
				out.printLine(word);
				return;
			}
		}
		for (int i = 0; i < word.length; i++) {
			if (Character.isLowerCase(word[i]))
				word[i] = Character.toUpperCase(word[i]);
			else
				word[i] = Character.toLowerCase(word[i]);
		}
		out.printLine(word);
	}
}
