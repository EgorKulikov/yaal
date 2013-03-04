package on2011_10.on2011_9_30.printing;



import net.egork.io.IOUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Printing {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int pageCount = in.readInt();
		int[] cost = new int[count];
		int[] pages = new int[count];
		IOUtils.readIntArrays(in, cost, pages);
		int bestIndex = -1;
		Rational bestRatio = Rational.MAX_VALUE;
		for (int i = 0; i < count; i++) {
			Rational curRatio = new Rational(cost[i], pages[i]);
			if (curRatio.compareTo(bestRatio) < 0) {
				bestRatio = curRatio;
				bestIndex = i;
			}
		}
		int[] result = new int[pages[bestIndex] * pages[bestIndex]];
		Arrays.fill(result, Integer.MAX_VALUE / 2);
		result[0] = 0;
		boolean[] good = new boolean[count];
		int[] bestPrice = new int[201];
		int[] bestPriceIndex = new int[201];
		Arrays.fill(bestPrice, Integer.MAX_VALUE);
		Arrays.fill(bestPriceIndex, -1);
		for (int i = 0; i < count; i++) {
			if (cost[i] < bestPrice[pages[i]]) {
				bestPrice[pages[i]] = cost[i];
				bestPriceIndex[pages[i]] = i;
			}
		}
		for (int i : bestPriceIndex) {
			if (i != -1)
				good[i] = true;
		}
		for (int i = 0; i < count; i++) {
			if (!good[i])
				continue;
			for (int j = 0; j < result.length - pages[i]; j++)
				result[j + pages[i]] = Math.min(result[j + pages[i]], result[j] + cost[i]);
		}
		long answer = Long.MAX_VALUE;
		for (int i = pageCount % pages[bestIndex]; i <= pageCount && i < result.length; i += pages[bestIndex]) {
			if (result[i] == Integer.MAX_VALUE / 2)
				continue;
			answer = Math.min(answer, result[i] + (long)(pageCount - i) / pages[bestIndex] * cost[bestIndex]);
		}
		if (answer == Long.MAX_VALUE)
			answer = -1;
		out.println(answer);
	}
}
