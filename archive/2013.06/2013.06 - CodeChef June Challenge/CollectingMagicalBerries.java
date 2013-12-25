package net.egork;

import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CollectingMagicalBerries {
	static final int MOD = 3046201;

	long[] factorial = IntegerUtils.generateFactorial(MOD, MOD);
	long[] reverse = IntegerUtils.generateReverseFactorials(MOD, MOD);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] initial = IOUtils.readIntArray(in, count);
		SumIntervalTree tree = new SumIntervalTree(count) {
			@Override
			protected long initValue(int index) {
				return initial[index];
			}
		};
		tree.init();
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == 'c') {
				for (int j = 0; j < 5; j++)
					in.readCharacter();
				int index = in.readInt() - 1;
				int value = in.readInt();
				tree.update(index, index, value - tree.query(index, index));
			} else {
				for (int j = 0; j < 4; j++)
					in.readCharacter();
				int from = in.readInt() - 1;
				int to = in.readInt() - 1;
				int total = (int) tree.query(from, to);
				int qty = to - from + 1;
				if (total >= MOD) {
					out.printLine(0);
					continue;
				}
				long answer = factorial[qty] * reverse[total % qty] % MOD * reverse[qty - total % qty] % MOD *
					factorial[total] % MOD * IntegerUtils.power(reverse[total / qty + 1], total % qty, MOD) % MOD *
					IntegerUtils.power(reverse[total / qty], qty - total % qty, MOD) % MOD;
				out.printLine(answer);
			}
		}
	}
}
