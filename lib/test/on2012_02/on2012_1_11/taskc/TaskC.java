package on2012_02.on2012_1_11.taskc;



import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] skill = IOUtils.readIntArray(in, count);
		Integer[] order = ListUtils.order(Array.wrap(skill));
		int delta = 0;
		List<Integer> first = new ArrayList<Integer>();
		List<Integer> second = new ArrayList<Integer>();
		for (int i = 0; i + 1 < count; i += 2) {
			if (delta >= 0) {
				first.add(order[i] + 1);
				second.add(order[i + 1] + 1);
				delta += skill[order[i]] - skill[order[i + 1]];
			} else {
				first.add(order[i + 1] + 1);
				second.add(order[i] + 1);
				delta -= skill[order[i]] - skill[order[i + 1]];
			}
		}
		if (count % 2 == 1) {
			if (delta >= 0)
				second.add(order[count - 1] + 1);
			else
				first.add(order[count - 1] + 1);
		}
		out.printLine(first.size());
		out.printLine(first.toArray());
		out.printLine(second.size());
		out.printLine(second.toArray());
	}
}
