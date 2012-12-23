package on2012_11.on2012_11_01_November_Challenge_2012.Delicious_Dishes;



import net.egork.numbers.IntegerUtils;
import net.egork.numbers.NumberIterator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DeliciousDishes {
	DeliciousIterator iterator = new DeliciousIterator();

	int[] mask = new int[100000];
	int[] mask2 = new int[100000];
	int[] bitCount = new int[1 << 10];

	{
		for (int i = 0; i < 100000; i++) {
			int ii = i;
			while (ii != 0) {
				int digit = ii % 10;
				if ((mask[i] >> digit & 1) == 1) {
					mask[i] = -1;
					break;
				}
				mask[i] += 1 << digit;
				ii /= 10;
			}
		}
		for (int i = 0; i < 100000; i++) {
			int ii = i;
			for (int j = 0; j < 5; j++) {
				int digit = ii % 10;
				if ((mask2[i] >> digit & 1) == 1) {
					mask2[i] = -1;
					break;
				}
				mask2[i] += 1 << digit;
				ii /= 10;
			}
		}
		for (int i = 0; i < bitCount.length; i++)
			bitCount[i] = 10 - Integer.bitCount(i);
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long from = in.readLong();
		long to = in.readLong();
		if (from > 9876543210L) {
			out.printLine(0);
			return;
		}
		to = Math.min(to, 9876543210L);
		iterator.answer = 0;
		iterator.run(from, to);
		out.printLine(iterator.answer);
	}

	class DeliciousIterator extends NumberIterator {
		long[][] c = IntegerUtils.generateBinomialCoefficients(30);
		long[] factorial = IntegerUtils.generateFactorial(30, Long.MAX_VALUE);
		long answer = 0;

		@Override
		protected void process(long prefix, int remainingDigits) {
			if (prefix < 100000) {
				int m1 = mask[(int) prefix];
				if (m1 == -1)
					return;
				answer += c[bitCount[m1]][remainingDigits] * factorial[remainingDigits];
				return;
			}
			int m1 = mask2[((int) (prefix % 100000))];
			if (m1 == -1)
				return;
			int m2 = mask[((int) (prefix / 100000))];
			if (m2 == -1 || (m1 & m2) != 0)
				return;
			answer += c[bitCount[m1 + m2]][remainingDigits] * factorial[remainingDigits];
		}
	}
}
