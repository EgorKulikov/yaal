package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int threshold = in.readInt();
		for (int i = 0; i < s.length; i++) {
			if (Character.toLowerCase(s[i]) < 97 + threshold)
				s[i] = Character.toUpperCase(s[i]);
			else
				s[i] = Character.toLowerCase(s[i]);
		}
		out.printLine(s);
    }
}
