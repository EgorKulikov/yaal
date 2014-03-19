package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndFavouriteSequence {
	int[] at;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int count = in.readInt();
		int[] left = new int[count];
		int[] right = new int[count];
		IOUtils.readIntArrays(in, left, right);
		MiscUtils.decreaseByOne(left, right);
		at = ArrayUtils.createOrder(size + 1);
		int[] order = ArrayUtils.order(right);
		int exponent = 0;
		for (int i : order) {
			int end = get(left[i]);
			if (end == right[i] + 1)
				continue;
			exponent++;
			at[end] = right[i] + 1;
		}
		out.printLine(IntegerUtils.power(2, exponent, 1000000007));
	}

	private int get(int i) {
		if (at[i] == i)
			return i;
		return at[i] = get(at[i]);
	}
}
