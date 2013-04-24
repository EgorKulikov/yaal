package net.egork;

import net.egork.collections.set.TreapSet;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.NavigableSet;

public class TaskC {
	NavigableSet<BigInteger> all = new TreapSet<BigInteger>();

	{
		all.add(BigInteger.valueOf(1));
		all.add(BigInteger.valueOf(4));
		all.add(BigInteger.valueOf(9));
		generate("1", 7);
		generate("2", 1);
		System.err.println(all.size());
	}

	private void generate(String prefix, int remaining) {
		tryAdd(prefix + StringUtils.reverse(prefix));
		if (prefix.length() == 25)
			return;
		for (int i = 0; i <= 2; i++) {
			if (remaining >= i * i)
				tryAdd(prefix + i + StringUtils.reverse(prefix));
			if (remaining >= 2 * i * i)
				generate(prefix + i, remaining - 2 * i * i);
		}
	}

	private void tryAdd(String s) {
		BigInteger base = new BigInteger(s);
		BigInteger candidate = base.multiply(base);
		all.add(candidate);
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger from = in.readBigInteger();
		BigInteger to = in.readBigInteger();
		out.printLine("Case #" + testNumber + ":", all.subSet(from, true, to, true).size());
    }
}
