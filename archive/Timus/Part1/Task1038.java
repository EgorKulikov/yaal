package Timus.Part1;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Task1038 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int result = 0;
		boolean sentenceStart = true;
		boolean wordStart = true;
		try {
			//noinspection InfiniteLoopStatement
			while (true) {
				int c = in.read();
				if (c == '.' || c == '!' || c == '?') {
					sentenceStart = wordStart = true;
				} else if (Character.isLetter(c)) {
					if (sentenceStart && !Character.isUpperCase(c))
						result++;
					else if (!wordStart && Character.isUpperCase(c))
						result++;
					sentenceStart = wordStart = false;
				} else
					wordStart = true;
			}
		} catch (InputMismatchException e) {
			out.println(result);
		}
	}
}

