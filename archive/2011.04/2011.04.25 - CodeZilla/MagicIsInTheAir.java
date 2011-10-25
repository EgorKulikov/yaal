import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class MagicIsInTheAir implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int cubeCount = in.readInt();
		int minimalSameColor = in.readInt();
		int[] colors = IOUtils.readIntArray(in, cubeCount);
		int[][][] answer = new int[cubeCount][cubeCount][minimalSameColor];
		ArrayUtils.fill(answer, -1);
		out.println(go(answer, colors, minimalSameColor, 0, cubeCount - 1, 0));
	}

	private int go(int[][][] answer, int[] colors, int minimalSameColor, int left, int right, int leftSame) {
		if (answer[left][right][leftSame] != -1)
			return answer[left][right][leftSame];
		if (left == right)
			return answer[left][right][leftSame] = minimalSameColor - leftSame - 1;
		if (left > right)
			return answer[left][right][leftSame] = 0;
		int result = Integer.MAX_VALUE;
		for (int i = left + 1; i <= right; i++) {
			if (colors[i] == colors[left]) {
				result = Math.min(result, go(answer, colors, minimalSameColor, left + 1, i - 1, 0) +
					go(answer, colors, minimalSameColor, i, right, Math.min(minimalSameColor - 1, leftSame + 1)));
			}
			result = Math.min(result, go(answer, colors, minimalSameColor, left, i - 1, leftSame) +
				go(answer, colors, minimalSameColor, i, right, 0));
		}
		return answer[left][right][leftSame] = result;
	}
}

