package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        int b = in.readInt();
        int n = in.readInt();
        int t = in.readInt();
        long current = 1;
        for (int i = 0; i < n; i++) {
            current = current * k + b;
            if (current > t) {
                out.printLine(n - i);
                return;
            }
        }
        out.printLine(0);
	}
}
