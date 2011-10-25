import net.egork.string.StringUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String first = in.readLine(false);
		String second = in.readLine(false);
		int length = first.length();
		if (length != second.length()) {
			out.println("-1 -1");
			return;
		}
		int[] prefix = StringUtils.prefixFunction(StringUtils.reverse(first) + '\n' + second);
		int[] z = StringUtils.zAlgorithm(second + '\n' + first + '\n');
		int firstIndex = -1;
		int secondIndex = -1;
		for (int i = 0; i < length - 1; i++) {
			if (first.charAt(i) != second.charAt(length - i - 1))
				break;
			int aLength = i + 1;
			int bLength = z[length + i + 2];
			int cLength = prefix[2 * length - i - 1];
			if (cLength > 0 && aLength + bLength + cLength >= length) {
				firstIndex = i;
				secondIndex = length - cLength;
			}
		}
		out.println(firstIndex + " " + secondIndex);
	}
}

