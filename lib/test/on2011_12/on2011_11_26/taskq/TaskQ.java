package on2011_12.on2011_11_26.taskq;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskQ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt() - 1;
		for (int i = 0; ; i++) {
			if (index <= i) {
				out.printLine(IntegerUtils.generateBinomialCoefficients(i + 1)[i][index]);
				return;
			}
			index -= i + 1;
		}
	}
}
