package on2012_12.on2012_12_22_Codeforces_Round__157.C___Little_Elephant_and_LCM;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		Arrays.sort(numbers);
		for (int i = 0; i < count; i++)
			numbers[i] *= 2;
		int[] divisors = IntegerUtils.generateDivisorTable(100001);
		int[][] allDivisors = new int[100001][];
		allDivisors[1] = new int[]{1};
		for (int i = 2; i <= 100000; i++) {
			int ii = i;
			int power = 0;
			while (ii % divisors[i] == 0) {
				power++;
				ii /= divisors[i];
			}
			allDivisors[i] = new int[allDivisors[ii].length * (power + 1)];
			System.arraycopy(allDivisors[ii], 0, allDivisors[i], 0, allDivisors[ii].length);
			for (int j = allDivisors[ii].length; j < allDivisors[i].length; j++)
				allDivisors[i][j] = divisors[i] * allDivisors[i][j - allDivisors[ii].length];
			Arrays.sort(allDivisors[i]);
		}
		long answer = 0;
		for (int i = 1; i <= 100000; i++) {
			long total = 1;
			int less = 0;
			int k = 0;
			for (int j : allDivisors[i]) {
				int current = -Arrays.binarySearch(numbers, 2 * j - 1) - 1;
				total = (total * IntegerUtils.power(k, current - less, MOD)) % MOD;
				less = current;
				k++;
			}
			if (less == count)
				continue;
			total = total * (IntegerUtils.power(k, count - less, MOD) - IntegerUtils.power(k - 1, count - less, MOD)) % MOD;
			answer += total;
		}
		answer %= MOD;
		answer += MOD;
		answer %= MOD;
		out.printLine(answer % MOD);
	}
}
