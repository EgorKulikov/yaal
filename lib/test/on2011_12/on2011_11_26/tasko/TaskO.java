package on2011_12.on2011_11_26.tasko;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskO {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(IntegerUtils.lcm(in.readInt(), in.readInt()) - 1);
	}
}
