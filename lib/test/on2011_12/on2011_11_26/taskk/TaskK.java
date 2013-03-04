package on2011_12.on2011_11_26.taskk;



import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(MiscUtils.convertToRoman(in.readInt()));
	}
}
