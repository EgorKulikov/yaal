package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.collections.sequence.Array;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskF implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		String front = in.readString();
		String[] order = in.readStringArray(count);
		int index = SequenceUtils.find(Array.wrap(order), (front));
		index = (index + count / 2) % count;
		out.println(order[index]);
	}
}

