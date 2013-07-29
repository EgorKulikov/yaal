package on2013_06.on2013_06_23_Codeforces_Round__189.A___Malek_Dance_Club;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] x = in.readString().toCharArray();
		int length = x.length;
		long[] power = IntegerUtils.generatePowers(2, 2 * length, MOD);
		long answer = 0;
		for (int i = 0; i < length; i++) {
			if (x[i] == '1')
				answer += power[2 * length - i - 2];
		}
		out.printLine(answer % MOD);
    }
}
