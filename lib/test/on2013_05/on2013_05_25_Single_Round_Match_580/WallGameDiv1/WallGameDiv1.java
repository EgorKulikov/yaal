package on2013_05.on2013_05_25_Single_Round_Match_580.WallGameDiv1;



import net.egork.misc.ArrayUtils;

public class WallGameDiv1 {
	int[] sums;
	int[][] rabbit;
	int[][] eel;
	int[] cost;

    public int play(String[] costs) {
		int rowCount = costs.length;
		int columnCount = costs[0].length();
		cost = new int[columnCount];
		for (int i = 0; i < columnCount; i++)
			cost[i] = costs[rowCount - 1].charAt(i) - '0';
		sums = new int[columnCount + 1];
		rabbit = new int[columnCount][columnCount];
		eel = new int[columnCount][columnCount];
		int[] next = new int[columnCount];
		for (int i = rowCount - 2; i >= 0; i--) {
			sums[0] = 0;
			for (int j = 0; j < columnCount; j++)
				sums[j + 1] = sums[j] + costs[i].charAt(j) - '0';
			ArrayUtils.fill(rabbit, -1);
			ArrayUtils.fill(eel, -1);
			for (int j = 0; j < columnCount; j++)
				next[j] = countEel(j, j) + sums[j + 1] - sums[j];
			int[] temp = cost;
			cost = next;
			next = temp;
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < columnCount; i++)
			answer = Math.min(answer, cost[i]);
		return answer;
	}

	private int countEel(int current, int other) {
		if (eel[current][other] != -1)
			return eel[current][other];
		return eel[current][other] = Math.max(cost[current], countRabbit(current, other));
	}

	private int countRabbit(int current, int other) {
		if (rabbit[current][other] != -1)
			return rabbit[current][other];
		if (Math.abs(current - other) == cost.length - 1)
			return rabbit[current][other] = 0;
		int right = Math.max(current, other);
		int left = Math.min(current, other);
		if (right == cost.length - 1)
			return rabbit[current][other] = countEel(left - 1, right) + sums[current] - sums[left - 1];
		if (left == 0)
			return rabbit[current][other] = countEel(right + 1, left) + sums[right + 2] - sums[current + 1];
		return rabbit[current][other] = Math.min(
			countEel(left - 1, right) + sums[current] - sums[left - 1],
			countEel(right + 1, left) + sums[right + 2] - sums[current + 1]
		);
	}
}
