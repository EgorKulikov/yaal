package on2011_12.on2011_11_12.taske;



import net.egork.collections.intcollection.IntArray;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.List;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int[] array = new int[length];
		for (int i = 0; i < length; i++)
			array[i] = i + 1;
		IntList wrapped = new IntArray(array);
		do {
			out.printLine(wrapped);
		} while (wrapped.nextPermutation());
	}
}
