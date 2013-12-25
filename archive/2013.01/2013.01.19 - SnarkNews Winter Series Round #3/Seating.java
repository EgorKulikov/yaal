package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Seating {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String first = in.readString();
		String second = in.readString();
		int count = first.length();
		if (second.length() != count) {
			out.printLine("NO");
			return;
		}
		if (check(first, second) || check(first, StringUtils.reverse(second)))
			out.printLine("YES");
		else
			out.printLine("NO");
    }

	private boolean check(String first, String second) {
		String s1 = first + second;
		String s2 = second + first;
		int[] z1 = StringUtils.zAlgorithm(s1);
		int count = first.length();
		if (z1[count] == count)
			return true;
		int[] z2 = StringUtils.zAlgorithm(s2);
		for (int i = 1, j = count - 1; i < count; i++, j--) {
			if (z1[i + count] == j && z2[j + count] == i)
				return true;
		}
		return false;
	}
}
