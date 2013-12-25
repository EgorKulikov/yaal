package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		int answer = -1;
		for (int i = 1; i < s.length(); i++) {
			for (int j = i + 1; j < s.length(); j++) {
				String first = s.substring(0, i);
				String second = s.substring(i, j);
				String third = s.substring(j);
				if (first.charAt(0) == '0' && first.length() != 1 || second.charAt(0) == '0' && second.length() != 1 || third.charAt(0) == '0' && third.length() != 1)
					continue;
				if (first.length() > 7 || second.length() > 7 || third.length() > 7)
					continue;
				int ff = Integer.parseInt(first);
				int ss = Integer.parseInt(second);
				int tt = Integer.parseInt(third);
				if (ff > 1000000 || ss > 1000000 || tt > 1000000)
					continue;
				answer = Math.max(answer, ff + ss + tt);
			}
		}
		out.printLine(answer);
	}
}
