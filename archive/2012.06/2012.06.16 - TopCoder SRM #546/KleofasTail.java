package net.egork;

public class KleofasTail {
	public long countGoodSequences(long K, long A, long B) {
		if (A == B)
			return go(A, K);
		if (K > B)
			return 0;
		long result = 0;
		if (A <= K && K <= B) {
			result++;
			A = K + 1;
		}
		if (A > B)
			return result;
		if ((A & 1) == 1) {
			result += go(A, K);
			A++;
		}
		if ((B & 1) == 0) {
			result += go(B, K);
			B--;
		}
		if (A > B)
			return result;
		result += 2 * countGoodSequences(K, A >> 1, B >> 1);
		return result;
	}

	private long go(long a, long k) {
		if (a < k)
			return 0;
		if (a == k)
			return 1;
		if ((a & 1) == 1)
			return go(a - 1, k);
		return go(a >> 1, k);
	}


}

