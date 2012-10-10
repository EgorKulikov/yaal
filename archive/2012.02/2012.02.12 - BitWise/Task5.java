package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task5 {
	static final int MAX = 1000000;
//	static final int[] mu = new int[MAX + 1];
//	static final int[][] result = new int[5001][5001];
	static final int[] sum = new int[MAX + 1];
	static final int[] f = new int[MAX + 1];

	static {
		int[] divisor = IntegerUtils.generateDivisorTable(MAX + 1);
		f[1] = 1;
		for (int i = 2; i <= MAX; i++) {
			int j = i / divisor[i];
			if (j % divisor[i] != 0)
				f[i] = -f[j] * (divisor[i] * divisor[i] - 1);
			else {
				j /= divisor[i];
				if (j % divisor[i] != 0)
					f[i] = -f[j] * divisor[i] * divisor[i];
			}
		}
		for (int i = 1; i <= MAX; i++)
			sum[i] = sum[i - 1] + i;
//		ArrayUtils.fill(result, -1);
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		int result = 0;
		int min = Math.min(x, y);
		for (int i = 1; i <= min; i++) {
			result += f[i] * sum[x / i] * sum[y / i];
//			if (mu[i] != 0)
//				result += f(x / i, y / i);
		}
		result &= (1 << 30) - 1;
		out.printLine(result);
	}

//	private int f(int x, int y) {
//		if (x <= 5000 && y <= 5000 && result[x][y] != -1)
//			return result[x][y];
//		int min = Math.min(x, y);
//		int answer = 0;
//		for (int i = 1; i <= min; i++)
//			answer += i * i * mu[i] * sum[x / i] * sum[y / i];
//		if (x <= 5000 && y <= 5000)
//			result[x][y] = result[y][x] = answer;
//		return answer;
//	}
}
