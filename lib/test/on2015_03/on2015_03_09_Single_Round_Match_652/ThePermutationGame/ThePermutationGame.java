package on2015_03.on2015_03_09_Single_Round_Match_652.ThePermutationGame;



import net.egork.numbers.IntegerUtils;

public class ThePermutationGame {
	private static final long MOD = (long) (1e9 + 7);

	public int findMin(int N) {
		int[] primes = IntegerUtils.generatePrimes(N + 1);
		long answer = 1;
		for (int i : primes) {
			int copy = N;
			while (copy >= i) {
				copy /= i;
				answer *= i;
				answer %= MOD;
			}
		}
		return (int)answer;
    }
}
