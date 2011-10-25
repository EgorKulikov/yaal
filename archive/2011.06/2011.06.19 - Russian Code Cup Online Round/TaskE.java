import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		long sum = in.readLong();
		int ones = in.readInt();
		long transaction = 0;
		for (int i = 0; i < 18; i++) {
			transaction *= 10;
			transaction++;
		}
		int result = go(sum, ones, transaction, 18);
		if (result >= Integer.MAX_VALUE / 2)
			out.println("Impossible");
		else
			out.println(result);
	}

	private int go(long sum, int ones, long transaction, int step) {
		if (sum == 0) {
			if (ones == 0)
				return 0;
			return Integer.MAX_VALUE / 2;
		}
		if (step == 0)
			return Integer.MAX_VALUE / 2;
		if (sum / transaction > ones / step)
			return Integer.MAX_VALUE / 2;
		long max = sum / transaction;
		int result = Integer.MAX_VALUE / 2;
		for (int i = 0; i <= max; i++) {
			result = Math.min(result, go(sum, ones, transaction / 10, step - 1) + i);
			sum -= transaction;
			ones -= step;
		}
		return result;
	}
}

