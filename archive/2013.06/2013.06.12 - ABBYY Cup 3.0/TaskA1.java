package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] mask = in.readString().toCharArray();
		int was = 0;
		long answer = 1;
		int zeroes = 0;
		boolean first = true;
		for (char c : mask) {
			if (c == '?') {
				if (first)
					answer *= 9;
				else
					zeroes++;
			} else if (Character.isLetter(c)) {
				int i = c - 'A';
				if (first)
					answer *= 9;
				else if ((was >> i & 1) == 0)
					answer *= 10 - Integer.bitCount(was);
				was |= 1 << i;
			}
			first = false;
		}
		out.print(answer);
		for (int i = 0; i < zeroes; i++)
			out.print(0);
		out.printLine();
    }
}
