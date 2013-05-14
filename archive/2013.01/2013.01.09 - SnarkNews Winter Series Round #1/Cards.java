package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class Cards {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int width = in.readInt();
		int height = in.readInt();
		int square = count * width * height;
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i * i <= square; i++) {
			if (square % i != 0)
				continue;
			int j = square / i;
			if (can(i, j, width, height))
				answer = 2 * (i + j);
		}
		out.printLine(answer);
    }

	private boolean can(int totalWidth, int totalHeight, int width, int height) {
		int g = IntegerUtils.gcd(width, height);
		if (totalWidth % g != 0 || totalHeight % g != 0)
			return false;
		totalWidth /= g;
		totalHeight /= g;
		width /= g;
		height /= g;
		if (totalWidth % width == 0 && totalHeight % height == 0)
			return true;
		if (totalWidth % height == 0 && totalHeight % width == 0)
			return true;
		if (totalHeight % (width * height) == 0) {
			int temp = totalWidth;
			totalWidth = totalHeight;
			totalHeight = temp;
		}
		if (totalWidth % (width * height) == 0) {
			int x = BigInteger.valueOf(width).modInverse(BigInteger.valueOf(height)).intValue();
			long y = (long)totalHeight * x % height;
			return y * width <= totalHeight;
		}
		return false;
	}
}
