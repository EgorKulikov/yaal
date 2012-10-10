package on2012_05.on2012_4_6.taskd;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int base = in.readInt();
		long left = in.readLong();
		long right = in.readLong();
		int mod = in.readInt();
		if (mod == 2) {
			out.printLine((base + 1) % 2);
			return;
		}
		long realBase;
		if (base % mod == 0)
			realBase = 0;
		else
			realBase = IntegerUtils.power(base, IntegerUtils.power(2, left, mod - 1), mod);
		long answer;
		if (realBase == 0)
			answer = 1;
		else if (realBase == 1)
			answer = IntegerUtils.power(2, right - left + 1, mod);
		else {
			answer = (IntegerUtils.power(realBase, IntegerUtils.power(2, right - left + 1, mod - 1), mod) - 1) *
				(BigInteger.valueOf(realBase - 1).modInverse(BigInteger.valueOf(mod)).longValue()) % mod;
		}
		if (base % 2 == 1)
			answer = answer * IntegerUtils.power((mod + 1) / 2, right - left, mod) % mod;
		out.printLine(answer);
	}
}
