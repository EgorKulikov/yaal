package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Cipher {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int shifts = in.readInt();
		char[] encoded = IOUtils.readCharArray(in, length + shifts - 1);
		char[] answer = new char[length];
		int current = 0;
		for (int i = 0; i < length; i++) {
			if (i >= shifts && answer[i - shifts] == '1') {
				current ^= 1;
			}
			answer[i] = (char) ('0' + (current ^ (encoded[i] - '0')));
			if (answer[i] == '1') {
				current ^= 1;
			}
		}
		out.printLine(answer);
    }
}
