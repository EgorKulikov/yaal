package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CielAndNewMenu {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		for (int i = 0; i < s.length; i++)
			s[i] -= '0';
		int nonZero = 0;
		long answer = 0;
		if ((s[0] & 7) == 0)
			answer++;
		if (s.length > 1) {
			if (s[0] != 0 && ((10 * s[0] + s[1]) & 7) == 0)
				answer++;
			if ((s[1] & 7) == 0)
				answer++;
		}
		for (int i = 0; i < s.length - 2; i++) {
			if ((s[i + 2] & 7) == 0)
				answer++;
			if (s[i + 1] != 0 && (((s[i + 1] * 10 + s[i + 2]) & 7) == 0))
				answer++;
			if (s[i] != 0)
				nonZero++;
			if (((s[i] * 100 + s[i + 1] * 10 + s[i + 2]) & 7) == 0)
				answer += nonZero;
		}
		out.printLine(answer);
	}
}
