package on2013_11.on2013_11_26_Codeforces_Round__215__Div__1_.C___Sereja_and_the_Arrangement_of_Numbers;



import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxFit = 1;
		for (int i = 2; ; i++) {
			int k = i >> 1;
			int current;
			if ((i & 1) == 0)
				current = 2 * k * k;
			else
				current = k * (2 * k + 1) + 1;
			if (current > count)
				break;
			maxFit = i;
		}
		int ticketCount = in.readInt();
		int[] number = new int[ticketCount];
		int[] price = new int[ticketCount];
		IOUtils.readIntArrays(in, number, price);
		ArrayUtils.sort(price, IntComparator.REVERSE);
		long[] partial = ArrayUtils.partialSums(price);
		out.printLine(partial[Math.min(ticketCount, maxFit)]);
    }
}
