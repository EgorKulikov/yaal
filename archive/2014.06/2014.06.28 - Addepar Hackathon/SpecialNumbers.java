package net.egork;

import net.egork.collections.set.EHashSet;
import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class SpecialNumbers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] divisors = MultiplicativeFunction.DIVISOR_COUNT.calculateUpTo(count + 1);
		long answer = 0;
		Set<Long> set = new EHashSet<>();
		for (int i = 1; i <= count; i++) {
			if (set.contains(divisors[i])) {
				continue;
			}
			set.add(divisors[i]);
			answer += i;
		}
		out.printLine(answer);
    }
}
