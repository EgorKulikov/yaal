package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int square = in.readInt();
		int root = (int) Math.round(Math.sqrt(square));
		if (root * (root - 1) == square) {
			out.printLine(root - 1, root);
		} else if (root * (root + 1) == square) {
			out.printLine(root, root + 1);
		} else {
			out.printLine(-1, -1);
		}
    }
}
