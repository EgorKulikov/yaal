package on2013_11.on2013_11_22_20_20_Hack_November.B_day_Gift;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BdayGift {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		out.printFormat("%.1f\n", ArrayUtils.sumArray(numbers) / 2d);
    }
}
