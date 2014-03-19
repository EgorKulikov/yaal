package net.egork;

import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int limit = in.readInt();
		int solutions = in.readInt();
		if (solutions % 2 != 1) {
			out.printLine(0);
			return;
		}
		solutions /= 2;
		if (solutions == 0) {
			out.printLine(-1);
			return;
		}
		long answer = 0;
		long[] phi = MultiplicativeFunction.PHI.calculateUpTo(limit + 1);
		for (int i = 1; i <= limit; i++) {
			if (limit / i != solutions)
				continue;
			answer += 2 * phi[i];
			if (i == 1)
				answer--;
		}
		answer *= 2;
		if (solutions == limit)
			answer++;
		if (answer > 1e12)
			out.printLine(-1);
		else
			out.printLine(answer);
    }
}
