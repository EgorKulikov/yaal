package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long upTo = in.readLong();
		int power = in.readInt();
		long answer = 0;
		int current = 0;
		int last = 0;
		int mod = (1 << power) - 1;
		for (int i = 1; i < power; i++) {
			for (int j = last + 1; j < (last + 1) * 10 && j <= upTo; j++) {
				for (int k = 0; k < i; k++) current *= 10;
				current += j;
				current &= mod;
				if (current == 0) answer++;
			}
			last = last * 10 + 9;
		}
		if (upTo > last) answer += (upTo - last + (1 << power) / 2 - 1) >> power;
		out.printLine(answer);
    }
}
