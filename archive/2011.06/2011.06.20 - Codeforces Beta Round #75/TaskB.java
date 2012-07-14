import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Comparator;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int walrusCount = in.readInt();
		final int[] age = IOUtils.readIntArray(in, walrusCount);
		Integer[] order = ArrayUtils.order(walrusCount, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (age[o1] != age[o2])
					return age[o1] - age[o2];
				return o1 - o2;
			}
		});
		int max = -1;
		int[] result = new int[walrusCount];
		for (int i : order) {
			if (i > max) {
				max = i;
				result[i] = -1;
			} else
				result[i] = max - i - 1;
		}
		IOUtils.printArray(result, out);
	}
}

