package on2014_11.on2014_11_15_Codeforces_Round__277_5__Div__2_.TaskF;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	long[][] answer;
	long mod;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int filled = in.readInt();
		mod = in.readInt();
		int[] columns = new int[count];
		for (int i = 0; i < filled; i++) {
			for (int j = 0; j < count; j++) {
				if (in.readCharacter() == '1') {
					columns[j]++;
				}
			}
		}
		answer = new long[count - filled + 1][count + 1];
		ArrayUtils.fill(answer, -1);
		out.printLine(go(count - filled, ArrayUtils.count(columns, 0), ArrayUtils.count(columns, 1)));
    }

	private long go(int count, int dblCount, int sngCount) {
		if (answer[count][dblCount] != -1) {
			return answer[count][dblCount];
		}
		if (count == 0) {
			return answer[count][dblCount] = 1;
		}
		answer[count][dblCount] = 0;
		if (dblCount >= 2) {
			answer[count][dblCount] += dblCount * (dblCount - 1) / 2 * go(count - 1, dblCount - 2, sngCount + 2) % mod;
		}
		if (dblCount >= 1 && sngCount >= 1) {
			answer[count][dblCount] += dblCount * sngCount * go(count - 1, dblCount - 1, sngCount) % mod;
		}
		if (sngCount >= 2) {
			answer[count][dblCount] += sngCount * (sngCount - 1) / 2 * go(count - 1, dblCount, sngCount - 2) % mod;
		}
		return answer[count][dblCount] %= mod;
	}
}
