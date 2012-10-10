package on2012_02.on2012_1_18.taska0;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		char[] seats = IOUtils.readCharArray(in, count);
		int cupHolderCount = count + 1 - CollectionUtils.count(Array.wrap(seats), 'L') / 2;
		out.printLine(Math.min(count, cupHolderCount));
	}
}
