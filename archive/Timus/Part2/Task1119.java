package Timus.Part2;

import net.egork.collections.sequence.ArrayWrapper;
import net.egork.collections.Pair;
import net.egork.collections.sequence.SequenceUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1119 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int blockCount = in.readInt();
		@SuppressWarnings({"unchecked"})
		Pair<Integer, Integer>[] blocks = new Pair[blockCount];
		for (int i = 0; i < blockCount; i++) {
			int row = in.readInt();
			int column = in.readInt();
			blocks[i] = new Pair<Integer, Integer>(row, column);
		}
		Arrays.sort(blocks, new Pair.Comparator<Integer, Integer>());
		int[] maxSequence = new int[blockCount];
		for (int i = 0; i < blockCount; i++) {
			maxSequence[i] = 1;
			for (int j = 0; j < i; j++) {
				if (blocks[j].first() < blocks[i].first() && blocks[j].second() < blocks[i].second())
					maxSequence[i] = Math.max(maxSequence[i], maxSequence[j] + 1);
			}
		}
		int maximum = blockCount == 0 ? 0 : maxSequence[SequenceUtils.maxIndex(ArrayWrapper.wrap(maxSequence))];
		out.printf("%.0f\n", (n + m - (2 - Math.sqrt(2)) * maximum) * 100);
	}
}

