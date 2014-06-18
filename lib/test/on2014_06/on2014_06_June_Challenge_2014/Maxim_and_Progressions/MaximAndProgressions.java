package on2014_06.on2014_06_June_Challenge_2014.Maxim_and_Progressions;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MaximAndProgressions {
	private static final int MOD = (int) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[][] two = new int[101][101];
		int[] one = new int[101];
		for (int i : array) {
			for (int j = 1; j <= 100; j++) {
				int last = 2 * j - i;
				if (last <= 100 && last >= 1) {
					two[i][j] += two[j][last];
					if (two[i][j] >= MOD)
						two[i][j] -= MOD;
				}
				two[i][j] += one[j];
				if (two[i][j] >= MOD)
					two[i][j] -= MOD;
			}
			one[i]++;
		}
		long answer = 0;
		for (int[] row : two)
			answer += ArrayUtils.sumArray(row);
		answer += ArrayUtils.sumArray(one) + 1;
		answer %= MOD;
		answer = IntegerUtils.power(2, count, MOD) - answer;
		if (answer < 0)
			answer += MOD;
		out.printLine(answer);
    }
}
