package on2011_11.on2011_10_4.taske;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.List;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		List<Long> divisors = IntegerUtils.getDivisors(in.readInt());
		long answer = 0;
		for (long first : divisors) {
			for (long second : divisors) {
				if (first <= second && IntegerUtils.gcd(first, second) == 1)
					answer++;
			}
		}
		out.printLine("Scenario #" + testNumber + ":");
		out.printLine(answer);
		out.printLine();
	}
}
