package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class Xorsequence {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long l = in.readLong();
		long r = in.readLong();
		long answer = calc(r) ^ calc(l - 1);
		out.printLine(answer);
	}

	private long calc(long limit) {
		switch ((int) (limit & 7)) {
		case 0:
			return limit;
		case 1:
			return limit;
		case 2:
			return 2;
		case 3:
			return 2;
		case 4:
			return limit + 2;
		case 5:
			return limit + 2;
		case 6:
			return 0;
		case 7:
			return 0;
		default:
			throw new RuntimeException();
		}
	}
}
