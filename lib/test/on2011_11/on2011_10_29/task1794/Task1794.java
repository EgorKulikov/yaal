package on2011_11.on2011_10_29.task1794;



import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1794 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] delta = new int[count];
		for (int i = 1; i <= count; i++)
			delta[(i - in.readInt() + count) % count]++;
		out.printLine(ListUtils.maxIndex(Array.wrap(delta)) + 1);
	}
}
