package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Flooring {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readLong();
		int modulo = in.readInt();
		int realModulo = modulo * 30;
		long last = 0;
		long lastSum = 0;
		long answer = 0;
		for (long i = 1; i * i <= count; i++) {
			long power = i * i % realModulo * i % realModulo * i * 30 % realModulo;
			answer += power * (count / i) % realModulo;
			last = i;
			lastSum += power;
		}
		lastSum %= realModulo;
		for (long i = last; i >= 1; i--) {
			long current = count / i;
			if (current <= last) {
				continue;
			}
			long curSum = sum(current, realModulo);
			answer += i * (curSum - lastSum) % realModulo;
			last = current;
			lastSum = curSum;
		}
		answer %= realModulo;
		if (answer < 0) {
			answer += realModulo;
		}
		answer /= 30;
		out.printLine(answer);
	}

	private long sum(long upTo, int modulo) {
		upTo %= modulo;
		return upTo * (upTo + 1) % modulo * (2 * upTo + 1) % modulo * (3 * upTo * upTo % modulo + 3 * upTo - 1) % modulo;
	}
}
