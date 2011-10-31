package April2011.CodeforcesBetaRound66;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.collections.CollectionUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] name = in.readString().toCharArray();
		int maxAlterations = in.readInt();
		int[][] bonus = new int[256][256];
		int goodPairs = in.readInt();
		for (int i = 0; i < goodPairs; i++) {
			char first = in.readCharacter();
			char second = in.readCharacter();
			bonus[first][second] = in.readInt();
		}
		int[][][] result = new int[name.length][maxAlterations + 1][256];
		ArrayUtils.fill(result, Integer.MIN_VALUE);
		if (maxAlterations != 0) {
			for (int i = 'a'; i <= 'z'; i++) {
				result[0][i == name[0] ? 0 : 1][i] = 0;
			}
		} else
			result[0][0][name[0]] = 0;
		for (int i = 1; i < name.length; i++) {
			result[i][0][name[i]] = result[i - 1][0][name[i - 1]] + bonus[name[i - 1]][name[i]];
			for (int j = 1; j <= maxAlterations; j++) {
				for (int k = 'a'; k <= 'z'; k++) {
					int oldJ = k == name[i] ? j : j - 1;
					for (int l = 'a'; l <= 'z'; l++) {
						if (result[i - 1][oldJ][l] != Integer.MIN_VALUE)
							result[i][j][k] = Math.max(result[i][j][k], result[i - 1][oldJ][l] + bonus[l][k]);
					}
				}
			}
		}
		int answer = Integer.MIN_VALUE;
		for (int i = 0; i <= maxAlterations; i++)
			answer = Math.max(answer, CollectionUtils.maxElement(Array.wrap(result[name.length - 1][i])));
		out.println(answer);
	}
}


