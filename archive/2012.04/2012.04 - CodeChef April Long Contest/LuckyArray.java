package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LuckyArray {
	long[][] c = IntegerUtils.generateBinomialCoefficients(11);
	long[] p2 = IntegerUtils.generatePowers(2, 11, Long.MAX_VALUE);
	long[] p8 = IntegerUtils.generatePowers(8, 11, Long.MAX_VALUE);
	private int[] count;
	private int[] type;
	int[] answer;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int max = in.readInt();
		int index = in.readInt() - 1;
		type = IOUtils.readIntArray(in, length - 1);
		count = generateCount(max);
/*		if (max <= 1000) {
			int[] c = count.clone();
			for (int i = 1; i <= max; i++)
				c[countLucky(i)]--;
			for (int i = 0; i <= 10; i++) {
				if (c[i] != 0)
					throw new RuntimeException();
			}
		}*/
		answer = new int[length];
		if (calculate(0, 10) <= index) {
			out.printLine(-1);
			return;
		}
		go(0, index, 10);
		out.printLine(Array.wrap(answer).toArray());
	}

	private int[] generateCount(int max) {
		int[] count = new int[11];
		int ten = 1;
		int i;
		for (i = 1; ten * 10 <= max; i++) {
			ten *= 10;
		}
		int shift = 0;
		int maxC = max;
		for (i--; i >= 0; i--) {
			int curDigit = 0;
			while (max >= ten) {
				int curShift = shift + (curDigit == 4 || curDigit == 7 ? 1 : 0);
				for (int j = 0; j <= i; j++)
					count[j + curShift] += c[i][j] * p2[j] * p8[i - j];
				curDigit++;
				max -= ten;
			}
			if (curDigit == 4 || curDigit == 7)
				shift++;
			ten /= 10;
		}
		count[0]--;
		count[countLucky(maxC)]++;
		return count;
	}

	private void go(int step, int index, int last) {
		if (step == answer.length - 1) {
			if (type[step - 1] == 0)
				answer[step] = notEquals(index, last);
			else
				answer[step] = equals(index, last);
			return;
		}
		for (int i = 1; ; i++) {
			int curLucky = countLucky(i);
			if (step != 0 && (curLucky != last ^ type[step - 1] == 0))
				continue;
			int curCalculate = calculate(step + 1, curLucky);
			if (curCalculate > index) {
				answer[step] = i;
				go(step + 1, index, curLucky);
				return;
			}
			index -= curCalculate;
		}
	}

	private int notEquals(int index, int last) {
		int from = 1;
		int to = (int)1e9;
		while (from < to) {
			int middle = (from + to) / 2;
			int[] count = generateCount(middle);
			int current = 0;
			for (int i = 0; i <= 10; i++) {
				if (i != last)
					current += count[i];
			}
			if (current > index)
				to = middle;
			else
				from = middle + 1;
		}
		return from;
	}

	private int equals(int index, int last) {
		int from = 1;
		int to = (int)1e9;
		while (from < to) {
			int middle = (from + to) / 2;
			int[] count = generateCount(middle);
			int current = count[last];
			if (current > index)
				to = middle;
			else
				from = middle + 1;
		}
		return from;
	}

	private int calculate(int step, int last) {
		if (step == answer.length)
			return 1;
		long[][] result = new long[answer.length - step + 1][11];
		result[0][last] = 1;
		for (int i = 1; i <= answer.length - step; i++) {
			for (int j = 0; j <= 10; j++) {
				if (step == 0 && i == 1 || type[step + i - 2] == 0) {
					for (int k = 0; k <= 10; k++) {
						if (j != k)
							result[i][j] += result[i - 1][k] * count[j];
					}
				} else
					result[i][j] = result[i - 1][j] * count[j];
				result[i][j] = Math.min(result[i][j], Integer.MAX_VALUE);
			}
		}
		long answer = 0;
		for (long value : result[result.length - 1])
			answer += value;
		return (int) Math.min(answer, Integer.MAX_VALUE);
	}

	private int countLucky(int i) {
		int result = 0;
		while (i != 0) {
			int digit = i % 10;
			if (digit == 4 || digit == 7)
				result++;
			i /= 10;
		}
		return result;
	}
}
