package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private int[] count;
	private int[][] digits;
	private int max;
	int curNumber;
	int[] table = new int[10000000];
	byte[] digit = new byte[10000000];

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		for (int i = 0; i < 10000000; i++) {
			table[i] = i / 10;
			digit[i] = (byte) (i % 10);
		}
		count = IOUtils.readIntArray(in, 10);
		digits = new int[10][4];
		for (int i = 0; i < 10000; i++) {
			curNumber = i;
			if (i == 0)
				max = 9;
			else
				max = Math.min(9, 9999 / i);
			for (int j = 0; j <= max; j++) {
				int number = i * j;
				for (int k = 0; k < 4; k++) {
					digits[j][k] = number % 10;
					number /= 10;
				}
			}
			boolean good = true;
			for (int j = 0; j < 4; j++) {
				int current = digits[1][j];
				count[current]--;
				if (count[current] < 0)
					good = false;
			}
			if (good) {
				int result = go(0, 0, 0, 1);
				if (result != -1) {
					out.printLine(i);
					out.printLine(result);
					return;
				}
			}
			for (int j = 0; j < 4; j++) {
				int current = digits[1][j];
				count[current]++;
			}
		}
		out.printLine(-1);
		out.printLine(-1);
	}

	private int go(int step, int number, int sum, int multiplier) {
		if (step == 4) {
			if (sum >= 10000000)
				return -1;
			int copy = sum;
			boolean good = true;
			for (int i = 0; i < 7; i++) {
				int current = digit[copy];
				count[current]--;
				if (count[current] < 0)
					good = false;
				copy = table[copy];
			}
			if (good)
				return number;
			copy = sum;
			for (int i = 0; i < 7; i++) {
				int current = digit[copy];
				count[current]++;
				copy = table[copy];
			}
			return -1;
		}
		for (int i = 0; i <= max; i++) {
			if (count[i] == 0)
				continue;
			count[i]--;
			boolean good = true;
			for (int j = 0; j < 4; j++) {
				int current = digits[i][j];
				count[current]--;
				if (count[current] < 0)
					good = false;
			}
			if (good) {
				int result = go(step + 1, number + multiplier * i, sum + multiplier * i * curNumber, multiplier * 10);
				if (result != -1)
					return result;
			}
			count[i]++;
			for (int j = 0; j < 4; j++) {
				int current = digits[i][j];
				count[current]++;
			}
		}
		return -1;
	}
}
