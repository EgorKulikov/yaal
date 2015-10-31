package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		char[] type = IOUtils.readCharArray(in, n);
		int q = in.readInt();
		for (int i = 0; i < q; i++) {
			long position = in.readLong() - 1;
			boolean revert = false;
			for (int j = 0; j < n; j++) {
				long threshold = (1L << (n - j - 1));
				if (position == threshold - 1) {
					out.printLine(revert ? 'U' : 'D');
					break;
				}
				if (type[j] == 'L') {
					if (position < threshold) {
						position = threshold - position - 2;
						revert = !revert;
					} else {
						position -= threshold;
					}
				} else {
					if (position >= threshold) {
						position = 2 * threshold - position - 2;
						revert = !revert;
					}
				}
			}
		}
	}
}
