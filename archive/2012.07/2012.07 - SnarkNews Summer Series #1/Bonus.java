package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Bonus {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        long base = in.readInt();
        long power = in.readInt();
        long mod = in.readInt();
        long answer = go(base, power, mod);
        out.printLine(answer);
	}

    private long go(long base, long power, long mod) {
        if (power == 0)
            return 0;
        long result = go(base, power >> 1, mod);
        result *= 1 + IntegerUtils.power(base, power >> 1, mod);
        result %= mod;
        if ((power & 1) != 0) {
            result += IntegerUtils.power(base, power - 1, mod);
            result %= mod;
        }
        return result;
    }
}
