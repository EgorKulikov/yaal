package on2012_02.on2012_1_22.dengklekbuildingroads;



import net.egork.misc.ArrayUtils;

public class DengklekBuildingRoads {
	long[][][][] result;
	int K;
	static final long MOD = (long)1e9 + 7;

	public int numWays(int N, int M, int K) {
		result = new long[N][M + 1][1 << (K + 1)][K];
		this.K = K;
		ArrayUtils.fill(result, -1);
		return (int) go(N - 1, M, 0, 0);
	}

	private long go(int remainingHomes, int remainingRoads, int mask, int shift) {
		if (remainingHomes == -1)
			return remainingRoads == 0 && mask == 0 ? 1 : 0;
		if (remainingRoads < 0)
			return 0;
		if (shift == K)
			return (mask & 1) == 0 ? go(remainingHomes - 1, remainingRoads, mask >> 1, 0) : 0;
		if (result[remainingHomes][remainingRoads][mask][shift] != -1)
			return result[remainingHomes][remainingRoads][mask][shift];
		if (shift >= remainingHomes)
			return result[remainingHomes][remainingRoads][mask][shift] = go(remainingHomes, remainingRoads, mask, shift + 1);
		return (result[remainingHomes][remainingRoads][mask][shift] =
			go(remainingHomes, remainingRoads - 1, mask ^ (1 << (shift + 1)) ^ 1, shift) +
			go(remainingHomes, remainingRoads, mask, shift + 1)) % MOD;
	}


}

