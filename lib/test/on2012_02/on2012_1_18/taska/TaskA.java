package on2012_02.on2012_1_18.taska;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;
import java.util.List;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		List<Long> divisors = IntegerUtils.getDivisors(n);
		Collections.sort(divisors);
		boolean[] winner = new boolean[divisors.size()];
		winner[0] = true;
		long move = 0;
		for (int i = 1; i < winner.length; i++) {
			boolean found = false;
			for (int j = 1; j < i; j++) {
				if (divisors.get(i) % divisors.get(j) == 0) {
					found = true;
					if (!winner[j]) {
						winner[i] = true;
						move = divisors.get(j);
					}
				}
			}
			if (!found)
				winner[i] = true;
		}
		if (winner[winner.length - 1]) {
			out.printLine(1);
			out.printLine(move);
		} else
			out.printLine(2);
	}
}
