import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int[] numbers = IOUtils.readIntArray(in, 2);
		int count = in.readInt();
		while (true) {
			for (int i = 0; i < 2; i++) {
				long move = IntegerUtils.gcd(count, numbers[i]);
				count -= move;
				if (count < 0) {
					out.println(1 - i);
					return;
				}
			}
		}
	}
}

