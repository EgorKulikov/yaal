package on2013_09.on2013_09_27_Single_Round_Match_592.LittleElephantAndPermutationDiv1;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

public class LittleElephantAndPermutationDiv1 {
	private static final long MOD = (long) (1e9 + 7);
	long[][][] answer;
	int k;

	final long go(int max, int fromUp, int sum) {
		if (fromUp > max)
			return 0;
		if (answer[max][fromUp][sum] != -1)
			return answer[max][fromUp][sum];
		if (max == 0) {
			if (sum >= k)
				answer[max][fromUp][sum] = 1;
			else
				answer[max][fromUp][sum] = 0;
			return answer[max][fromUp][sum];
		}
		answer[max][fromUp][sum] = 0;
		if (fromUp != 0)
			answer[max][fromUp][sum] += go2(max, fromUp, sum) * fromUp;
		answer[max][fromUp][sum] += go2(max, fromUp + 1, sum + max);
		return answer[max][fromUp][sum] %= MOD;
	}

	final long go2(int max, int fromUp, int sum) {
		long result = 0;
		if (fromUp != 0)
			result += go(max - 1, fromUp - 1, sum) * fromUp;
		result += go(max - 1, fromUp, sum + max);
		return result % MOD;
	}

    public int getNumber(int N, int K) {
		answer = new long[N + 1][N + 1][N * N + 1];
		ArrayUtils.fill(answer, -1);
		k = K;
		return (int) (go(N, 0, 0) * IntegerUtils.factorial(N, MOD) % MOD);
    }
}
