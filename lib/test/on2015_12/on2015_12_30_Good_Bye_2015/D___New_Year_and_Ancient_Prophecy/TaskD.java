package on2015_12.on2015_12_30_Good_Bye_2015.D___New_Year_and_Ancient_Prophecy;



import net.egork.io.IOUtils;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		char[] digits = IOUtils.readCharArray(in, n);
		StringHash hash = new SimpleStringHash(new String(digits));
		int[] array = StringUtils.suffixArray(new String(digits));
		long[][] answer = new long[n][n + 1];
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] == '0') {
				continue;
			}
			answer[i][n - i] = 1;
			for (int j = n - i - 1; j >= 1; j--) {
				answer[i][j] = answer[i][j + 1];
				if (i + 2 * j > n) {
					continue;
				}
				if (hash.hash(i, i + j) != hash.hash(i + j, i + 2 * j) && array[i] < array[i + j]) {
					answer[i][j] += answer[i + j][j];
				} else {
					answer[i][j] += answer[i + j][j + 1];
				}
				answer[i][j] %= MOD;
			}
		}
		out.printLine(answer[0][1]);
	}
}
