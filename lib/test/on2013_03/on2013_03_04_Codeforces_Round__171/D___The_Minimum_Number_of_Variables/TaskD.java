package on2013_03.on2013_03_04_Codeforces_Round__171.D___The_Minimum_Number_of_Variables;


import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		boolean[] possible = new boolean[1 << count];
		boolean[] nextPossible = new boolean[1 << count];
		possible[1] = true;
		int[][] index = new int[count][count];
		ArrayUtils.fill(index, -1);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < i; k++) {
					if (numbers[j] + numbers[k] == numbers[i])
						index[i][j] = k;
				}
			}
		}
		for (int i = 1; i < count; i++) {
			Arrays.fill(nextPossible, 0, 1 << (i - 1), false);
			for (int j = 0; j < (1 << i); j++) {
				if (possible[j]) {
					boolean good = false;
					for (int k = 0; k < i && !good; k++) {
						if ((j >> k & 1) != 0 && index[i][k] != -1 && (j >> index[i][k] & 1) != 0)
							good = true;
					}
					if (good) {
						nextPossible[j + (1 << i)] = true;
						for (int k = 0; k < i; k++) {
							if ((j >> k & 1) == 1)
								nextPossible[j + (1 << i) - (1 << k)] = true;
						}
					}
				}
			}
			boolean[] temp = possible;
			possible = nextPossible;
			nextPossible = temp;
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < (1 << count); i++) {
			if (possible[i])
				answer = Math.min(answer, Integer.bitCount(i));
		}
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		out.printLine(answer);
    }
}
