package on2013_05.on2013_05_18_Single_Round_Match_579.TravellingPurchasingMan;



import net.egork.misc.ArrayUtils;

public class TravellingPurchasingMan {
    public int maxStores(int N, String[] interestingStores, String[] roads) {
		int edgeCount = roads.length;
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] length = new int[edgeCount];
		for (int i = 0; i < edgeCount; i++) {
			String[] tokens = roads[i].split(" ");
			from[i] = Integer.parseInt(tokens[0]);
			to[i] = Integer.parseInt(tokens[1]);
			length[i] = Integer.parseInt(tokens[2]);
		}
		int[][] distance = new int[N][N];
		ArrayUtils.fill(distance, Integer.MAX_VALUE / 2);
		for (int i = 0; i < edgeCount; i++)
			distance[from[i]][to[i]] = distance[to[i]][from[i]] = length[i];
		for (int i = 0; i < N; i++)
			distance[i][i] = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++)
					distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
			}
		}
		int count = interestingStores.length;
		int[] open = new int[count];
		int[] close = new int[count];
		int[] duration = new int[count];
		for (int i = 0; i < count; i++) {
			String[] tokens = interestingStores[i].split(" ");
			open[i] = Integer.parseInt(tokens[0]);
			close[i] = Integer.parseInt(tokens[1]);
			duration[i] = Integer.parseInt(tokens[2]);
		}
		int[][] result = new int[1 << count][count];
		ArrayUtils.fill(result, Integer.MAX_VALUE / 2);
		System.arraycopy(distance[N - 1], 0, result[0], 0, count);
		int answer = 0;
		for (int i = 0; i < (1 << count); i++) {
			boolean good = false;
			for (int j = 0; j < count; j++) {
				if (result[i][j] != Integer.MAX_VALUE / 2) {
					good = true;
					break;
				}
			}
			if (!good)
				continue;
			answer = Math.max(answer, Integer.bitCount(i));
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					result[i][j] = Math.min(result[i][j], result[i][k] + distance[j][k]);
				}
			}
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 0 && result[i][j] <= close[j])
					result[i + (1 << j)][j] = Math.min(result[i + (1 << j)][j], Math.max(result[i][j], open[j]) + duration[j]);
			}
		}
		return answer;
    }
}
