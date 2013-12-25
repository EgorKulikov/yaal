package net.egork;

public class BigFatInteger {
    public int minOperations(int A, int B) {
		int max = 0;
		int answer = 0;
		for (int i = 2; i <= A; i++) {
			if (A % i == 0) {
				int current = 0;
				answer++;
				do {
					current++;
					A /= i;
				} while (A % i == 0);
				max = Math.max(max, current);
			}
		}
		answer += Integer.bitCount(Integer.highestOneBit(2 * (B * max) - 1) - 1);
		return answer;
    }
}
