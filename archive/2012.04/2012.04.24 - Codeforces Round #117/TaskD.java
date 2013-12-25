package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String first = in.readString();
		String second = in.readString();
		if (first.length() > second.length()) {
			String temp = first;
			first = second;
			second = temp;
		}
		int[] z1 = StringUtils.zAlgorithm(first);
		int[] z2 = StringUtils.zAlgorithm(second);
		int[] z = StringUtils.zAlgorithm(first + "$" + second);
		int answer = 0;
		for (int i = 1; i < z1.length; i++) {
			if (z1.length % i == 0 && z2.length % i == 0 && z1[i] == z1.length - i && z2[i] == z2.length - i && z[z1.length + 1] >= i)
				answer++;
		}
		if (z[z1.length + 1] >= z1.length && (z1.length == z2.length || z2.length % z1.length == 0 && z2[z1.length] == z2.length - z1.length))
			answer++;
		out.printLine(answer);
	}
}
