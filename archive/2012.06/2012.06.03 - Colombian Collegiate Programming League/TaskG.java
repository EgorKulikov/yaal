package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String word = in.readString();
		String s = word + '#' + StringUtils.reverse(word);
		int[] z = StringUtils.zAlgorithm(s);
		String answer = "";
		for (int i = word.length() + 1; i < z.length; i++) {
			if (z[i] > answer.length())
				answer = word.substring(0, z[i]);
		}
		out.printLine(StringUtils.reverse(answer));
	}
}
