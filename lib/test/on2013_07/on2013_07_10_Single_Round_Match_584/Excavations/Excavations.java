package on2013_07.on2013_07_10_Single_Round_Match_584.Excavations;



import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class Excavations {
    public long count(int[] kind, int[] depth, int[] found, int K) {
		int count = kind.length;
		ArrayUtils.orderBy(kind, depth);
		int size = ArrayUtils.compress(depth).length;
		Arrays.sort(found);
		long[][][][] variants = new long[2][2][K + 1][size];
		long[][][][] next = new long[2][2][K + 1][size];
		Arrays.fill(variants[1][0][0], 1);
		variants[1][0][0][size - 1] = 0;
		variants[1][1][0][size - 1] = 1;
		for (int i = 0; i < count; i++) {
			boolean canTake = Arrays.binarySearch(found, kind[i]) >= 0;
			if ((i == 0 || kind[i] != kind[i - 1]) && canTake) {
				for (int k = 0; k < 2; k++) {
					for (int j = 0; j <= K; j++) {
						System.arraycopy(variants[1][k][j], 0, variants[0][k][j], 0, size);
						Arrays.fill(variants[1][k][j], 0);
					}
				}
			}
			ArrayUtils.fill(next, 0);
			for (int j = 0; j < 2; j++) {
				for (int m = 0; m < 2; m++) {
					for (int k = 0; k <= K; k++) {
						for (int l = 0; l < size; l++) {
							if (variants[j][m][k][l] == 0)
								continue;
							next[j][m][k][l] += variants[j][m][k][l];
							if (k == K)
								continue;
							if (canTake)
								next[depth[i] <= l ? 1 : j][m][k + 1][l] += variants[j][m][k][l];
							else if (depth[i] > l)
								next[j][depth[i] == l + 1 ? 1 : m][k + 1][l] += variants[j][m][k][l];
						}
					}
				}
			}
			if ((i == count - 1 || kind[i] != kind[i + 1]) && canTake)
				ArrayUtils.fill(next[0], 0);
			long[][][][] temp = variants;
			variants = next;
			next = temp;
		}
		long answer = 0;
		for (int i = 0; i < size; i++)
			answer += variants[1][1][K][i];
		return answer;
    }
}
