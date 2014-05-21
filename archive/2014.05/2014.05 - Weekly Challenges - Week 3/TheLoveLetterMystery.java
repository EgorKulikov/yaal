package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheLoveLetterMystery {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] word = in.readString().toCharArray();
		int answer = 0;
		for (int i = 0, j = word.length - 1; i < j; i++, j--)
			answer += Math.abs((int)word[i] - (int)word[j]);
		out.printLine(answer);
    }
}
