package on2013_07.on2013_07_17_ABBYY_Cup_Final.TaskC;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	long answer = 0;
	private long[][][] result;
	private int[][][] digit;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long current = in.readLong();
		int curLast = (int) (current % 10);
		long tens = current / 10;
		result = new long[20][10][10];
		digit = new int[20][10][10];
		long[] ten = IntegerUtils.generatePowers(10, 19, Long.MAX_VALUE);
		ArrayUtils.fill(result, -1);
		while (tens > 0) {
			int lengthNines = 0;
			long copy = tens;
			while (copy % 10 == 9) {
				lengthNines++;
				copy /= 10;
			}
			int max = 0;
			while (copy != 0) {
				max = (int) Math.max(max, copy % 10);
				copy /= 10;
			}
			go(lengthNines, max, curLast);
			answer += result[lengthNines][max][curLast];
			curLast = digit[lengthNines][max][curLast];
			tens -= ten[lengthNines];
		}
		if (tens < 0)
			answer--;
		else if (curLast != 0)
			answer++;
		out.printLine(answer);
    }

	private void go(int nines, int max, int digit) {
		if (result[nines][max][digit] != -1)
			return;
		if (nines == 0) {
			if (digit >= max) {
				result[nines][max][digit] = 2;
				this.digit[nines][max][digit] = 10 - max;
			} else {
				result[nines][max][digit] = 1;
				this.digit[nines][max][digit] = 10 - (max - digit);
			}
			return;
		}
		result[nines][max][digit] = 0;
		this.digit[nines][max][digit] = digit;
		for (int i = 9; i >= 0; i--) {
			go(nines - 1, Math.max(i, max), this.digit[nines][max][digit]);
			result[nines][max][digit] += result[nines - 1][Math.max(i, max)][this.digit[nines][max][digit]];
			this.digit[nines][max][digit] = this.digit[nines - 1][Math.max(i, max)][this.digit[nines][max][digit]];
		}
	}
}
