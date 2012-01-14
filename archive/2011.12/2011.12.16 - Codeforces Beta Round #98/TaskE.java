package net.egork;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int[] farthest = new int[4 * s.length + 1];
		Arrays.fill(farthest, -1);
		int balance = 2 * s.length;
		farthest[balance] = 0;
		for (int i = 0; i < s.length; i++) {
			if (MiscUtils.isStrictVowel(s[i]))
				balance--;
			else
				balance += 2;
			farthest[balance] = i + 1;
		}
		for (int i = farthest.length - 2; i >= 0; i--)
			farthest[i] = Math.max(farthest[i], farthest[i + 1]);
		int maxLength = 0;
		int count = 0;
		balance = 2 * s.length;
		for (int i = 0; i < s.length; i++) {
			int curLength = farthest[balance] - i;
			if (curLength > maxLength) {
				maxLength = curLength;
				count = 1;
			} else if (curLength == maxLength)
				count++;
			if (MiscUtils.isStrictVowel(s[i]))
				balance--;
			else
				balance += 2;
		}
		if (maxLength == 0)
			out.printLine("No solution");
		else
			out.printLine(maxLength, count);
	}
}
