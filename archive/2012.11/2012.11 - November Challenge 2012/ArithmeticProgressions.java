package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.FastFourierTransform;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ArithmeticProgressions {
	public static final int STEP = 1000;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(numbers);
		long[] countLeft = new long[30000];
		long[] countRight = new long[30000];
		for (int i : numbers)
			countRight[i]++;
		long result = 0;
		long[] local = new long[count];
		for (int i = 0; i < count; i += STEP) {
			for (int j = i; j < i + STEP && j < count; j++)
				countRight[numbers[j]]--;
			long[] answer = FastFourierTransform.multiply(countLeft, countRight);
			for (int j = i; j < i + STEP && j < count; j++) {
				local[j] = answer[2 * numbers[j]];
				for (int k = i; k < j; k++) {
					int right = 2 * numbers[j] - numbers[k];
					if (right >= 0 && right < 30000)
						local[j] += countRight[right];
				}
				for (int k = j + 1; k < i + STEP && k < count; k++) {
					int left = 2 * numbers[j] - numbers[k];
					if (left >= 0 && left < 30000)
						local[j] += countLeft[left];
				}
				countLeft[numbers[j]]++;
				result += local[j];
			}
		}
		out.printLine(result);
	}
}
