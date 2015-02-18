package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class Task12 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		BigInteger need = in.readBigInteger();
		BigInteger total = BigInteger.ONE;
		for (int i = 1; i <= count; i++) {
			total = total.multiply(BigInteger.valueOf(2 * i));
		}
		out.printLine(total.compareTo(need) >= 0 ? "Harshat Mata" : "Nope");
    }
}
