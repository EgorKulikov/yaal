package Timus.Part2;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class Task1114 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int a = in.readInt();
		int b = in.readInt();
		BigInteger[][][] answer = new BigInteger[n + 1][a + 1][b + 1];
		answer[0][0][0] = BigInteger.ONE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= a; j++) {
				for (int k = 0; k <= b; k++) {
					if (answer[i][j][k] == null)
						continue;
					for (int l = j; l <= a; l++) {
						for (int m = k; m <= b; m++) {
							if (answer[i + 1][l][m] == null)
								answer[i + 1][l][m] = answer[i][j][k];
							else
								answer[i + 1][l][m] = answer[i + 1][l][m].add(answer[i][j][k]);
						}
					}
				}
			}
		}
		BigInteger result = BigInteger.ZERO;
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j <= b; j++)
				result = result.add(answer[n][i][j]);
		}
		out.println(result);
	}
}

