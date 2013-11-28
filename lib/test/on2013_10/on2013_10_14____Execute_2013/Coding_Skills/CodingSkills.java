package on2013_10.on2013_10_14____Execute_2013.Coding_Skills;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CodingSkills {

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] qty = IOUtils.readIntArray(in, count);
		long[] skill = IOUtils.readLongArray(in, count);
		int expertCount = in.readInt();
		long[][] answer = new long[expertCount + 1][expertCount + 1];
		ArrayUtils.fill(answer, Long.MIN_VALUE);
		long[][] next = new long[expertCount + 1][expertCount + 1];
		answer[expertCount][0] = 0;
		for (int i = 0; i < count; i++) {
			ArrayUtils.fill(next, Long.MIN_VALUE);
			for (int j = 0; j <= expertCount; j++) {
				for (int k = 0; k <= expertCount; k++) {
					if (answer[j][k] == Long.MIN_VALUE)
						continue;
					int current = k + qty[i];
					for (int l = 0; l <= j && l <= current; l++)
						next[j - l][l] = Math.max(next[j - l][l], answer[j][k] + (current - l) * skill[i]);
				}
			}
			long[][] temp = answer;
			answer = next;
			next = temp;
		}
		long result = Long.MIN_VALUE;
		for (long[] row : answer)
			result = Math.max(result, row[0]);
		out.printLine(result);
	}
}
