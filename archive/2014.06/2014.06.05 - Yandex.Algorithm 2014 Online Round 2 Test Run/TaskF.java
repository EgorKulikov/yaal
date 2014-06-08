package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] qty = new int[3][count + 1];
		for (int i = 0; i < 3; i++)
			qty[i] = getQty(IOUtils.readIntArray(in, count));
		int[][] present = new int[3][];
		for (int i = 0; i < 3; i++)
			present[i] = getPresent(qty[i]);
		long answer = 0;
		for (int i : present[0]) {
			for (int j : present[1]) {
				int g = IntegerUtils.gcd(i, j);
				for (int k : present[2]) {
					int result = g / IntegerUtils.gcd(g, k);
					result *= IntegerUtils.gcd(i, k) * IntegerUtils.gcd(j, k);
					answer += (long)result * qty[0][i] * qty[1][j] * qty[2][k];
				}
			}
		}
		out.printLine(answer);
    }

	private int[] getPresent(int[] qty) {
		IntList result = new IntArrayList();
		for (int i = 1; i < qty.length; i++) {
			if (qty[i] != 0)
				result.add(i);
		}
		return result.toArray();
	}

	private int[] getQty(int[] array) {
		MiscUtils.decreaseByOne(array);
		boolean[] present = new boolean[array.length];
		int[] result = new int[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			if (present[i])
				continue;
			int size = 0;
			int j = i;
			do {
				present[j] = true;
				j = array[j];
				size++;
			} while (j != i);
			result[size]++;
		}
		return result;
	}
}
