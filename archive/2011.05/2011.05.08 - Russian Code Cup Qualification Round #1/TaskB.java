import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int tariffCount = in.readInt();
		int callCount = in.readInt();
		int[] fixed = new int[tariffCount];
		int[] period = new int[tariffCount];
		int[] price = new int[tariffCount];
		IOUtils.readIntArrays(in, fixed, period, price);
		int[] calls = IOUtils.readIntArray(in, callCount);
		int index = 0;
		int cost = Integer.MAX_VALUE;
		for (int i = 0; i < tariffCount; i++) {
			int currentCost = fixed[i] * 100;
			for (int call : calls) {
				if (call >= period[i])
					currentCost += (call + period[i] - 1) / period[i] * price[i];
			}
			if (currentCost < cost) {
				cost = currentCost;
				index = i + 1;
			}
		}
		out.println(index);
	}
}

