package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class GeneralizedArithmeticProgressionFreeSequence {
	private int[] forbidden = new int[2000001];

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int length = in.readInt();
		int[] sequence = new int[length];
		int index = 0;
		for (int i = 1; index < length; i++) {
			if (forbidden[i] != testNumber) {
				sequence[index] = i;
				for (int j = 0; j < index; j++) {
					int candidate = a * i - b * sequence[j];
					if (candidate >= 0 && candidate <= 2000000)
						forbidden[candidate] = testNumber;
					candidate = a * sequence[j] - b * i;
					if (candidate >= 0 && candidate <= 2000000)
						forbidden[candidate] = testNumber;
				}
				int candidate = a * i - b * i;
				if (candidate >= 0 && candidate <= 2000000)
					forbidden[candidate] = testNumber;
				index++;
			}
		}
		IOUtils.printArray(sequence, out);
	}
}
