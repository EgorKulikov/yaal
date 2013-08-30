package on2013_08.on2013_08_12_Single_Round_Match_588.KeyDungeonDiv1;



import net.egork.misc.ArrayUtils;

public class KeyDungeonDiv1 {
	int[][] maxWhite;
	int[] total;

    public int maxKeys(int[] doorR, int[] doorG, int[] roomR, int[] roomG, int[] roomW, int[] keys) {
		int answer = (int) ArrayUtils.sumArray(keys);
		int count = doorR.length;
		total = new int[1 << count];
		int[] r = new int[1 << count];
		int[] w = new int[1 << count];
		int[] g = new int[1 << count];
		total[0] = answer;
		r[0] = keys[0];
		g[0] = keys[1];
		w[0] = keys[2];
		for (int i = 0; i < (1 << count); i++) {
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 0) {
					total[i + (1 << j)] = total[i] - doorR[j] - doorG[j] + roomR[j] + roomG[j] + roomW[j];
					r[i + (1 << j)] = r[i] - doorR[j] + roomR[j];
					w[i + (1 << j)] = w[i] + roomW[j];
					g[i + (1 << j)] = g[i] - doorG[j] + roomG[j];
				}
			}
		}
		maxWhite = new int[1 << count][10 * (count + 1) + 1];
		ArrayUtils.fill(maxWhite, -1);
		maxWhite[0][keys[0]] = keys[2];
		boolean[] possible = new boolean[1 << count];
		possible[0] = true;
		for (int i = 0; i < (1 << count); i++) {
			for (int j = 0; j <= 10 * (count + 1); j++) {
				if (maxWhite[i][j] == -1)
					continue;
				answer = Math.max(answer, total[i]);
				int curRed = j;
				int curWhite = maxWhite[i][j];
				int curGreen = total[i] - curRed - curWhite;
				for (int k = 0; k < count; k++) {
					if ((i >> k & 1) != 0)
						continue;
					int newRed = curRed - doorR[k];
					int newGreen = curGreen - doorG[k];
					int newWhite = curWhite;
					if (newRed < 0) {
						newWhite += newRed;
						newRed = 0;
					}
					if (newGreen < 0) {
						newWhite += newGreen;
						newGreen = 0;
					}
					if (newWhite < 0)
						continue;
					newRed += roomR[k];
					newWhite += roomW[k];
					maxWhite[i + (1 << k)][newRed] = Math.max(maxWhite[i + (1 << k)][newRed], newWhite);
				}
			}
			/*if (!possible[i])
				continue;
			answer = Math.max(answer, total[i]);
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) != 0)
					continue;
				if (r[i] + w[i] >= doorR[j] && g[i] + w[i] >= doorG[j] && r[i] + g[i] + w[i] >= doorR[j] + doorG[j])
					possible[i + (1 << j)] = true;
			}*/
		}
		return answer;
    }
}
