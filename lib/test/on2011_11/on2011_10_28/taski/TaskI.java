package on2011_11.on2011_10_28.taski;



import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.List;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		List<Pair<Long,Integer>> factors = IntegerUtils.factorize(number);
		boolean first = true;
		for (Pair<Long, Integer> factor : factors) {
			if (first)
				first = false;
			else
				out.print('*');
			out.print(factor.first);
			if (factor.second != 1)
				out.print("^" + factor.second);
		}
		out.printLine();
	}
}
