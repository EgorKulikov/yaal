package Timus.Part4;

import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.List;

public class Task1355 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		if (b % a != 0) {
			out.println(0);
			return;
		}
		List<Pair<Long, Integer>> divisors = IntegerUtils.factorize(b / a);
		int result = 1;
		for (Pair<Long, Integer> divisor : divisors)
			result += divisor.second();
		out.println(result);
	}
}

