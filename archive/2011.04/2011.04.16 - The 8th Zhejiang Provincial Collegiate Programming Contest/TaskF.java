package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.collections.sequence.ArrayWrapper;
import net.egork.collections.sequence.SequenceUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskF implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		String front = in.readString();
		String[] order = in.readStringArray(count);
		int index = SequenceUtils.find(ArrayWrapper.wrap(order), (front));
		index = (index + count / 2) % count;
		out.println(order[index]);
	}
}

