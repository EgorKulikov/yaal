package on2015_12.on2015_12_20_Grand_Prix_of_Peterhof.F____p__q__Knight;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int p = in.readInt();
		int q = in.readInt();
		if ((p % 2 == 0) == (q % 2 == 0) || IntegerUtils.gcd(p, q) != 1) {
			out.printLine(-1);
			return;
		}
		if (p % 2 == 1) {
			int temp = p;
			p = q;
			q = temp;
		}
		long c = BigInteger.valueOf(p).modInverse(BigInteger.valueOf(q)).longValue();
		if (c % 2 == 1) {
			c += q;
		}
		long answer = Long.MAX_VALUE;
		for (long cc = c; cc >= -2 * q; cc -= 2 * q) {
			long dd = (1 - cc * p) / q;
			long current = Math.max(Math.abs(cc), p) + Math.max(Math.abs(dd), q);
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
	}
}
