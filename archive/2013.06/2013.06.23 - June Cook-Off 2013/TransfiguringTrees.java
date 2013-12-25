package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TransfiguringTrees {
	private static final long MOD = (long) (1e9 + 7);
	long[] factorial = IntegerUtils.generateFactorial(100000, MOD);
	long[] reverse = IntegerUtils.generateReverseFactorials(100000, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxChildren = in.readInt();
		int[] preOrder = IOUtils.readIntArray(in, count);
		int[] postOrder = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(postOrder, preOrder);
		int[] preStart = new int[count];
		int[] preEnd = new int[count];
		int[] postStart = new int[count];
		int[] postEnd = new int[count];
		preEnd[0] = count - 1;
		postEnd[0] = count - 1;
		int[] postReverse = ArrayUtils.reversePermutation(postOrder);
		int size = 1;
		long answer = 1;
		for (int i = 0; i < size; i++) {
			int aStart = preStart[i];
			int aEnd = preEnd[i];
			int bStart = postStart[i];
			int bEnd = postEnd[i];
			if (preOrder[aStart] != postOrder[bEnd]) {
				out.printLine(0);
				return;
			}
			aStart++;
			bEnd--;
			int qty = 0;
			while (aStart <= aEnd) {
				int at = postReverse[preOrder[aStart]];
				if (at < bStart || at > bEnd) {
					out.printLine(0);
					return;
				}
				preStart[size] = aStart;
				preEnd[size] = aStart + at - bStart;
				postStart[size] = bStart;
				postEnd[size++] = at;
				qty++;
				aStart += at - bStart + 1;
				bStart = at + 1;
			}
			if (qty > maxChildren) {
				out.printLine(0);
				return;
			}
			answer = answer * c(maxChildren, qty) % MOD;
		}
		out.printLine(answer);
    }

	private long c(int n, int m) {
		return factorial[n] * reverse[n - m] % MOD * reverse[m] % MOD;
	}
}
