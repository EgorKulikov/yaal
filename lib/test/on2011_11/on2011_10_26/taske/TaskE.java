package on2011_11.on2011_10_26.taske;



import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		char[] permutation = in.readString().toCharArray();
		if (ListUtils.nextPermutation(Array.wrap(permutation)))
			out.printLine(testNumber, new String(permutation));
		else
			out.printLine(testNumber, "BIGGEST");
	}
}
