package net.egork;

import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		String s = in.readString();
		if (s.length() == 1) {
			out.printLine("Case " + testNumber + ": 1");
			return;
		}
		int length = s.length();
		if (s.charAt(0) != s.charAt(length - 1)) {
			boolean circle = true;
			for (int i = 1; i < length; i++) {
				if (s.charAt(i) == s.charAt(i - 1)) {
					circle = false;
					s = s.substring(i) + s.substring(0, i);
					break;
				}
			}
			if (circle) {
				s += s;
				boolean[] answer = new boolean[length];
				answer[0] = true;
				StringHash hash = new SimpleStringHash(s);
				long baseHash = hash.hash(0, length);
				for (int i = 1; i < length - 1; i++)
					answer[i] = baseHash != hash.hash(i + 1, i + 1 + length);
				answer[length - 1] = true;
				out.print("Case " + testNumber + ": ");
				for (boolean b : answer)
					out.print(b ? 1 : 0);
				out.printLine();
				return;
			}
		}
		boolean[] answer = new boolean[length];
		answer[length - 1] = true;
		int start = 0;
		StringHash hash = new SimpleStringHash(s);
		for (int i = 1; i <= length; i++) {
			if (i == length || s.charAt(i) == s.charAt(i - 1)) {
				int curLength = i - start;
				for (int j = 1; j < curLength; j++) {
					if (!answer[length - j - 1])
						answer[length - j - 1] = hash.hash(start, start + curLength - j) != hash.hash(start + j, i);
				}
				start = i;
			}
		}
		out.print("Case " + testNumber + ": ");
		for (boolean b : answer)
			out.print(b ? 1 : 0);
		out.printLine();
    }
}
