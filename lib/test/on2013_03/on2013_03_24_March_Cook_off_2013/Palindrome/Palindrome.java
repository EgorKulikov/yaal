package on2013_03.on2013_03_24_March_Cook_off_2013.Palindrome;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class Palindrome {
	static final long MOD = (long) (1e9 + 7);
	static final long REVERSE = BigInteger.valueOf(25).modInverse(BigInteger.valueOf(MOD)).longValue();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		out.printLine((calculate(count / 2) + calculate((count + 1) / 2)) % MOD);
    }

	private long calculate(int count) {
		return (IntegerUtils.power(26, count + 1, MOD) - 1) * REVERSE % MOD + MOD - 1;
	}
}
