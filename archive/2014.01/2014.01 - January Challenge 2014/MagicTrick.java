package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class MagicTrick {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] array = IOUtils.readLongArray(in, count);
		BigInteger toAdd = BigInteger.valueOf(in.readLong());
		BigInteger toMultiply = BigInteger.valueOf(in.readLong());
		BigInteger mod = BigInteger.valueOf(in.readLong());;
		char[] operations = IOUtils.readCharArray(in, count);
		long[] answer = new long[count];
		BigInteger totalMultiply = BigInteger.ONE;
		BigInteger totalAdd = BigInteger.ZERO;
		int start = 0;
		int end = count - 1;
		boolean reverse = false;
		int i = 0;
		for (char c : operations) {
			if (c == 'R')
				reverse = !reverse;
			else if (c == 'A')
				totalAdd = totalAdd.add(toAdd).mod(mod);
			else {
				totalMultiply = totalMultiply.multiply(toMultiply).mod(mod);
				totalAdd = totalAdd.multiply(toMultiply).mod(mod);
			}
			long base;
			if (reverse)
				base = array[end--];
			else
				base = array[start++];
			answer[i++] = BigInteger.valueOf(base).multiply(totalMultiply).add(totalAdd).mod(mod).longValue();
		}
		out.printLine(answer);
    }
}
