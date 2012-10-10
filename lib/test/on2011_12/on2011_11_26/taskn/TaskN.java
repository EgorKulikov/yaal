package on2011_12.on2011_11_26.taskn;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskN {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt();
		out.printLine(IntegerUtils.generateFibonacci(index + 2, -1)[index + 1]);
	}
}
