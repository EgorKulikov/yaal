package net.egork.timus;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Task1226 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		StringBuilder word = new StringBuilder();
		try {
			//noinspection InfiniteLoopStatement
			while (true) {
				char c = (char) in.read();
				if (c == Character.MAX_VALUE)
					break;
				if (Character.isLetter(c))
					word.append(c);
				else {
					if (word.length() > 0) {
						out.print(word.reverse());
						word.delete(0, word.length());
					}
					out.print(c);
				}
			}
		} catch (InputMismatchException ignored) {
		}
		out.print(word.reverse());
	}
}

