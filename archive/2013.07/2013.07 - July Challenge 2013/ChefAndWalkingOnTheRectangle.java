package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndWalkingOnTheRectangle {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int repeats = in.readInt();
		if (rowCount * columnCount <= 2)
			out.printLine(0);
		else if (rowCount == 1 || columnCount == 1)
			out.printLine(repeats);
		else
			out.printLine((repeats + 1) >> 1);
    }
}
