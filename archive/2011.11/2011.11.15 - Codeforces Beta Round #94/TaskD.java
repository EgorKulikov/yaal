package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count % 2 == 1) {
			out.printLine("NO");
			return;
		}
		int[] numbers = IOUtils.readIntArray(in, count);
		Arrays.sort(numbers);
		int currentNumber = numbers[0];
		int nextRequired = 1;
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] != currentNumber && nextRequired <= 0 || numbers[i] > currentNumber + 1) {
				out.printLine("NO");
				return;
			}
			if (numbers[i] != currentNumber) {
				nextRequired = -nextRequired;
				currentNumber = numbers[i];
			}
			nextRequired++;
		}
		if (nextRequired != 0)
			out.printLine("NO");
		else
			out.printLine("YES");
	}
}
