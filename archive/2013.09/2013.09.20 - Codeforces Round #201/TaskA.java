package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		Arrays.sort(numbers);
		int gcd = 0;
		for (int i : numbers)
			gcd = IntegerUtils.gcd(gcd, i);
		int moves = ArrayUtils.maxElement(numbers) / gcd - count;
		if ((moves & 1) == 0)
			out.printLine("Bob");
		else
			out.printLine("Alice");
    }
}
