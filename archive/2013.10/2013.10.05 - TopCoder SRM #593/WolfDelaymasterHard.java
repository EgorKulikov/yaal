package net.egork;

import java.util.Arrays;

public class WolfDelaymasterHard {
	private static final long MOD = (long) (1e9 + 7);

	public int countWords(int N, int wlen, int w0, int wmul, int wadd, int olen, int o0, int omul, int oadd) {
		char[] word = new char[N];
		Arrays.fill(word, '?');
		for (int i = 0; i < wlen; i++) {
			word[w0] = 'w';
			w0 = (int) (((long)w0 * wmul + wadd) % N);
		}
		for (int i = 0; i < olen; i++) {
			word[o0] = 'o';
			o0 = (int) (((long)o0 * omul + oadd) % N);
		}
		int[] nextW = new int[N + 1];
		int[] nextO = new int[N + 1];
		int[] lastW = new int[N + 1];
		nextW[N] = N;
		for (int i = N - 1; i >= 0; i--) {
			if (word[i] == 'w')
				nextW[i] = i;
			else
				nextW[i] = nextW[i + 1];
		}
		nextO[N] = N;
		for (int i = N - 1; i >= 0; i--) {
			if (word[i] == 'o')
				nextO[i] = i;
			else
				nextO[i] = nextO[i + 1];
		}
		for (int i = 0; i < N; i++) {
			if (word[i] == 'w')
				lastW[i + 1] = i + 1;
			else
				lastW[i + 1] = lastW[i];
		}
		int[] answer = new int[N / 2 + 1];
		int[] sum = new int[answer.length + 1];
		answer[N / 2] = 1;
		sum[N / 2] = 1;
		for (int i = N / 2 - 1; i >= 0; i--) {
			int start = 2 * i;
			int max = Math.min(2 * nextO[start] - start, nextW[nextO[start]]);
			max = (max - start) >> 1;
			int current = 1;
			long result = 0;
			while (current <= max) {
				if (nextW[start + current] < start + 2 * current) {
					current = lastW[start + 2 * current] - start;
					continue;
				}
				int till = (nextW[start + current] - start) >> 1;
				till = Math.min(till, max);
				result += sum[i + current] - sum[i + till + 1];
				current = nextW[start + current] - start + 1;
			}
			result %= MOD;
			if (result < 0)
				result += MOD;
			answer[i] = (int) result;
			sum[i] = sum[i + 1] + answer[i];
			if (sum[i] >= MOD)
				sum[i] -= MOD;
		}
		return answer[0];
    }
}
