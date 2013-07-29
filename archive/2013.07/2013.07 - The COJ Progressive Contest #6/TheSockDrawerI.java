package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheSockDrawerI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] number = IOUtils.readIntArray(in, count);
		out.printLine("Case", testNumber + ":", count + 1, ArrayUtils.maxElement(number) + 1);
    }
}
