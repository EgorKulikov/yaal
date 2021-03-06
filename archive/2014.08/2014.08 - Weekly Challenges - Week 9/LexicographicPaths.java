package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class LexicographicPaths {
	long[][] c = IntegerUtils.generateBinomialCoefficients(21);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		int index = in.readInt();
		StringBuilder answer = new StringBuilder(x + y);
		while (x > 0 || y > 0) {
			if (x != 0 && index < c[x - 1 + y][y]) {
				x--;
				answer.append('H');
			} else {
				if (x != 0) {
					index -= c[x - 1 + y][y];
				}
				y--;
				answer.append('V');
			}
		}
		out.printLine(answer);
    }
}
