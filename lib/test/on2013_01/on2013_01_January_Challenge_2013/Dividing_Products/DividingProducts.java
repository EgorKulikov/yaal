package on2013_01.on2013_01_January_Challenge_2013.Dividing_Products;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DividingProducts {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int testCount = in.readInt();
		int[] digits = new int[testCount];
		long[] number = new long[testCount];
		for (int i = 0; i < testCount; i++) {
			digits[i] = in.readInt();
			number[i] = in.readLong();
		}
		int[] answer = new int[testCount];
		int[] order = ArrayUtils.order(digits);
		int twoMultiplier = 37 * 37 * 73;
		int threeMultiplier = 37 * 37;
		int fiveMultiplier = 37;
		int special = 37 * 37 * 73 * 109;
		int[] current = new int[special + 1];
		int base = 54 * twoMultiplier + 36 * threeMultiplier + 18 * fiveMultiplier + 18;
		current[base] = 1;
		int k = 0;
		for (int i = 1; i <= 36; i++) {
			if (i % 2 == 1) {
				current[special] *= 10;
				for (int j = special - 1; j >= 0; j--) {
					if (current[j] == 0)
						continue;
					current[j + twoMultiplier] += current[j];
					current[j + threeMultiplier] += current[j];
					current[j + twoMultiplier * 2] += current[j];
					current[j + fiveMultiplier] += current[j];
					current[j + twoMultiplier + threeMultiplier] += current[j];
					current[j + 1] += current[j];
					current[j + twoMultiplier * 3] += current[j];
					current[j + threeMultiplier * 2] += current[j];
					if (i > 1)
						current[special] += current[j];
				}
			} else {
				current[special] *= 9;
				for (int j = 0; j < special; j++) {
					if (current[j] == 0)
						continue;
					current[j - twoMultiplier] += current[j];
					current[j - threeMultiplier] += current[j];
					current[j - twoMultiplier * 2] += current[j];
					current[j - fiveMultiplier] += current[j];
					current[j - twoMultiplier - threeMultiplier] += current[j];
					current[j - 1] += current[j];
					current[j - twoMultiplier * 3] += current[j];
					current[j - threeMultiplier * 2] += current[j];
				}
			}
			while (k < testCount && digits[order[k]] == i) {
				long curNumber = number[order[k]];
				if (curNumber == 0)
					answer[order[k]] = current[special];
				else {
					int index = base;
					int q1 = 0;
					while (curNumber % 2 == 0) {
						index += twoMultiplier;
						curNumber /= 2;
						q1++;
					}
					int q2 = 0;
					while (curNumber % 3 == 0) {
						index += threeMultiplier;
						curNumber /= 3;
						q2++;
					}
					int q3 = 0;
					while (curNumber % 5 == 0) {
						index += fiveMultiplier;
						curNumber /= 5;
						q3++;
					}
					int q4 = 0;
					while (curNumber % 7 == 0) {
						index++;
						curNumber /= 7;
						q4++;
					}
					if (curNumber == 1 && q1 <= 54 && q2 <= 36 && q3 <= 18 && q4 <= 18)
						answer[order[k]] = current[index];
				}
				k++;
			}
		}
		for (int i : answer) {
			if (i >= 0)
				out.printLine(i);
			else
				out.printLine((1L << 32) + i);
		}
    }
}
