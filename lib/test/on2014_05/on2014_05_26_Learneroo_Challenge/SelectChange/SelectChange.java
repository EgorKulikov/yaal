package on2014_05.on2014_05_26_Learneroo_Challenge.SelectChange;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SelectChange {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt() - 1;
		int target = in.readInt();
		int[] coins = IOUtils.readIntArray(in, count);
		boolean[][] current = new boolean[4][target + 1];
		boolean[][] next = new boolean[4][target + 1];
		current[0][0] = true;
		for (int i : coins) {
			ArrayUtils.fill(next, false);
			for (int j = 0; j <= target; j++) {
				for (int k = 0; k < 4; k++) {
					if (current[k][j]) {
						next[(k * 2) & 2][j] = true;
						if (k != 3 && j + i <= target)
							next[((k * 2) & 2) + 1][j + i] = true;
					}
				}
			}
			boolean[][] temp = current;
			current = next;
			next = temp;
		}
		out.printLine(current[0][target] || current[1][target] || current[2][target] || current[3][target]);
    }
}
