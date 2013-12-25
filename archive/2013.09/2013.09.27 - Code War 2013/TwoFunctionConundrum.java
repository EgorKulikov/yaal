package net.egork;

import net.egork.numbers.FastFourierTransform;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TwoFunctionConundrum {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		long[] x = new long[first.length];
		for (int i = 0; i < first.length; i++)
			x[i] = first[first.length - i - 1] - '0';
		long[] y = new long[second.length];
		for (int i = 0; i < second.length; i++)
			y[i] = second[second.length - i - 1] - '0';
		long[] result = FastFourierTransform.multiply(x, y);
		long remaining = 0;
		for (int i = 0; i < result.length; i++) {
			remaining += result[i];
			result[i] = remaining % 10;
			remaining /= 10;
		}
		int i;
		for (i = result.length - 1; i > 0; i--) {
			if (result[i] != 0)
				break;
		}
		if (remaining > 0) {
			out.print(remaining);
			i = result.length - 1;
		}
		for (; i >= 0; i--)
			out.print(result[i]);
		out.printLine();
    }
}
