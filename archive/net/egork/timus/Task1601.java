package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1601 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		boolean sentenceStart = true;
		while (true) {
			int ch = in.read();
			if (ch == -1)
				break;
			if (Character.isLetter(ch)) {
				if (sentenceStart) {
					sentenceStart = false;
					out.print((char) Character.toUpperCase(ch));
				} else
					out.print((char) Character.toLowerCase(ch));
			} else {
				if (ch == '.' || ch == '?' || ch == '!')
					sentenceStart = true;
				out.print((char) ch);
			}
		}
	}
}

