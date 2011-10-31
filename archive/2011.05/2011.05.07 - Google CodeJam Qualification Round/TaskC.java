import net.egork.collections.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int candyCount = in.readInt();
		int[] candies = IOUtils.readIntArray(in, candyCount);
		int patrickValue = 0;
		for (int candy : candies)
			patrickValue ^= candy;
		out.print("Case #" + testNumber + ": ");
		if (patrickValue != 0)
			out.println("NO");
		else
			out.println(ArrayUtils.sumArray(candies) - CollectionUtils.minElement(Array.wrap(candies)));
	}
}

