package net.egork;

public class BallsInBoxes {
    public long maxTurns(long N, long K) {
        long total = N - K + 1;
        if (total <= K) {
            return ways(total);
        }
        long full = ways(K);
        for (long answer = total / K - 1; answer <= total / K + full; answer++) {
            long remaining = total;
            long fullTaken = Math.max(0, answer - full);
            remaining -= K * fullTaken;
            for (long i = answer - fullTaken; i > 0; i--) {
                remaining -= 1L << (i - 1);
            }
            remaining--;
            if (remaining <= 0) {
                return answer;
            }
        }
		throw new RuntimeException();
    }

    protected int ways(long K) {
        return Long.bitCount(Long.highestOneBit(2 * K - 1) - 1);
    }
}
