package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int euro = in.readInt();
		int cents = in.readInt();
		out.printLine(cents, 0, 0, 0, 0, 0, euro, 0, 0, 0, 0, 0, 0);
    }
}
