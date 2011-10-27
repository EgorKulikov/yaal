package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int index = in.readInt() - 1;
		int tail = -1;
		for (int i = 0; i <= count; i++) {
			if (IntegerUtils.factorial(i) > index) {
				tail = i;
				break;
			}
		}
		if (tail == -1) {
			out.println(-1);
			return;
		}
		long[] happy = generateHappy();
		int answer = Arrays.binarySearch(happy, count - tail);
		if (answer < 0)
			answer = -answer - 1;
		else
			answer++;
		boolean[] taken = new boolean[tail];
		int start = count - tail + 1;
		for (int i = 0; i < tail; i++) {
			long factorial = IntegerUtils.factorial(tail - i - 1);
			for (int j = 0; j < tail; j++) {
				if (!taken[j]) {
					if (factorial > index) {
						taken[j] = true;
						if (Arrays.binarySearch(happy, start + i) >= 0 && Arrays.binarySearch(happy, start + j) >= 0)
							answer++;
						break;
					} else
						index -= factorial;
				}
			}
		}
		out.println(answer);
	}

	private long[] generateHappy() {
		long[] happy = new long[(1 << 10) - 2];
		happy[0] = 4;
		happy[1] = 7;
		int first = 0;
		int last = 2;
		for (int i = 2; i <= 9; i++) {
			for (int j = 0; j < last - first; j++) {
				happy[last + 2 * j] = 10 * happy[first + j] + 4;
				happy[last + 2 * j + 1] = 10 * happy[first + j] + 7;
			}
			int next = last + 2 * (last - first);
			first = last;
			last = next;
		}
		return happy;
	}
}
