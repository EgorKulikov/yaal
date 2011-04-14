package Timus.Part2;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class Task1108 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		BigInteger current = BigInteger.ONE;
		for (int i = 0; i < n; i++) {
			out.println(current.add(BigInteger.ONE));
			current = current.multiply(current.add(BigInteger.ONE));
		}
	}
}

