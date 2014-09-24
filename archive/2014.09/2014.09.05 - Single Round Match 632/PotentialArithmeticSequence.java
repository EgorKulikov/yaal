package net.egork;

public class PotentialArithmeticSequence {
    public int numberOfSubsequences(int[] d) {
		int answer = 0;
		for (int i = 0; i < d.length; i++) {
			for (int j = i; j < d.length; j++) {
				boolean good = true;
				for (int k = 0; j - (1 << k) >= i; k++) {
					int at = j - (1 << k);
					if (Math.max(d[j], d[at]) >= k) {
						if (Math.min(d[j], d[at]) != k || Math.max(d[j], d[at]) == k) {
							good = false;
							break;
						}
					} else {
						if (d[j] != d[at]) {
							good = false;
							break;
						}
					}
				}
				if (!good) {
					break;
				}
				answer++;
			}
		}
		return answer;
    }
}
