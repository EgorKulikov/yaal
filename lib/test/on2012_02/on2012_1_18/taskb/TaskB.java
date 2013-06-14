package on2012_02.on2012_1_18.taskb;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int size = in.readInt();
		int palLength = in.readInt();
		if (palLength == 1 || palLength > length)
			out.printLine(IntegerUtils.power(size, length, MOD));
		else if (palLength == length)
			out.printLine(IntegerUtils.power(size, (palLength + 1) / 2, MOD));
		else if (palLength % 2 == 1)
			out.printLine(IntegerUtils.power(size, 2, MOD));
		else
			out.printLine(size);
	}
}
