package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ExponentiatingToAPrimePower {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int power = in.readInt();
		int modulo = in.readInt();
		out.printLine(IntegerUtils.power(ArrayUtils.sumArray(IOUtils.readIntArray(in, count)), power, modulo));
	}
}
