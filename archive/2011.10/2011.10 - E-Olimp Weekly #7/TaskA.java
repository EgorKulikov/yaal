package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int stringIndex = in.readInt();
		int letterIndex = in.readInt();
		long[] fibonacci = IntegerUtils.generateFibonacci(1000000000);
		if (stringIndex < fibonacci.length && letterIndex > fibonacci[stringIndex]) {
			out.println("No solution");
			return;
		}
		if (stringIndex >= fibonacci.length) {
			if (stringIndex % 2 == fibonacci.length % 2)
				stringIndex = fibonacci.length - 2;
			else
				stringIndex = fibonacci.length - 1;
		}
		while (stringIndex > 2) {
			if (letterIndex <= fibonacci[stringIndex - 2])
				stringIndex -= 2;
			else {
				letterIndex -= fibonacci[stringIndex - 2];
				stringIndex--;
			}
		}
		out.println((char)('a' + stringIndex + letterIndex - 2));
	}
}
