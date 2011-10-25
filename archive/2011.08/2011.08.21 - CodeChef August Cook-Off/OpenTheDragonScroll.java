import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class OpenTheDragonScroll implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int numBits = in.readInt();
		int bits = Integer.bitCount(in.readInt()) + Integer.bitCount(in.readInt());
		bits = Math.min(bits, 2 * numBits - bits);
		int result = 0;
		for (int i = 0; i < bits; i++)
			result += 1 << (numBits - i - 1);
		out.println(result);
	}
}

