package net.egork;

import net.egork.misc.MiscUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(MiscUtils.convertToRoman(in.readInt()));
	}
}
