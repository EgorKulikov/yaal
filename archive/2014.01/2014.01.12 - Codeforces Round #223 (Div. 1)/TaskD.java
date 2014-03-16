package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);
	private long[] factorial;
	private long[] reverse;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] order = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(order);
		factorial = IntegerUtils.generateFactorial(count + 1, MOD);
		reverse = IntegerUtils.generateReverseFactorials(count + 1, MOD);
		if (ArrayUtils.count(order, -1) == count) {
			out.printLine(IntegerUtils.power(2, count - 1, MOD));
			return;
		}
		int last = count;
		int left = 0;
		int right = count - 1;
		int leftPosition = 0;
		int rightPosition = count - 1;
		long answer = 1;
		while (true) {
			while (order[left] == -1)
				left++;
			while (order[right] == -1)
				right--;
			if (order[left] >= last || order[right] >= last) {
				out.printLine(0);
				return;
			}
			if (left == right) {
				if (order[left] == 0) {
					answer = answer * c(rightPosition - leftPosition, left - leftPosition) % MOD;
					break;
				}
				int shouldTake = last - order[left] - 1;
				long total = 0;
				if (rightPosition - left <= shouldTake) {
					total += c(shouldTake, rightPosition - left) * IntegerUtils.power(2, order[left] - 1, MOD) % MOD;
				}
				if (left - leftPosition <= shouldTake)
					total += c(shouldTake, left - leftPosition) * IntegerUtils.power(2, order[left] - 1, MOD) % MOD;
				answer = answer * total % MOD;
				break;
			}
			if (order[left] > order[right]) {
				int shouldTake = last - order[left] - 1;
				int leftTake = left - leftPosition;
				int rightTake = shouldTake - leftTake;
				if (leftTake > shouldTake || rightTake > rightPosition - right) {
					out.printLine(0);
					return;
				}
				answer = answer * c(shouldTake, leftTake) % MOD;
				last = order[left];
				leftPosition = ++left;
				rightPosition -= rightTake;
			} else {
				int shouldTake = last - order[right] - 1;
				int rightTake = rightPosition - right;
				int leftTake = shouldTake - rightTake;
				if (rightTake > shouldTake || leftTake > left - leftPosition) {
					out.printLine(0);
					return;
				}
				answer = answer * c(shouldTake, rightTake) % MOD;
				last = order[right];
				rightPosition = --right;
				leftPosition += leftTake;
			}
		}
		out.printLine(answer);
    }

	private long c(int n, int m) {
		return factorial[n] * reverse[m] % MOD * reverse[n - m] % MOD;
	}
}
