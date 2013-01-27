import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] boss = IOUtils.readIntArray(in, count);
		int[] depth = new int[count];
		for (int i = 0; i < count; i++)
			go(i, boss, depth);
		out.println(CollectionUtils.maxElement(Array.wrap(depth)));
	}

	private int go(int index, int[] boss, int[] depth) {
		if (index == -2)
			return 0;
		if (depth[index] != 0)
			return depth[index];
		return depth[index] = go(boss[index] - 1, boss, depth) + 1;
	}
}

