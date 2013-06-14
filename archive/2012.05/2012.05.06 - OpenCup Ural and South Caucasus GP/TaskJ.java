package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long first = in.readLong();
		boolean odd = first % 2 == 1;
		long limit = in.readLong();
		List<Long> divisors = new ArrayList<Long>();
		for (long i = 2; i * i <= first; i++) {
			if (first % i == 0) {
				divisors.add(i);
				do {
					first /= i;
				} while (first % i == 0);
			}
		}
		if (first != 1)
			divisors.add(first);
		long answer = 0;
		int divisorCount = divisors.size();
		int count = 1 << divisorCount;
		for (int i = 1; i < count; i++) {
			long curDivisor = 1;
			for (int j = 0; j < divisorCount; j++) {
				if ((i >> j & 1) != 0)
					curDivisor *= divisors.get(j);
			}
			long delta = limit / curDivisor;
			if ((Integer.bitCount(i) & 1) == 1)
				answer += delta;
			else
				answer -= delta;
			if (odd) {
				delta = (delta + 1) >> 1;
				if ((Integer.bitCount(i) & 1) == 1)
					answer -= delta;
				else
					answer += delta;
			}
		}
		if (odd)
			answer += (limit + 1) >> 1;
		out.printLine(answer);
	}
}
