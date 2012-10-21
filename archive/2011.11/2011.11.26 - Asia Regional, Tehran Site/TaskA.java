package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	private long[][] c = IntegerUtils.generateBinomialCoefficients(10);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readCharacter() - '0';
		char[] code = in.readString().toCharArray();
		if (count == 1) {
			out.printLine(9);
			return;
		}
		long[] numbers = new long[count];
		long answer = 0;
		for (int i = 0; i < (1 << (code.length - 1)); i++) {
			if (Integer.bitCount(i) != count - 1)
				continue;
			Arrays.fill(numbers, 0);
			int index = 0;
			for (int j = 0; j < code.length; j++) {
				numbers[index] *= 10;
				numbers[index] += code[j] - '0';
				if ((i >> j & 1) == 1)
					index++;
			}
			boolean good = true;
			for (int j = 1; j < count && good; j++) {
				if (numbers[j - 1] > numbers[j])
					good = false;
			}
			if (good) {
				int remaining = 9;
				int current = 1;
				long currentAnswer = 1;
				for (int j = 1; j < count; j++) {
					if (numbers[j] != numbers[j - 1]) {
						currentAnswer *= c[remaining][current];
						remaining -= current;
						current = 1;
					} else
						current++;
				}
				currentAnswer *= c[remaining][current];
				answer += currentAnswer;
			}
		}
		out.printLine(answer);
	}
}
