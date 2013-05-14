package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] permutation = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(permutation);
		int[] reverse = ArrayUtils.createOrder(count);
		ArrayUtils.reverse(reverse);
		if (ListUtils.countUnorderedPairs(Array.wrap(permutation)) % 2 == ListUtils.countUnorderedPairs(Array.wrap(reverse)) % 2)
			out.printLine("Second");
		else
			out.printLine("First");
    }
}
