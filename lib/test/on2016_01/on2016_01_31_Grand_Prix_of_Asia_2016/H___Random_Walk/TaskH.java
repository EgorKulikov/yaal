package on2016_01.on2016_01_31_Grand_Prix_of_Asia_2016.H___Random_Walk;



import com.sun.org.apache.bcel.internal.generic.IUSHR;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskH {
	long[] pow;

	int mod;

	long[][] c;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		mod = in.readInt();
		pow = IntegerUtils.generatePowers(4, n + 1, mod);
		long answer = (n + 1) * pow[n] % mod;
		long[] dp = new long[n + 1];
		c = IntegerUtils.generateBinomialCoefficients(n + 1, mod);
		for (int k = 2; k <= n; k += 2) {
			dp[k] = c[k][k / 2];
			dp[k] *= dp[k];
			dp[k] %= mod;
			for (int i = 2; i < k; i += 2) {
				long cc = c[k - i][(k - i) / 2];
				cc *= cc;
				cc %= mod;
				dp[k] -= dp[i] * cc % mod;
			}
			dp[k] %= mod;
			if (dp[k] < 0) {
				dp[k] += mod;
			}
			answer -= dp[k] * pow[n - k] % mod * (n - k + 1) % mod;
		}
		answer %= mod;
		if (answer < 0) {
			answer += mod;
		}
		out.printLine(answer);
	}
}
