package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int euro = in.readInt();
		int cents = in.readInt();
		out.printLine(cents, 0, 0, 0, 0, 0, euro, 0, 0, 0, 0, 0, 0);
    }
}
