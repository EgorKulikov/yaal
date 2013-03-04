package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CodeCrazyMinions {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] word = in.readString().toCharArray();
		int answer = 2;
		for (int i = 1; i < word.length; i++) {
			if (word[i] >= word[i - 1])
				answer += word[i] - word[i - 1];
			else
				answer += 26 + word[i] - word[i - 1];
			answer++;
		}
		if (answer <= word.length * 11)
			out.printLine("YES");
		else
			out.printLine("NO");
		System.err.println(answer);
	}
}
