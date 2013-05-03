package on2011_11.on2011_10_28.task1796;



import net.egork.misc.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1796 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] value = {10, 50, 100, 500, 1000, 5000};
		int[] count = IOUtils.readIntArray(in, 6);
		int price = in.readInt();
		long money = ArrayUtils.multiply(value, count);
		long butOne = money;
		for (int i = 0; i < 6; i++) {
			if (count[i] != 0) {
				butOne -= value[i];
				break;
			}
		}
		int[] answer = ArrayUtils.range((int)(butOne / price + 1), (int) (money / price));
		out.printLine(answer.length);
		out.printLine(Array.wrap(answer).toArray());
	}
}
