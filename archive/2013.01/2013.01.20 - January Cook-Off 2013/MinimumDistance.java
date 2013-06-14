package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MinimumDistance {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int ds = in.readInt();
		int dt = in.readInt();
		int d = in.readInt();
		if (d > ds + dt)
			out.printLine(d - ds - dt);
		else if (Math.abs(ds - dt) > d)
			out.printLine(Math.abs(ds - dt) - d);
		else
			out.printLine(0);
    }
}
