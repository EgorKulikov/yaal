import net.egork.io.IOUtils;
import net.egork.string.StringUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String s = in.readString();
		int[] result = new int[s.length()];
		result[0] = s.length();
		for (int i = 0; i < s.length(); i++) {
			int[] z = StringUtils.zAlgorithm(s.substring(i));
			for (int j = 1; j < z.length; j++) {
				int count = z[j] / j;
				result[count] = Math.max(result[count], j);
			}
		}
		for (int i = result.length - 2; i >= 0; i--)
			result[i] = Math.max(result[i], result[i + 1]);
		for (int i = 0; i < result.length; i++)
			result[i] *= i + 1;
		out.print("Case #" + testNumber + ": ");
		IOUtils.printArray(result, out);
	}
}

