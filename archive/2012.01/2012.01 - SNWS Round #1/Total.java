package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Total {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int stepCount = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		numbers = Arrays.copyOf(numbers, count + 6);
		for (int i = 0; i < 6; i++)
			numbers[i + count] = numbers[i];
		int[] answer = new int[count + 6];
		int[] next = new int[count + 6];
		for (int i = 0; i <= stepCount; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 1; k <= 6; k++)
					next[j] = Math.max(next[j], answer[j + k] + numbers[j]);
			}
			int[] temp = next;
			next = answer;
			answer = temp;
			for (int j = 0; j < 6; j++)
				answer[j + count] = answer[j];
		}
		out.printLine(answer[0]);
	}
}
