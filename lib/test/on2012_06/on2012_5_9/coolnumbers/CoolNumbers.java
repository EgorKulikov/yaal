package on2012_06.on2012_5_9.coolnumbers;



import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class CoolNumbers {
	long[] prime = new long[100];
	int[] power = new int[100];

	int[][] number = new int[100][68];
	int[] length = new int[100];

	static class BigInteger implements Comparable<BigInteger> {
		int[] digits;
		int length;

		BigInteger(int[] digits) {
			this(digits, digits.length);
		}

		BigInteger(int[] digits, int length) {
			this.digits = digits;
			this.length = length;
		}

		BigInteger max(BigInteger other) {
			if (compareTo(other) >= 0)
				return this;
			return other;
		}

		BigInteger min(BigInteger other) {
			if (compareTo(other) <= 0)
				return this;
			return other;
		}

		public int compareTo(BigInteger o) {
			if (length != o.length)
				return length - o.length;
			for (int i = length - 1; i >= 0; i--) {
				if (digits[i] != o.digits[i])
					return digits[i] - o.digits[i];
			}
			return 0;
		}

		void next() {
			int count = 0;
			for (int i = length - 1; i >= 0; i--) {
				if (digits[i] != 0)
					count++;
				if (count == 3) {
					digits[i]++;
					for (int j = i; j < length - 1; j++) {
						if (digits[j] == 10) {
							digits[j] = 0;
							digits[j + 1]++;
						} else
							break;
					}
					Arrays.fill(digits, 0, i, 0);
					if (digits[length - 1] == 10) {
						digits[length - 1] = 0;
						digits[length++] = 1;
					}
					return;
				}
			}
			digits[0]++;
			for (int j = 0; j < length - 1; j++) {
				if (digits[j] == 10) {
					digits[j] = 0;
					digits[j + 1]++;
				} else
					break;
			}
			if (digits[length - 1] == 10) {
				digits[length - 1] = 0;
				digits[length++] = 1;
			}
		}

		void print(OutputWriter out) {
			for (int i = length - 1; i >= 0; i--)
				out.print(digits[i]);
		}

		public void previous() {
			int count = 0;
			for (int i = length - 1; i >= 0; i--) {
				if (digits[i] != 0)
					count++;
				if (count > 3)
					digits[i] = 0;
			}
		}
	}

	private void go(int step, int base, int need) {
		if (step == count) {
			int sumDigits = 0;
			for (int i = 0; i < length[step]; i++)
				sumDigits += sumDigs[number[step][i]];
			if (sumDigits < base + need || sumDigits - base > 27)
				return;
			int[] curNumber = new int[4 * length[step]];
			for (int i = 0; i < length[step]; i++) {
				curNumber[4 * i] = number[step][i] % 10;
				curNumber[4 * i + 1] = number[step][i] % 100 / 10;
				curNumber[4 * i + 2] = number[step][i] % 1000 / 100;
				curNumber[4 * i + 3] = number[step][i] / 1000;
			}
			int curLength = curNumber.length;
			while (curNumber[curLength - 1] == 0)
				curLength--;
			BigInteger result = new BigInteger(curNumber, curLength);
			int sumOther = 0;
			int firstMask = 0;
			for (int j = 0; j < curLength; j++) {
				int firstDigit = curNumber[j];
				if ((firstMask >> firstDigit & 1) != 0)
					continue;
				firstMask += 1 << firstDigit;
				sumOther += firstDigit;
				if (sumDigits - sumOther == base) {
					addNumber(result);
//					result.add(number);
					return;
				}
				if (sumDigits - sumOther < base || sumDigits - sumOther > base + 18) {
					sumOther -= firstDigit;
					continue;
				}
				int secondMask = 0;
				for (int k = j + 1; k < curLength; k++) {
					int secondDigit = curNumber[k];
					if ((secondMask >> secondDigit & 1) != 0)
						continue;
					secondMask += 1 << secondDigit;
					sumOther += secondDigit;
					if (sumDigits - sumOther == base) {
						addNumber(result);
						return;
					}
					if (sumDigits - sumOther < base || secondDigit - sumOther > 9) {
						sumOther -= secondDigit;
						continue;
					}
					int thirdMask = 0;
					for (int l = k + 1; l < curLength; l++) {
						int thirdDigit = curNumber[l];
						if ((thirdMask >> thirdDigit & 1) != 0)
							continue;
						thirdMask += 1 << thirdDigit;
						sumOther += thirdDigit;
						if (sumDigits - sumOther == base) {
							addNumber(result);
							return;
						}
						sumOther -= thirdDigit;
					}
					sumOther -= secondDigit;
				}
				sumOther -= firstDigit;
			}
			return;
		}
		int power = this.power[step];
		System.arraycopy(number[step], 0, number[step + 1], 0, length[step]);
		length[step + 1] = length[step];
		for (int i = 0; ; i++) {
			go(step + 1, base, Math.max(need, (i + power - 1) / power));
			if (i == power * 27)
				return;
			int remainder = 0;
			for (int j = 0; j < length[step + 1]; j++) {
				remainder += number[step + 1][j] * prime[step];
				number[step + 1][j] = remainder % 10000;
				remainder /= 10000;
			}
			while (remainder != 0) {
				number[step + 1][length[step + 1]++] = remainder % 10000;
				remainder /= 10000;
			}
		}
	}

	private void addNumber(BigInteger number) {
		result.add(number);
	}

	int count;
	NavigableSet<BigInteger> result = new TreeSet<BigInteger>();
	BigInteger[] cool;
	int[] start = new int[80];

	int[] sumDigs = new int[10000];

	{
		long time = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			int iCopy = i;
			while (iCopy != 0) {
				sumDigs[i] += iCopy % 10;
				iCopy /= 10;
			}
		}
		int[] good = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 98, 99, 100, 102, 103, 104, 105, 106, 108, 109, 110, 111, 112, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 158, 159, 161, 162, 163, 164, 165, 166, 168, 169, 170, 171, 172, 174, 175, 176, 177, 178, 179, 180, 182, 183, 184, 185, 186, 187, 188, 189, 190, 192, 194, 195, 196, 198, 199, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 242, 243, 244, 245, 246, 247, 248, 251, 252, 253, 254, 255, 256, 261, 262, 264, 265, 266, 268, 269, 271, 272, 273, 275, 279, 282, 286, 287, 288, 297, 301, 302, 304, 306, 314, 315, 325};
		for (int i : good) {
			List<Pair<Long,Integer>> list = IntegerUtils.factorize(i);
			count = list.size();
			for (int j = 0; j < count; j++) {
				prime[j] = list.get(j).first;
				power[j] = list.get(j).second;
			}
			length[0] = 1;
			number[0][0] = 1;
			go(0, i, 0);
		}
		cool = result.toArray(new BigInteger[result.size()]);
		int curLength = 0;
		for (int i = 0; i < cool.length; i++) {
			while (curLength <= cool[i].length)
				start[curLength++] = i;
		}
		for (; curLength < 80; curLength++)
			start[curLength] = cool.length;
		System.err.println("Init " + (System.currentTimeMillis() - time));
		System.err.println(cool.length);
	}

	BigInteger curNumber = new BigInteger(new int[1001]);
	BigInteger previous = new BigInteger(new int[1001]);
	int[] input = new int[1001];

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = 0;
		while (true) {
			int next = in.read();
			if (Character.isDigit(next)) {
				input[length++] = next - '0';
				break;
			}
		}
		while (true) {
			int next = in.read();
			if (!Character.isDigit(next))
				break;
			input[length++] = next - '0';
		}
		for (int i = 0; i < length; i++)
			curNumber.digits[i] = input[length - i - 1];
		curNumber.length = length;
		System.arraycopy(curNumber.digits, 0, previous.digits, 0, length);
		previous.length = length;
		previous.previous();
		if (length >= 70 || length <= 3) {
			previous.print(out);
			out.print(' ');
			curNumber.next();
			curNumber.print(out);
			out.printLine();
			return;
		}
		int index = Arrays.binarySearch(cool, start[length], start[length + 1], curNumber);
		if (index < 0)
			index = -index - 2;
		BigInteger first = cool[index].max(previous);
		curNumber.next();
		BigInteger second = curNumber;
		if (index < cool.length - 1)
			second = second.min(cool[index + 1]);
		first.print(out);
		out.print(' ');
		second.print(out);
		out.printLine();
	}
}