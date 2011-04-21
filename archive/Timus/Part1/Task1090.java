package Timus.Part1;

import net.egork.collections.sequence.ArrayWrapper;
import net.egork.collections.sequence.SequenceUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1090 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		long[] answer = new long[k];
		for (int i = 0; i < k; i++)
			answer[i] = SequenceUtils.countUnorderedPairs(ArrayWrapper.wrap(in.readIntArray(n)));
		out.println(SequenceUtils.maxIndex(ArrayWrapper.wrap(answer)) + 1);
	}
}

