package on2014_03.on2014_03_29_Single_Round_Match_614.CycleColoring;



import net.egork.numbers.IntegerUtils;

public class CycleColoring {
	private static final long MOD = (long) (1e9 + 7);

	public int countColorings(int[] vertexCount, int[] fromVertex, int[] toVertex, int K) {
		long[] same = new long[1000001];
		long[] different = new long[1000001];
		same[0] = 1;
		different[0] = 0;
		for (int i = 1; i <= 1000000; i++) {
			same[i] = different[i - 1];
			different[i] = (different[i - 1] * (K - 2) + same[i - 1] * (K - 1)) % MOD;
		}
		long reverse = IntegerUtils.reverse(K - 1, MOD);
		for (int i = 1; i <= 1000000; i++)
			different[i] = different[i] * reverse % MOD;
		long tSame = K;
		long tDifferent = 0;
		for (int i = 1; i < fromVertex.length; i++) {
			int difference1 = Math.abs(toVertex[i - 1] - fromVertex[i]);
			int difference2 = vertexCount[i] - difference1;
			long nTSame = same[difference1] * same[difference2] % MOD * tSame + different[difference1] * different[difference2] % MOD * tDifferent;
			long nTDifferent = same[difference1] * same[difference2] % MOD * tDifferent + different[difference1] * different[difference2] % MOD * (tSame * (K - 1) % MOD + tDifferent * (K - 2) % MOD);
			tSame = nTSame % MOD;
			tDifferent = nTDifferent % MOD;
		}
		int difference1 = Math.abs(toVertex[vertexCount.length - 1] - fromVertex[0]);
		int difference2 = vertexCount[0] - difference1;
		long answer = same[difference1] * same[difference2] % MOD * tSame + different[difference1] * different[difference2] % MOD * tDifferent;
		return (int) (answer % MOD);
    }
}
