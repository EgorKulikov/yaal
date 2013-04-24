package on2013_03.on2013_03_08_Prayaas__Algorithm_Intensive_Programming_Contest_.ToggleThemAll;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ToggleThemAll {
	static final long MOD = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readLong();
		long answer = (IntegerUtils.power(4, (count + 1) / 2, MOD) - 1) * IntegerUtils.reverse(3, MOD) % MOD;
		if ((count & 1) == 0) {
			answer *= 2;
			answer %= MOD;
		}
		out.printLine(answer);
    }
}
