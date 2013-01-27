package on2011_12.on2011_11_26.taskc;



import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(new HashSet<Integer>(Array.wrap(IOUtils.readIntArray(in, in.readInt()))).size());
	}
}
