package on2012_11.on2012_11_06_November_Challenge_2012.Lucky_Balance;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LuckyBalance {
	public static final int MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] number = in.readString().toCharArray();
		int count4 = 0;
		int count7 = 0;
		for (char c : number) {
			if (c == '4')
				count4++;
			else
				count7++;
		}
		long answer = 0;
		long[] c1 = IntegerUtils.generateBinomialRow(count4 - 1, MOD);
		long[] c2 = IntegerUtils.generateBinomialRow(count7, MOD);
		for (int i = 0; i < count4 && i <= count7; i++)
			answer += c1[i] * c2[i] % MOD;
		c1 = IntegerUtils.generateBinomialRow(count4, MOD);
		c2 = IntegerUtils.generateBinomialRow(count7 - 1, MOD);
		for (int i = 0; i <= count4 && i < count7; i++)
			answer += c1[i] * c2[i] % MOD;
		c1 = IntegerUtils.generateBinomialRow(count4 - 1, MOD);
		c2 = IntegerUtils.generateBinomialRow(count7 - 1, MOD);
		for (int i = 0; i < count4 && i < count7; i++)
			answer -= c1[i] * c2[i] % MOD;
		answer %= MOD;
		answer += MOD;
		answer %= MOD;
		out.printLine(answer);
	}
}
