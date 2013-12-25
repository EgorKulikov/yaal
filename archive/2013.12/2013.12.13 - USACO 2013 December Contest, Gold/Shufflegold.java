package net.egork;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Shufflegold {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int total = in.readInt();
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] permutation = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(permutation);
		int[] reverse = ArrayUtils.reversePermutation(permutation);
		int[] start = new int[count];
		int current = 0;
		boolean[] taken = new boolean[count];
		for (int i = 0; i < count; i++) {
			start[i] = current < count ? reverse[current] : current;
			if (start[i] < count)
				taken[start[i]] = true;
			current = start[i] + 1;
		}
		int[] special = new int[count];
		System.arraycopy(reverse, 1, special, 0, count - 1);
		special[count - 1] = reverse[0];
		int[] temp = new int[count];
		int[] end = new int[count];
		power(end, temp, special, total - count + 1);
		System.arraycopy(end, 0, special, 1, count - 1);
		special[0] = end[count - 1];
		end = special;
		current = count - 1;
		int value = total - 1;
		for (int i = 0; i <= total - count && current >= 0; i++) {
			current = permutation[current];
			end[current--] = value--;
		}
		for (int i = 0; i < queryCount; i++) {
			int at = in.readInt();
			if (at <= count) {
				out.printLine(end[count - at] + 1);
				continue;
			}
			at = total - at;
			if (at < count) {
				out.printLine(start[at] + 1);
				continue;
			}
			out.printLine(start[count - 1] + at - count + 2);
		}
    }

	private void power(int[] result, int[] temp, int[] base, int exponent) {
		if (exponent == 0) {
			for (int i = 0; i < result.length; i++)
				result[i] = i;
			return;
		}
		if ((exponent & 1) == 1) {
			power(temp, result, base, exponent - 1);
			multiply(result, temp, base);
		} else {
			power(temp, result, base, exponent >> 1);
			multiply(result, temp, temp);
		}
	}

	private void multiply(int[] result, int[] first, int[] second) {
		for (int i = 0; i < result.length; i++)
			result[i] = first[second[i]];
	}
}
