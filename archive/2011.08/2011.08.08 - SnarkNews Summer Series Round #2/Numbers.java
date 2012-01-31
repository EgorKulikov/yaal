import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class Numbers implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int strikeCount = in.readInt();
		int perStrike = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		int[] sums = new int[count + 1];
		for (int i = 0; i < count; i++)
			sums[i + 1] = sums[i] + numbers[i];
		if (strikeCount * perStrike >= count) {
			out.println(sums[count]);
			return;
		}
		int answer = 0;
		int[][] result = new int[count + 1][strikeCount + 1];
		for (int i = perStrike; i <= count; i++) {
			for (int j = 1; j <= strikeCount; j++) {
				answer = Math.max(answer,
					result[i][j] = Math.max(result[i - 1][j], result[i - perStrike][j - 1] + sums[i] - sums[i - perStrike]));
			}
		}
		out.println(answer);
	}
}

