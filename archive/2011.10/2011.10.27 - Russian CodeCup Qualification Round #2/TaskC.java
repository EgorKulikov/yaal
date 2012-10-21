package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int length = in.readInt();
		int module = in.readInt();
		String first = in.readString();
		String second = in.readString();
		int answer = go(first, second, length, module) + go(second, first, length, module);
		if (first.startsWith(second) && first.endsWith(second))
			answer -= go(first, first, length, module);
		if (!first.equals(second) && second.startsWith(first) && second.endsWith(first))
			answer -= go(second, second, length, module);
		answer += module;
		answer %= module;
		out.println(answer);
	}

	private int go(String prefix, String suffix, int length, int module) {
		if (length >= prefix.length() + suffix.length())
			return (int) IntegerUtils.power(26, length - prefix.length() - suffix.length(), module);
		if (suffix.length() > length || prefix.length() > length)
			return 0;
		if (suffix.startsWith(prefix.substring(length - suffix.length())))
			return 1;
		return 0;
	}
}
