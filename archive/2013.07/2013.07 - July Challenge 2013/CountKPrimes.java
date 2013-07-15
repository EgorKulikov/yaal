package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CountKPrimes {
	LongIntervalTree[] trees = new LongIntervalTree[5];

	{
		int[] divisor = IntegerUtils.generateDivisorTable(100001);
		int[] count = new int[100001];
		for (int i = 2; i <= 100000; i++) {
			int j = i;
			while (j % divisor[i] == 0)
				j /= divisor[i];
			count[i] = count[j] + 1;
		}
		for (int i = 0; i < 5; i++) {
			trees[i] = new SumIntervalTree(100001);
			for (int j = 2; j <= 100000; j++) {
				if (count[j] == i + 1)
					trees[i].update(j, j, 1);
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		int count = in.readInt();
		out.printLine(trees[count - 1].query(from, to));
	}
}
