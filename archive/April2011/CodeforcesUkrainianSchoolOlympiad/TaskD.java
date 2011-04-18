package April2011.CodeforcesUkrainianSchoolOlympiad;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		BigInteger a = in.readBigInteger();
		BigInteger b = in.readBigInteger();
		if (a.testBit(0) != b.testBit(0)) {
			out.println(-1);
			return;
		}
		BigInteger x = BigInteger.ZERO;
		BigInteger y = BigInteger.ZERO;
		boolean carryOn = false;
		for (int i = 0; i < 64; i++) {
			if (a.testBit(i + 1) != b.testBit(i + 1)) {
				if (a.testBit(i) && b.testBit(i)) {
					out.println(-1);
					return;
				}
				if (b.testBit(i))
					y = y.setBit(i);
				else {
					x = x.setBit(i);
					y = y.setBit(i);
				}
			} else {
				if (!a.testBit(i) && b.testBit(i)) {
					out.println(-1);
					return;
				}
				if (b.testBit(i))
					y = y.setBit(i);
			}
		}
		out.println(x + " " + y);
	}
}

