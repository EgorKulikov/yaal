package on2011_9_26.taska;



import net.egork.collections.CollectionUtils;
import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.ArrayWrapper;
import net.egork.collections.sequence.SequenceUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[] weights = IOUtils.readIntArray(in, 3);
		if (SequenceUtils.find(ArrayWrapper.wrap(weights), new Filter<Integer>() {
			public boolean accept(Integer value) {
				return value < 94 || value > 727;
			}
		}) != -1)
		{
			out.println("Error");
			return;
		}
		out.println(CollectionUtils.maxElement(ArrayWrapper.wrap(weights)));
	}
}
