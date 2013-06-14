package on2012_09.on2012_09_08_Autumn_2012_Training_ACM_contest___1.F__Fibonacci_Sequence;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] fibonacci = IntegerUtils.generateFibonacci(count, -1);
		out.printLine(fibonacci[count - 1]);
	}
}
