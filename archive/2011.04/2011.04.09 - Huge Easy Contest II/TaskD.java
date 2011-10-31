package April2011.UVaHugeEasyContestII;

import net.egork.collections.sequence.Array;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		out.println("Case " + testNumber + ": " + calculate(Array.wrap(in.readLine().split(" "))).toString(2));
	}

	private BigInteger calculate(Sequence<String> expression) {
		if (expression.size() == 1)
			return new BigInteger(expression.get(0), 2);
		for (int i = expression.size() - 1; i >= 0; i--) {
			String token = expression.get(i);
			if (token.equals("or"))
				return calculate(expression.subSequence(0, i)).or(calculate(expression.subSequence(i + 1)));
			if (token.equals("xor"))
				return calculate(expression.subSequence(0, i)).xor(calculate(expression.subSequence(i + 1)));
			if (token.equals("and"))
				return calculate(expression.subSequence(0, i)).and(calculate(expression.subSequence(i + 1)));
		}
		BigInteger result = calculate(expression.subSequence(1));
		if (expression.get(0).equals("shr"))
			return result.shiftRight(1);
		else if (expression.get(0).equals("shl"))
			return result.shiftLeft(1);
		else if (result.equals(BigInteger.ZERO))
			return BigInteger.ONE;
		else
			return BigInteger.ONE.shiftLeft(result.bitLength()).subtract(BigInteger.ONE).subtract(result);
	}
}

