import net.egork.collections.ArrayUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class MostPopularFriend implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int friendCount = in.readInt();
		if (friendCount == 1)
			throw new RuntimeException();
		int[][] distance = new int[friendCount][friendCount];
		ArrayUtils.fill(distance, Integer.MAX_VALUE / 2);
		for (int i = 0; i < friendCount; i++)
			distance[i][i] = 0;
		for (int i = 0; i < friendCount; i++) {
			String list = in.readLine();
			for (String friend : list.split(" ")) {
				int friendIndex = Integer.parseInt(friend) - 1;
				if (friendIndex == i)
					continue;
				distance[i][friendIndex] = 1;//distance[friendIndex][i] = 1;
			}
		}
		for (int i = 0; i < friendCount; i++) {
			for (int j = 0; j < friendCount; j++) {
				if (distance[i][j] != distance[j][i])
					throw new RuntimeException();
			}
		}
		for (int i = 0; i < friendCount; i++) {
			for (int j = 0; j < friendCount; j++) {
				for (int k = 0; k < friendCount; k++)
					distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
			}
		}
		for (int i = 0; i < friendCount; i++) {
			for (int j = 0; j < friendCount; j++) {
				if (distance[i][j] == Integer.MAX_VALUE / 2)
					throw new RuntimeException();
			}
		}
		int index = -1;
		int value = Integer.MAX_VALUE;
		for (int i = 0; i < friendCount; i++) {
			int currentValue = 0;
			for (int j = 0; j < friendCount; j++)
				currentValue += distance[j][i];
			if (currentValue < value) {
				value = currentValue;
				index = i;
			}
		}
		out.printf("%d %.6f\n", index + 1, (double)value / friendCount);
	}
}

