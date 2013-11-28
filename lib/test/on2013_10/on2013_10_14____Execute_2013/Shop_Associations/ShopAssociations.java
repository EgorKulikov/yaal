package on2013_10.on2013_10_14____Execute_2013.Shop_Associations;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ShopAssociations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] prices = IOUtils.readIntArray(in, count);
		long[] sums = ArrayUtils.partialSums(prices);
		long min = Long.MAX_VALUE / 2;
		long answer = Long.MIN_VALUE;
		for (long l : sums) {
			answer = Math.max(answer, l - min);
			min = Math.min(min, l);
		}
		out.printLine(answer);
    }
}
