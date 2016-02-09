package on2016_01.on2016_01_30_World_CodeSprint.Alien_Flowers;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AlienFlowers {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rr = in.readInt();
		int rb = in.readInt();
		int bb = in.readInt();
		int br = in.readInt();
		if (Math.abs(rb - br) > 1) {
			out.printLine(0);
			return;
		}
		if (rb == 0 && br == 0) {
			if (rr != 0 && bb != 0) {
				out.printLine(0);
			} else if (rr != 0 || bb != 0) {
				out.printLine(1);
			} else {
				out.printLine(2);
			}
			return;
		}
		long answer = 0;
		if (rb != br) {
			int max = Math.max(rb, br);
			answer = IntegerUtils.binomialCoefficient(rr + max - 1, rr, MOD) * IntegerUtils.binomialCoefficient(bb + max - 1, bb, MOD) % MOD;
		} else {
			answer += IntegerUtils.binomialCoefficient(rr + rb - 1, rr, MOD) * IntegerUtils.binomialCoefficient(bb + rb, bb, MOD) % MOD;
			answer += IntegerUtils.binomialCoefficient(rr + rb, rr, MOD) * IntegerUtils.binomialCoefficient(bb + rb - 1, bb, MOD) % MOD;
			answer %= MOD;
		}
		out.printLine(answer);
	}
}
