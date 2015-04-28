package on2015_04.on2015_04_17_Single_Round_Match_656.PermutationCounts;



import net.egork.misc.ArrayUtils;

public class PermutationCounts {
    private static final int MOD = (int) (1e9 + 7);
    int[][] result;
    boolean[] more;
    int n;

    public int countPermutations(int N, int[] pos) {
        n = N;
        result = new int[N][N];
        more = new boolean[N];
        for (int i : pos) {
            more[i] = true;
        }
        ArrayUtils.fill(result, -1);
        long answer = 0;
        for (int i = 0; i < N; i++) {
            answer += go(1, i);
        }
		return (int)(answer % MOD);
    }

    private int go(int position, int value) {
        if (position == n) {
            return 1;
        }
        if (value == 0 && !more[position] || value == n - position && more[position]) {
            return result[position][value] = 0;
        }
        if (result[position][value] != -1) {
            return result[position][value];
        }
        if (more[position]) {
            return result[position][value] = (go(position + 1, value) + go(position, value + 1)) % MOD;
        } else {
            return result[position][value] = (go(position + 1, value - 1) + go(position, value - 1)) % MOD;
        }
    }
}
