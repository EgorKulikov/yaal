package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] a = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(a);
		int[] qty = new int[count];
		for (int i : a) qty[i]++;
		int sum = 0;
		for (int i : qty) sum += i & 1;
		if (sum > 1) {
			out.printLine(0);
			return;
		}
		int shift = 0;
		while (shift < count / 2) {
			if (a[shift] != a[count - shift - 1]) {
				break;
			}
			qty[a[shift]] -= 2;
			shift++;
		}
		if (shift == count / 2) {
			out.printLine((long)count * (count + 1) / 2);
			return;
		}
		int middle = count / 2;
		while (middle > shift) {
			if (a[middle - 1] != a[count - middle]) break;
			middle--;
		}
		int[] left = Arrays.copyOfRange(a, shift, middle);
		int[] right = Arrays.copyOfRange(a, count - middle, count - shift);
		ArrayUtils.sort(left, IntComparator.DEFAULT);
		ArrayUtils.sort(right, IntComparator.DEFAULT);
		if (Arrays.equals(left, right)) {
			out.printLine(2L * (shift + 1) * (count - middle + 1) - (shift + 1L) * (shift + 1));
			return;
		}
		long answer = 0;
		for (int j = 0; j < 2; j++) {
			int[] required = new int[count];
			int nonZero = 0;
			for (int i = 0; i < count; i++) {
				if ((required[i] = (qty[i] + 1) >> 1) != 0) nonZero++;
			}
			int end = shift;
			while (nonZero > 0) {
				if (--required[a[end++]] == 0) nonZero--;
			}
			answer += (shift + 1L) * (count - end + 1);
			ArrayUtils.reverse(a);
		}
		answer -= (shift + 1L) * (shift + 1);
		out.printLine(answer);
	}
}
