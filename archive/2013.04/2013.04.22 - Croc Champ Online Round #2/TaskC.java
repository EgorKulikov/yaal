package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readLong();
		if (count % 3 != 0) {
			out.printLine(0);
			return;
		}
		count /= 3;
		int answer = 0;
		for (long i = 2; i * i * i <= count; i++) {
			if (count % i != 0)
				continue;
			long remaining = count / i;
			long minJ = (long) Math.max(i, Math.sqrt(remaining) - i / 2 - 10);
			for (long j = minJ; j * j <= remaining; j++) {
				if (remaining % j != 0)
					continue;
				long k = remaining / j;
				long a = i + j - k;
				long b = i + k - j;
				long c = j + k - i;
				if (a < 2 || b < 2 || c < 2 || (a & 1) != 0)
					continue;
				if (a == b && a == c)
					answer++;
				else if (a == b || a == c || b == c)
					answer += 3;
				else
					answer += 6;
			}
		}
		out.printLine(answer);
    }
}
