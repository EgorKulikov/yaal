package on2011_10.on2011_9_26.taska;


import net.egork.collections.CollectionUtils;
import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[] weights = IOUtils.readIntArray(in, 3);
		if (ListUtils.find(Array.wrap(weights), new Filter<Integer>() {
			public boolean accept(Integer value) {
				return value < 94 || value > 727;
			}
		}) != 3)
		{
			out.println("Error");
			return;
		}
		out.println(CollectionUtils.maxElement(Array.wrap(weights)));
	}
}
