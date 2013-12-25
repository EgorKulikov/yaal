package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int changes = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		long left = 0;
		long right = 0;
		for (int i = 1; i < count; i++)
			right = Math.max(right, Math.abs(array[i] - array[i - 1]));
		int[] min = new int[count];
		while (left < right) {
			long middle = (left + right) >> 1;
			min[0] = 0;
			for (int i = 1; i < count; i++) {
				min[i] = i;
				long delta = 0;
				for (int j = i - 1; j >= 0; j--) {
					delta += middle;
					if (Math.abs(array[i] - array[j]) <= delta)
						min[i] = Math.min(min[i], min[j] + i - j - 1);
				}
			}
			boolean ok = false;
			for (int i = 0; i < count; i++) {
				if (min[i] + count - 1 - i <= changes) {
					ok = true;
					break;
				}
			}
			if (ok)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(left);
	}
}
