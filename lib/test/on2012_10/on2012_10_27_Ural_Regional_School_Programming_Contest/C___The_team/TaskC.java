package on2012_10.on2012_10_27_Ural_Regional_School_Programming_Contest.C___The_team;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] required = IOUtils.readIntArray(in, count);
		int[] order = ArrayUtils.order(required);
		List<Integer> answer = new ArrayList<Integer>();
		for (int i = 0; i < count; i++) {
			if (required[order[i]] <= i + 1 && (i == count - 1 || required[order[i + 1]] > i + 2))
				answer.add(i + 1);
		}
		out.printLine(answer.size());
		for (int i : answer) {
			out.print(i);
			for (int j = 0; j < i; j++)
				out.print(" " + (order[j] + 1));
			out.printLine();
		}
	}
}
