import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int money = in.readInt();
		int[] price = IOUtils.readIntArray(in, count);
		Arrays.sort(price);
		int result = 0;
		for (int i = 0; i < count && price[i] <= money; i++) {
			money -= price[i];
			result++;
		}
		out.println(result);
	}
}

