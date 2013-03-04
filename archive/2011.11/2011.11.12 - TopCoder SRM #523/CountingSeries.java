package net.egork;

public class CountingSeries {
	public long countThem(long a, long b, long c, long d, long upperBound) {
		long answer = 0;
		if (upperBound >= a)
			answer += (upperBound - a) / b + 1;
		while (c <= upperBound) {
			if (c < a || (c - a) % b != 0)
				answer++;
			c *= d;
			if (d == 1)
				break;
		}
		return answer;
	}
}

