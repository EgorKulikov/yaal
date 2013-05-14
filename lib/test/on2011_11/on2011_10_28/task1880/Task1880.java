package on2011_11.on2011_10_28.task1880;



import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class Task1880 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Set<Integer> numbers = read(in);
		numbers.retainAll(read(in));
		numbers.retainAll(read(in));
		out.printLine(numbers.size());
	}

	private Set<Integer> read(InputReader in) {
		return new HashSet<Integer>(Array.wrap(IOUtils.readIntArray(in, in.readInt())));
	}
}
