package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PleaseLikeMe {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int required = in.readInt();
		int days = in.readInt();
		int start = in.readInt();
		int grow = in.readInt() + 1;
		for (int i = 1; i < days && required > start; i++)
			required = (required + grow - 1) / grow;
		if (required > start)
			out.printLine("DEAD AND ROTTING");
		else
			out.printLine("ALIVE AND KICKING");
    }
}
