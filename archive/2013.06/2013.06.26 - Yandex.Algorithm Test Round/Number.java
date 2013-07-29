package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Number {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] number = in.readString().toCharArray();
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 256; i++) {
			if (Integer.bitCount(i) != 4)
				continue;
			int current = 0;
			for (int j = 0; j < 8; j++) {
				if ((i >> j & 1) == 1) {
					current *= 10;
					current += number[j] - '0';
				}
			}
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
    }
}
