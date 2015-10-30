package on2015_09.on2015_09_28_Single_Round_Match_669.LineMST;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

public class LineMST {
    private static final long MOD = (long) (1e9 + 7);
    long[][] answer;
    long[][] pow;
    int l;

    public int count(int N, int L) {
        this.l = L;
        answer = new long[N][L + 1];
        ArrayUtils.fill(answer, -1);
        pow = new long[L + 1][];
        for (int i = 1; i <= L; i++) {
            pow[i] = IntegerUtils.generatePowers(i, N * (N - 1) / 2 + 1, MOD);
        }
		return (int) (calculate(N - 1, l) * IntegerUtils.factorial(N, MOD) % MOD * IntegerUtils.reverse(2, MOD) % MOD);
    }

    private long calculate(int n, int current) {
        if (answer[n][current] != -1) {
            return answer[n][current];
        }
        if (current == 0) {
            return answer[n][current] = n == 0 ? 1 : 0;
        }
        answer[n][current] = calculate(n, current - 1);
        for (int i = 0; i < n; i++) {
            answer[n][current] += pow[l - current + 1][(i + 1) * (n - i) - 1] * calculate(i, current - 1) % MOD
                * calculate(n - i - 1, current) % MOD;
        }
        return answer[n][current] %= MOD;
    }
}
