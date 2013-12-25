package on2013_07.on2013_07_02_World_Finals_Dress_Rehearsal.TaskC;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		int earth = in.readInt();
		int mars = in.readInt();
		long passed = IntegerUtils.findCommon(earth, 365, mars, 687);
		out.printLine("Case", testNumber + ":", (687 * 365 - passed) % (687 * 365));
    }
}
