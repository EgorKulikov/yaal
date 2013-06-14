package net.egork;

public class WellTimedSearch {
    public double getProbability(int N, int A, int B) {
		int bad = A - 1;
		int goodPerSegment = 1;
		for (int i = 0; i < B - A; i++) {
			goodPerSegment = 2 * goodPerSegment + 1;
			goodPerSegment = Math.min(goodPerSegment, N);
		}
		int max = 1;
		for (int i = 0; i < A - 1; i++) {
			max *= 2;
			max = Math.min(max, N);
		}
		long answer = Math.min(N - bad, goodPerSegment);
		for (long i = 0; i < max - 1; i++) {
			for (int j = 0; ; j++) {
				if ((i >> j & 1) == 0) {
					bad += j;
					break;
				}
			}
			bad = Math.min(bad, N);
			answer = Math.max(answer, Math.min(N - bad, goodPerSegment * (i + 2)));
		}
		return (double)answer / N;
    }
}
