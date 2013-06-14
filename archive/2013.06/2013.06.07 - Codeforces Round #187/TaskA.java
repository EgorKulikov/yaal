package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		BigInteger threshold = in.readBigInteger();
		int[] rating = IOUtils.readIntArray(in, count);
		BigInteger sum = BigInteger.ZERO;
		int good = 0;
		for (int i = 0; i < count; i++) {
			BigInteger bigRating = BigInteger.valueOf(rating[i]);
			BigInteger curDelta = sum.subtract(BigInteger.valueOf((long)(count - 1 - i) * good).multiply(bigRating));
			if (curDelta.compareTo(threshold) < 0)
				out.printLine(i + 1);
			else {
				sum = sum.add(bigRating.multiply(BigInteger.valueOf(good)));
				good++;
			}
		}
    }
}
