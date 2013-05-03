package on2012_10.on2012_10_21_October_Cook_Off_2012.Buying_Sweets;



import net.egork.misc.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BuyingSweets {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int price = in.readInt();
		int[] values = IOUtils.readIntArray(in, count);
		long sum = ArrayUtils.sumArray(values);
		long answer = sum / price;
		int minValue = CollectionUtils.minElement(Array.wrap(values));
		if (answer == (sum - minValue) / price)
			out.printLine(-1);
		else
			out.printLine(answer);
	}
}
