package net.egork;

public class SequenceTransmission {
	public long signalChanges(long a, long b, long n) {
		long answer = 0;
		if (a % 2 == 0 && b % 2 == 0)
			answer += n;
		else if (a % 2 == 1 && b % 2 == 0)
			answer += n / 2;
		else if (a % 2 == 1 && b % 2 == 1)
			answer += (n + 1) / 2;
		if ((a * n + b) % 2 == 0)
			answer--;
		b += a;
		for (int i = 0; i <= 60; i++) {
			long period = 1L << (i + 2);
			long fullPeriodCount = n / period;
			long prePeriod = n % period;
			long current = 0;
			long total = 0;
			long mask = (1L << i) - 1;
			long checkMask = (1L << i) + (1L << (i + 1));
			while (current < period && (fullPeriodCount != 0 || current < prePeriod)) {
				long curValue = a * current + b;
				long next = curValue - (curValue & mask) + (1L << i);
				long step = 1;
				step = Math.max((next - curValue + a - 1) / a, step);
				if (current < prePeriod && current + step > prePeriod)
					step = prePeriod - current;
				step = Math.min(step, period - current);
				long checkValue = (curValue & checkMask) >> i;
				if (checkValue == 1 || checkValue == 2)
					total += step;
				current += step;
				if (current == prePeriod)
					answer += total;
			}
			answer += fullPeriodCount * total;
		}
		return answer - n;
	}

}

