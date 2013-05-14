package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int a[] = IOUtils.readIntArray(in, n);
		int b[] = IOUtils.readIntArray(in, n);
		if (n == 1) {
			out.printLine(-1);
			return;
		}
		boolean zero = true;
		for (int i = 0; i < n; i++) {
			if (a[i] == b[i]) {
				zero = false;
				break;
			}
		}
		if (zero) {
			out.printLine(0);
		} else {
			out.printLine(1);
			int mem = b[n - 1];
			for (int i = n - 1; i > 0; i--) {
				if (a[i] == mem || b[i - 1] == a[i - 1]) {
					b[i] = b[i - 1];
				} else {
					b[i] = mem;
					mem = b[i - 1];
				}
			}
			b[0] = mem;
		}
		out.printLine(b);
    }
}
