package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Sequence {
	String sequence = "1 10 100 101 1000 1001 101";

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readLong();
		if (count == 0) {
			out.printLine(0);
			return;
		}
		long result = solve(count);
		out.printLine(result);
	}

	public static long solve(long count) {
		long[] fibonacciTwoOnes = IntegerUtils.generateFibonacci(count + 10);
		long[] fibonacci = new long[fibonacciTwoOnes.length - 1];
		System.arraycopy(fibonacciTwoOnes, 1, fibonacci, 0, fibonacci.length);
		long[] countOnes = new long[fibonacci.length];
		countOnes[0] = 0;
		countOnes[1] = 1;
		for (int i = 2; i < countOnes.length; i++)
			countOnes[i] = countOnes[i - 1] + countOnes[i - 2] + fibonacci[i - 2];
		long upTo = 2;
		long remaining = count - 1;
		for (int i = 1; i < fibonacci.length; i++) {
			long current = fibonacci[i - 1] * (i + 1);
			if (current > remaining) {
				upTo += remaining / (i + 1);
				remaining %= i + 1;
				break;
			} else {
				upTo += fibonacci[i - 1];
				remaining -= current;
			}
		}
		long result = 0;
		long last = upTo;
		while (upTo != 0) {
			for (int i = 0; i < fibonacci.length; i++) {
				if (upTo < fibonacci[i]) {
					result += countOnes[i - 1];
					upTo -= fibonacci[i - 1];
					result += upTo;
					break;
				}
			}
		}
		StringBuilder builder = convert(fibonacci, last);
		for (int i = 0; i < remaining; i++) {
			if (builder.charAt(i) == '1')
				result++;
		}
		return result;
	}

	private static StringBuilder convert(long[] fibonacci, long last) {
		StringBuilder builder = new StringBuilder();
		for (int i = fibonacci.length - 1; i >= 0; i--) {
			if (last >= fibonacci[i]) {
				builder.append(1);
				last -= fibonacci[i];
			} else if (builder.length() != 0)
				builder.append(0);
		}
		return builder;
	}

	public static void main(String[] args) {
		long[] fibonacciTwoOnes = IntegerUtils.generateFibonacci(20, -1);
		long[] fibonacci = new long[fibonacciTwoOnes.length - 1];
		System.arraycopy(fibonacciTwoOnes, 1, fibonacci, 0, fibonacci.length);
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i <= 1000; i++)
			builder.append(convert(fibonacci, i));
		long total = 0;
		for (int i = 1; i <= builder.length(); i++) {
			if (builder.charAt(i - 1) == '1')
				total++;
			if (total != solve(i))
				throw new RuntimeException();
		}
	}
}
