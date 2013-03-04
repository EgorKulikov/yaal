package on2011_12.on2011_11_17.taskg;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int x = in.readInt();
		int y = in.readInt();
		int module = in.readInt();
		long powY = IntegerUtils.power(y, n - 1, module);
		long answer = (x + y) * powY + (n - 1) * powY % module * x;
		answer %= module;
		out.printLine(answer);
	}
}
