package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class OneXLIS {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		if (count == 1) {
			out.printLine(1);
			return;
		}
		int[] direct = calculate(numbers);
		if (direct[count - 1] == count) {
			out.printLine(0);
			return;
		}
		ArrayUtils.reverse(numbers);
		for (int i = 0; i < count; i++)
			numbers[i] = -numbers[i];
		int[] reverse = calculate(numbers);
		int answer = 0;
		for (int i = 0; i < count - 1; i++)
			answer = Math.max(answer, direct[i] + reverse[count - 2 - i]);
		out.printLine(answer);
    }

	private int[] calculate(int[] numbers) {
		int[] direct = new int[numbers.length];
		int size = 0;
		int[] answer = new int[numbers.length];
		int j = 0;
		for (int i : numbers) {
			int position = -Arrays.binarySearch(direct, 0, size, 2 * i + 1) - 1;
			direct[position] = 2 * i;
			if (position == size)
				size++;
			answer[j++] = size;
		}
		return answer;
	}
}
