package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DavidsMagic {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        long number = in.readLong();
        if (number == 0)
            throw new UnknownError();
        long left = 1;
        long right = 1 << 29;
        while (left < right) {
            long middle = (left + right) >> 1;
            if (middle * (middle + 1) > (number << 1))
                right = middle;
            else
                left = middle + 1;
        }
        out.printLine(left, ((left * (left + 1)) >> 1) - number);
	}
}
