package on2014_06.on2014_06_19_Single_Round_Match_625.Seatfriends;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

public class Seatfriends {
	static final long MOD = (long) (1e9 + 7);
	long[][] answer;
	int total;
	int count;
	int maxGroups;
	long[][] c;

    public int countseatnumb(int N, int K, int G) {
		total = N;
		count = K;
		maxGroups = G;
		answer = new long[K + 1][G + 1];
		ArrayUtils.fill(answer, -1);
		c = IntegerUtils.generateBinomialCoefficients(N + 1, MOD);
		long result = go(0, 0) * total % MOD;
		return (int) result;
    }

	private long go(int step, int groups) {
		if (groups > maxGroups)
			return 0;
		if (answer[step][groups] != -1)
			return answer[step][groups];
		if (step + groups > total && groups != 1)
			return answer[step][groups] = 0;
		if (step == count) {
			int remainingSeats = total - count;
			if (remainingSeats == 0)
				return answer[step][groups] = 1;
			return answer[step][groups] = c[remainingSeats - 1][groups - 1];
		}
		if (step == total - 1)
			return answer[step][groups] = go(step + 1, 1);
		answer[step][groups] = (groups == 0 ? groups + 1 : groups) * go(step + 1, groups + 1);
		if (groups != 0) {
			answer[step][groups] += 2 * groups * go(step + 1, groups);
		}
		if (groups > 1) {
			answer[step][groups] += groups * go(step + 1, groups - 1);
		}
		return answer[step][groups] %= MOD;
	}
}
