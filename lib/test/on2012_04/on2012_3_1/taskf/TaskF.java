package on2012_04.on2012_3_1.taskf;



import net.egork.numbers.IntegerUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		boolean[] isPrime = IntegerUtils.generatePrimalityTable(10000000);
		int index = in.readInt();
		for (int i = 13; ; i++) {
			int reverse = Integer.parseInt(StringUtils.reverse(Integer.toString(i)));
			if (isPrime[i] && isPrime[reverse] && i != reverse)
				index--;
			if (index == 0) {
				out.printLine(i);
				return;
			}
		}
	}
}
