import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class mowlawn implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int cowCount = in.readInt();
		int partyCows = in.readInt();
		int[] efficiency  = IOUtils.readIntArray(in, cowCount);
		final long[] result = new long[cowCount];
		NavigableSet<Integer> results = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return IntegerUtils.longCompare(result[o2], result[o1]);
			}
		});
		long sum = 0;
		for (int i = 0; i < cowCount; i++) {
			long best = i <= partyCows ? 0 : result[results.first()];
			sum += efficiency[i];
			result[i] = best - efficiency[i];
			results.add(i);
			if (i > partyCows)
				results.remove(i - partyCows - 1);
		}
		out.println(sum + (partyCows == cowCount ? 0 : result[results.first()]));
	}
}

