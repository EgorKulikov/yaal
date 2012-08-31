package on2011_12.on2011_11_12.taskg;



import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;
import java.util.TreeSet;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Set<Integer> answer = new TreeSet<Integer>(Array.wrap(IOUtils.readIntArray(in, in.readInt())));
		answer.addAll(Array.wrap(IOUtils.readIntArray(in, in.readInt())));
		out.printLine(answer.toArray());
	}
}
