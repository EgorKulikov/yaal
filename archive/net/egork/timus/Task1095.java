package net.egork.timus;

import net.egork.arrays.ArrayWrapper;
import net.egork.arrays.ArrayUtils;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class Task1095 implements Solver {
	private static final BigInteger SEVEN = BigInteger.valueOf(7);

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] number = in.readString().toCharArray();
		Arrays.sort(number);
		for (int i = 0; i < number.length; i++) {
			if (number[i] != '0') {
				char temp = number[i];
				number[i] = number[0];
				number[0] = temp;
			}
		}
		do {
			if (new BigInteger(new String(number)).mod(SEVEN).equals(BigInteger.ZERO)) {
				out.println(new String(number));
				return;
			}
		} while (ArrayUtils.nextPermutation(ArrayWrapper.wrap(number)));
		out.println(0);
	}
}

