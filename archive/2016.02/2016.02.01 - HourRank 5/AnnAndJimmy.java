package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class AnnAndJimmy {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int answer = (n / 3) * ((n + 1) / 3) * ((n + 2) / 3);
		out.printLine(answer);
	}
}
