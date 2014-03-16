package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	IntSet bad = new IntHashSet();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int badCount = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		int[] badPrimes = IOUtils.readIntArray(in, badCount);
		for (int i : badPrimes)
			bad.add(i);
		int[] gcd = new int[count];
		gcd[0] = numbers[0];
		for (int i = 1; i < count; i++)
			gcd[i] = IntegerUtils.gcd(gcd[i - 1], numbers[i]);
		int current = 1;
		int answer = 0;
		for (int i = count - 1; i >= 0; i--) {
			int candidate = gcd[i] / current;
			if (score(candidate) < 0)
				current = gcd[i];
			answer += score(numbers[i] / current);
		}
		out.printLine(answer);
    }

	private int score(int number) {
		int result = 0;
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				int delta = bad.contains(i) ? -1 : 1;
				do {
					number /= i;
					result += delta;
				} while (number % i == 0);
			}
		}
		if (number > 1) {
			if (bad.contains(number))
				result--;
			else
				result++;
		}
		return result;
	}
}
