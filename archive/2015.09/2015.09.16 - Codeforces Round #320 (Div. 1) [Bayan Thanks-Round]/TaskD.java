package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		char[] s = IOUtils.readCharArray(in, n);
		long answer = s.length * (m - 1);
		int valid = 0;
		for (int i = 0; i < s.length; i++) {
			if (i != 0 && s[i] != s[i - 1]) {
				valid++;
			}
			answer += valid * (m - 1);
		}
		valid = 0;
		for (int i = s.length - 1; i >= 0; i--) {
			if (i != s.length - 1 && s[i + 1] != s[i]) {
				valid++;
			}
			answer += valid * (m - 1);
		}
		for (int i = 1; i < s.length; i++) {
			if (i != 1 && s[i] == s[i - 2]) {
				continue;
			}
			if (s[i] == s[i - 1]) {
				continue;
			}
			int start = i - 1;
			int end = i;
			for (int j = i + 1; j < s.length; j++) {
				if (s[j] != s[j - 2]) {
					break;
				}
				end = j;
			}
			long length = end - start;
			answer -= length * (length + 1) / 2;
		}
//		for (int i = 1; i < s.length; i++) {
//			if (s[i] != s[i - 1]) {
//				answer--;
//			}
//		}
		out.printLine(answer);
	}
}
