package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		long previous = number;
		while (!BigInteger.valueOf(previous).isProbablePrime(20))
			previous--;
		long next = number + 1;
		while (!BigInteger.valueOf(next).isProbablePrime(20))
			next++;
		Rational answer = new Rational((previous - 2L) * next + 2 * (number - previous + 1), 2L * previous * next);
		out.printLine(answer);
    }
}
