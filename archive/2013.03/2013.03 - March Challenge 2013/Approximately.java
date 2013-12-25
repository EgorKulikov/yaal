package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Approximately {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0) {
			out.printLine(3);
			return;
		}
		out.print("3.");
		int a = 103993 % 33102;
		int b = 33102;
		for (int i = 0; i < count; i++) {
			a *= 10;
			out.print(a / b);
			a %= b;
		}
		out.printLine();
    }
}
