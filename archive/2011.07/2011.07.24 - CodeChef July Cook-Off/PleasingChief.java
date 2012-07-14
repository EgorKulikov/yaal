import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class PleasingChief implements Solver {
	private static double[][] c;

	static {
		init(1000);
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int itemCount = in.readInt();
		int giftCount = in.readInt();
		int[] prices = IOUtils.readIntArray(in, itemCount);
		int[] discounts = IOUtils.readIntArray(in, giftCount);
		Arrays.sort(prices);
		Arrays.sort(discounts);
		double result = 0;
		for (int i = 0; i < itemCount; i++) {
			int min = Math.max(0, giftCount - itemCount + i);
			int max = Math.min(giftCount, i + 1);
			double current = 0;
			for (int j = min; j < max; j++)
				current += discounts[j] * c[i][j] * c[itemCount - i - 1][giftCount - j - 1];
			result += current * prices[i];
		}
		result /= c[itemCount][giftCount] * 100;
		out.printf("%.3f\n", result);
	}

	private static void init(int itemCount) {
		c = new double[itemCount + 1][itemCount + 1];
		for (int i = 0; i <= itemCount; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++)
				c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
		}
	}
}

