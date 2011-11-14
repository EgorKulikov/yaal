package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskD {
	int cnt1 = 0;
	int cnt2 = 0;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readInt();
		int size = (int) (count / 2);
		int multiplier = 800;
		count *= multiplier;
		long height = 0;
		long height0 = 0;
		long delta0 = 0;
		long stepMeet = 0;
		boolean firstMeet = false;
		long step = 0;
		boolean answerFound = false;
		long answer = -1;
		for (int i = 0; i < size; i++) {
//			char c = in.readCharacter();
			char c;
			if (i < size / 2)
				c = 'f';
			else
				c = '0';
			if (Character.isDigit(c))
				c -= '0';
			else
				c -= 'a' - 10;
			for (int j = 3; j >= 0; j--) {
				int bit;
				if ((c >> j & 1) == 0)
					bit = 0;
				else
					bit = 1;
				if (firstMeet && !answerFound) {
					if (f2(step + multiplier, height + (2 * bit - 1) * multiplier, count, height0, delta0, stepMeet) < 0) {
						step += multiplier;
						height += (2 * bit - 1) * multiplier;
						continue;
					}
				} else if (!answerFound) {
					if (f(step + multiplier, height + (2 * bit - 1) * multiplier, count) < 0) {
						step += multiplier;
						height += (2 * bit - 1) * multiplier;
						continue;
					}
				} else
					continue;
				for (int k = 0; k < multiplier; k++) {
					step++;
					if (bit == 1)
						height++;
					else
						height--;
					if (firstMeet && !answerFound) {
						if ((height - height0) % 2 != 0 && height < height0)
							continue;
						long delta0s = ((step - stepMeet) - Math.abs(height - height0)) / 2;
						long delta1s;
						if (height > height0)
							delta1s = count - height - delta0 - delta0s;
						else
							delta1s = count - height0 - delta0 - delta0s;
						long left;
						if (height > height0)
							left = height - height0 + 3 * delta0s / 2;
						else
							left = (height0 - height) / 2  + 3 * delta0s / 2;
						if (left == height0 + 3 * delta0 + 2 * height + 3 * delta1s) {
							answerFound = true;
							answer = 3 * height0 + 6 * delta0 + 2 * height + 3 * delta1s;
						}
					} else if (!answerFound) {
						delta0 = (step - height) / 2;
						if (f(step, height, count) == 0) {
							firstMeet = true;
							height0 = height;
							stepMeet = step;
						}
					}
				}
			}
		}
		if (!answerFound) {
			return;
		}
		long denominator = multiplier;
		long gcd = BigInteger.valueOf(answer).gcd(BigInteger.valueOf(denominator)).longValue();
		answer /= gcd;
		denominator /= gcd;
		out.printLine(answer + "/" + denominator);
	}

	private long f(long step, long height, long count) {
		long delta0 = (step - height) / 2;
		long delta1 = count - height - delta0;
		return 2 * height + 3 * delta0 - (height + 3 * delta1 / 2);
	}

	private long f2(long step, long height, long count, long height0, long delta0, long stepMeet) {
		if (((height - height0) & 1) != 0 && height < height0)
			return -1;
		long delta0s = ((step - stepMeet) - Math.abs(height - height0)) >> 1;
		long delta1s;
		if (height > height0)
			delta1s = count - height - delta0 - delta0s;
		else
			delta1s = count - height0 - delta0 - delta0s;
		long left;
		if (height > height0)
			left = height - height0 + ((3 * delta0s) >> 1);
		else
			left = ((height0 - height) + 3 * delta0s) >> 1;
		return left - (height0 + 3 * delta0 + 2 * height + 3 * delta1s);
	}
}
