import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.math.BigInteger;

public class Billboards implements Solver {
	private static final int MOD = 1000000007;
	private static final BigInteger BI_MOD = BigInteger.valueOf(MOD);

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int billboardCount = in.readInt();
		int consecutive = in.readInt();
		int mandatory = in.readInt();
		int bad = billboardCount % consecutive;
		int good = consecutive - bad;
		long answer;
		if (good >= mandatory)
			answer = count(billboardCount / consecutive, good, mandatory);
		else
			answer = count(billboardCount / consecutive + 1, bad, mandatory - good);
		out.println(answer);
	}

	private long count(int segmentCount, int slotsPerSegment, int checkedSlots) {
		int a = checkedSlots;
		int b = slotsPerSegment - a;
		int c = segmentCount;
		long result = 1;
		for (int i = 0; i < a; i++) {
			result = (result * c(b + i, b + c + a - 1)) % MOD;
			result = result * reverse(c(i, b + c + a - 1)) % MOD;
		}
		return result;
	}

	private long c(int k, int n) {
		long result = 1;
		for (int i = n - k + 1; i <= n; i++)
			result = result * i % MOD;
		for (int i = 1; i <= k; i++)
			result = result * reverse(i) % MOD;
		return result;
	}

	private long reverse(long n) {
		return BigInteger.valueOf(n).modInverse(BI_MOD).longValue();
	}
}

