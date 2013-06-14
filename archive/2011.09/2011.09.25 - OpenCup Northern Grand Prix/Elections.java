import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class Elections implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		int[] votes = new int[count];
		int[][] resigns = new int[count][];
		int totalResign = 0;
		int totalVotes = 0;
		for (int i = 0; i < count; i++) {
			votes[i] = in.readInt();
			totalVotes += votes[i];
			int curResigns = in.readInt();
			resigns[i] = new int[curResigns];
			for (int j = 0; j < curResigns; j++)
				resigns[i][j] = in.readInt();
			totalResign += curResigns;
		}
		int[][] toParliament = new int[count][102];
		for (int i = 0; i < count; i++) {
			int index = 0;
			for (int j = 1; j <= 101; j++) {
				if (index != resigns[i].length && resigns[i][index] == j) {
					toParliament[i][j] = toParliament[i][j - 1];
					index++;
				} else
					toParliament[i][j] = toParliament[i][j - 1] + 1;
			}
		}
		Rational best = new Rational(Integer.MAX_VALUE, 1);
		int[] result = new int[count];
		int[][][] delta = new int[count + 1][size + totalResign + 1][size + 1];
		int[][][] last = new int[count + 1][size + totalResign + 1][size + 1];
		int inf = Integer.MAX_VALUE / 2;
		for (int i = size; i <= size + totalResign; i++) {
			for (int[][] row : delta) {
				for (int[] array : row) {
					Arrays.fill(array, inf);
				}
			}
			delta[0][0][0] = 0;
			for (int j = 0; j < count; j++) {
				int minus = votes[j] * i;
				for (int k = 0; k <= i; k++) {
					for (int l = 0; l <= size; l++) {
						for (int m = 0; k + m <= i && l + toParliament[j][m] <= size; m++) {
							int curToP = l + toParliament[j][m];
							int deltaDelta = Math.abs(m * totalVotes - minus);
							if (delta[j + 1][k + m][curToP] > delta[j][k][l] + deltaDelta) {
								delta[j + 1][k + m][curToP] = delta[j][k][l] + deltaDelta;
								last[j + 1][k + m][curToP] = m;
							}
						}
					}
				}
			}
			Rational current = new Rational(delta[count][i][size], i * totalVotes);
			if (best.more(current)) {
				best = current;
				int curTotal = i;
				int curGood = size;
				for (int j = count; j > 0; j--) {
					result[j - 1] = last[j][curTotal][curGood];
					int l = last[j][curTotal][curGood];
					curTotal -= l;
					curGood -= toParliament[j - 1][l];
				}
			}
//			for (int j = 0; j < (1 << count); j++) {
//				int totalMembers = 0;
//				int debils = 0;
//				int delta = 0;
//				for (int k = 0; k < count; k++) {
//					int current = i * votes[k] / totalVotes;
//					if ((j >> k & 1) == 1)
//						current++;
//					totalMembers += toParliament[k][current];
//					debils += current;
//					delta += Math.abs(current * totalVotes - votes[k] * i);
//				}
//				if (totalMembers != size || debils != i)
//					continue;
//				Rational variant = new Rational(delta, totalVotes * i);
//				if (best.more(variant)) {
//					best = variant;
//					a = i;
//					mask = j;
//				}
//			}
		}
//		int[] result = new int[count];
//		for (int i = 0; i < count; i++) {
//			result[i] = a * votes[i] / totalVotes;
//			if ((mask >> i & 1) != 0)
//				result[i]++;
//		}
		for (int i : result)
			out.println(i);
	}
}

class Rational {
	private final long p;
	private final long q;

	Rational(long p, long q) {
		this.p = p;
		this.q = q;
	}

	public boolean more(Rational other) {
		return p * other.q > other.p * q;
	}
}

