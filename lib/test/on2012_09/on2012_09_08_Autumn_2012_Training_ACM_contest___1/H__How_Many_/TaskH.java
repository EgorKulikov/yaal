package on2012_09.on2012_09_08_Autumn_2012_Training_ACM_contest___1.H__How_Many_;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int canTake = in.readInt();
		long[][] c = IntegerUtils.generateBinomialCoefficients(count + 1);
		out.printLine(c[count][canTake]);
	}
}
