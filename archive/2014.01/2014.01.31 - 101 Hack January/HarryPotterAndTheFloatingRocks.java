package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HarryPotterAndTheFloatingRocks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x0 = in.readInt();
		int y0 = in.readInt();
		int x1 = in.readInt();
		int y1 = in.readInt();
		int dx = x0 - x1;
		int dy = y0 - y1;
		out.printLine(IntegerUtils.gcd(dx, dy) - 1);
    }
}
