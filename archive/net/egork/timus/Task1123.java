package net.egork.timus;

import net.egork.string.StringUtils;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class Task1123 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String salary = in.readString();
		int n = salary.length();
		String candidate = salary.substring(0, (n + 1) / 2) + StringUtils.reverse(salary.substring(0, n / 2));
		if (new BigInteger(candidate).compareTo(new BigInteger(salary)) >= 0) {
			out.println(candidate);
			return;
		}
		BigInteger firstPart = new BigInteger(salary.substring(0, (n + 1) / 2));
		firstPart = firstPart.add(BigInteger.ONE);
		String firstPartString = firstPart.toString();
		candidate = firstPartString + StringUtils.reverse(firstPartString.substring(0, n / 2));
		if (candidate.length() == salary.length()) {
			out.println(candidate);
			return;
		}
		throw new RuntimeException();
	}
}

