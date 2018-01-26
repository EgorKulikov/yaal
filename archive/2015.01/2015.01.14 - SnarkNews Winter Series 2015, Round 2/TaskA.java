package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int angle = in.readInt();
		if (angle < 5) angle += 180;
		if (angle >= 185) angle -= 180;
		angle = (angle + 5) / 10;
		out.printLine(String.format("%02d", angle));
    }
}
