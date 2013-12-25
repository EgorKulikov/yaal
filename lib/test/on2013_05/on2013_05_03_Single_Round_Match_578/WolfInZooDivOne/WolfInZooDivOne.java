package on2013_05.on2013_05_03_Single_Round_Match_578.WolfInZooDivOne;



import net.egork.misc.ArrayUtils;
import net.egork.string.StringUtils;

public class WolfInZooDivOne {
	long[][] answer;
	int N;
	int[] left, right;
	static final long MOD = (long) (1e9 + 7);

    public int count(int N, String[] L, String[] R) {
		this.N = N;
		left = process(L);
		right = process(R);
		answer = new long[N + 1][N + 1];
		ArrayUtils.fill(answer, -1);
		return (int) go(-1, -1);
    }

	private long go(int current, int last) {
		if (answer[current + 1][last + 1] != -1)
			return answer[current + 1][last + 1];
		long result = 1;
		int next = current + 1;
		for (int i = 0; i < left.length; i++) {
			if (left[i] <= last && right[i] >= current)
				next = Math.max(next, right[i] + 1);
		}
		for (int i = next; i < N; i++)
			result += go(i, current);
		return answer[current + 1][last + 1] = result % MOD;
	}

	private int[] process(String[] strings) {
		String s = StringUtils.unite(strings);
		String[] tokens = s.split(" ");
		int[] result = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++)
			result[i] = Integer.parseInt(tokens[i]);
		return result;
	}
}
