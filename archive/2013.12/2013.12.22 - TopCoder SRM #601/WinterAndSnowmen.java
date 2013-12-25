package net.egork;

public class WinterAndSnowmen {
	static final long MOD = (long) (1e9 + 7);

	int[] result;
	int curBit;
	int N, M;
	int last;
	int[] value;

    public int getNumber(int N, int M) {
		this.N = N;
		this.M = M;
		last = Math.max(N, M) + 1;
//		result = new int[(1 << 23) + 10];
		long answer = 0;
		value = new int[last];
		for (int i = 10; i >= 0; i--) {
//			for (int j = 1; j < last; j++)
//				value[j] = (j >> i & 1) << 22;
//			Arrays.fill(result, -1);
			curBit = i;
//			answer += calculate(1);
			long[][] current = new long[2][2048];
			long[][] next = new long[2][2048];
			for (int j = 0; j < 2048; j++) {
				if ((j >> i) == 1)
					current[0][j] = 1;
			}
			for (int j = Math.max(M, N); j > 0; j--) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2048; l++) {
						next[k][l] = current[k][l];
						if (j <= N) {
							next[k][l] += current[k ^ (j >> i & 1)][l ^ j];
						}
						if (j <= M)
							next[k][l] += current[k][l ^ j];
						next[k][l] %= MOD;
					}
				}
				long[][] t = current;
				current = next;
				next = t;
			}
			answer += current[0][0];
		}
		return (int)(answer % MOD);
    }

	private int calculate(int key) {
//		int key = (special << 22) + (xor << 11) + step;
		int step = key & 2047;
		if (result[key] != -1)
			return result[key];
		if (step == last) {
			if ((key >> (curBit + 11)) == 1)
				return result[key] = 1;
			return result[key] = 0;
		}
		long answer = calculate(key + 1);
		int addStep = step << 11;
		if (step <= N) {
			answer += calculate((key ^ value[step] ^ addStep) + 1);
//			if (answer >= MOD)
//				answer -= MOD;
		}
		if (step <= M) {
			answer += calculate((key ^ addStep) + 1);
//			if (answer >= MOD)
//				answer -= MOD;
		}
		return result[key] = (int)(answer % MOD);
	}
}
