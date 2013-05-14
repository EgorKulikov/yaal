package on2011_11.on2011_10_28.taskt;



import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskT {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Set<Integer> days = new HashSet<Integer>(Array.wrap(IOUtils.readIntArray(in, count)));
		out.printLine(days.size());
	}
}
