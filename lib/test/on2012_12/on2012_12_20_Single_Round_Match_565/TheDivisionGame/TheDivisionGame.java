package on2012_12.on2012_12_20_Single_Round_Match_565.TheDivisionGame;



import net.egork.numbers.IntegerUtils;

public class TheDivisionGame {
	public long countWinningIntervals(int L, int R) {
		int[] primes = IntegerUtils.generatePrimes(100000);
		int[] remaining = new int[R - L + 1];
		for (int i = 0; i <= R - L; i++)
			remaining[i] = L + i;
		int[] nimber = new int[R - L + 1];
		for (int i : primes) {
			int first = i - L % i;
			if (first == i)
				first = 0;
			for (int j = first; j <= R - L; j += i) {
				do {
					remaining[j] /= i;
					nimber[j]++;
				} while (remaining[j] % i == 0);
			}
		}
		for (int i = 0; i <= R - L; i++) {
			if (remaining[i] != 1)
				nimber[i]++;
		}
		int[] was = new int[1000];
		was[0] = 1;
		int xor = 0;
		long answer = (long)(R - L + 1) * (R - L + 2) / 2;
		for (int i = 0; i <= R - L; i++) {
			xor ^= nimber[i];
			answer -= was[xor];
			was[xor]++;
		}
		return answer;
	}
}
