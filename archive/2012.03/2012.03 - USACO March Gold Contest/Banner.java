package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Banner {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		long low = in.readInt();
		long high = in.readInt();
		int modulo = in.readInt();
		long answer = 0;
		if (low == 1)
			answer += ((columnCount - rowCount) % modulo + modulo) % modulo;
		int[] divisor = IntegerUtils.generateDivisorTable(rowCount + 1);
		int[] count = new int[rowCount + 1];
		for (int i = 2; i <= rowCount; i++) {
			int current = i;
			while (current % divisor[i] == 0)
				current /= divisor[i];
			count[i] = count[current] + 1;
		}
		int[][] primeDivisors = new int[rowCount + 1][];
		primeDivisors[1] = new int[0];
		for (int i = 2; i <= rowCount; i++) {
			int current = i;
			while (current % divisor[i] == 0)
				current /= divisor[i];
			primeDivisors[i] = Arrays.copyOf(primeDivisors[current], count[i]);
			primeDivisors[i][count[i] - 1] = divisor[i];
		}
		long[] squares = new long[columnCount + 1];
		for (int j = 0; j <= columnCount; j++)
			squares[j] = (long)j * j;
		for (int i = 1; i <= rowCount; i++) {
			int from = Arrays.binarySearch(squares, low * low - (long)i * i);
			if (from < 0)
				from = -from - 1;
			int to = Arrays.binarySearch(squares, high * high - (long)i * i);
			if (to < 0)
				to = -to - 2;
			if (from > to)
				continue;
			int length = 1 << count[i];
			long current = 0;
			for (int j = 0; j < length; j++) {
				int curDivisor = 1;
				int sign = 1;
				for (int k = 0; k < count[i]; k++) {
					if ((j >> k & 1) == 1) {
						curDivisor *= primeDivisors[i][k];
						sign = -sign;
					}
				}
				int actualFrom = from + (curDivisor - from % curDivisor) % curDivisor;
				int actualTo = to - to % curDivisor;
				if (actualFrom > actualTo)
					continue;
				current += sign * (long)(2 * columnCount + 2 - actualFrom - actualTo) * ((actualTo - actualFrom + curDivisor) / curDivisor) % modulo;
			}
			answer += current * (rowCount - i + 1) % modulo;
		}
		answer %= modulo;
		out.printLine(answer);
	}
}
