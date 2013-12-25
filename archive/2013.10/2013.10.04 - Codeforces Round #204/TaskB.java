package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] permutation = IOUtils.readIntArray(in, count);
		int inversionCount = (int) ListUtils.countUnorderedPairs(Array.wrap(permutation));
		int answer = inversionCount / 2 * 4 + inversionCount % 2;
		out.printLine(answer);
    }
}
