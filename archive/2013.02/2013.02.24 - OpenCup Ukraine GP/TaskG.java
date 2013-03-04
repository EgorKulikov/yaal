package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskG {
	static int[] result, next;
	static int[][] shift;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int steps = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		Arrays.sort(numbers);
		shift = new int[count][];
		for (int i = 0; i < count; i++) {
			shift[i] = new int[i + 1];
			int middle = i;
			int total = 0;
			for (int j = i - 1; j >= 0; j--) {
				total += (middle - j) * (numbers[j + 1] - numbers[j]);
				while (numbers[middle] - numbers[j] > numbers[i] - numbers[middle]) {
					total += numbers[i] - numbers[middle];
					total -= numbers[middle] - numbers[j];
					middle--;
				}
				shift[i][j] = total;
			}
		}
		result = new int[count];
		next = new int[count];
		int sum = 0;
		for (int i = 1; i < count; i++) {
			sum += i * (numbers[i] - numbers[i - 1]);
			result[i] = sum;
		}
		for (int i = 1; i < steps; i++) {
			for (int j = 0; j <= i; j++)
				next[j] = 0;
			int pos = -1;
			next[count - 1] = Integer.MAX_VALUE;
			int[] sh = shift[count - 1];
			for (int j = 0; j < count; j++) {
				int value = result[j] + sh[j];
				if (value < next[count - 1]) {
					next[count - 1] = value;
					pos = j;
				}
			}
			update(i, count - 1, i - 1, pos);
			int[] temp = result;
			result = next;
			next = temp;
		}
		sum = 0;
		for (int i = count - 2; i >= 0; i--) {
			sum += (count - 1 - i) * (numbers[i + 1] - numbers[i]);
			result[i] += sum;
		}
		int answer = Integer.MAX_VALUE;
		for (int i : result)
			answer = Math.min(answer, i);
		out.printLine(answer);
	}

	private void update(int from, int to, int left, int right) {
		int middle = (from + to) >> 1;
		if (middle == from)
			return;
		int pos = -1;
		int nm = Integer.MAX_VALUE;
		int[] sh = shift[middle];
		int upTo = Math.min(right, middle);
		for (int i = left; i <= upTo; i++) {
			int value = result[i] + sh[i];
			if (value < nm) {
				nm = value;
				pos = i;
			}
		}
		next[middle] = nm;
		update(from, middle, left, pos);
		update(middle, to, pos, right);
	}
}
