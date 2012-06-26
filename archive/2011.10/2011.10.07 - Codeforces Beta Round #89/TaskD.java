import net.egork.collections.ArrayUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int infantryCount = in.readInt();
		int cavalryCount = in.readInt();
		int maxInfantry = in.readInt();
		int maxCavalry = in.readInt();
		int[][][][] result = new int[infantryCount + 1][cavalryCount + 1][maxInfantry + 1][maxCavalry + 1];
		ArrayUtils.fill(result, -1);
		out.println(go(infantryCount, cavalryCount, 0, 0, maxInfantry, maxCavalry, result));
	}

	private int go(int infantryCount, int cavalryCount, int currentInfantry, int currentCavalry, int maxInfantry, int maxCavalry, int[][][][] result) {
		if (result[infantryCount][cavalryCount][currentInfantry][currentCavalry] != -1)
			return result[infantryCount][cavalryCount][currentInfantry][currentCavalry];
		int answer = 0;
		if (infantryCount != 0 && currentInfantry != maxInfantry)
			answer += go(infantryCount - 1, cavalryCount, currentInfantry + 1, 0, maxInfantry, maxCavalry, result);
		if (cavalryCount != 0 && currentCavalry != maxCavalry)
			answer += go(infantryCount, cavalryCount - 1, 0, currentCavalry + 1, maxInfantry, maxCavalry, result);
		if (infantryCount == 0 && cavalryCount == 0)
			answer++;
		if (answer >= 100000000)
			answer -= 100000000;
		return result[infantryCount][cavalryCount][currentInfantry][currentCavalry] = answer;
	}
}

