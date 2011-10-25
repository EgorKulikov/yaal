import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.StringWrapper;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String number = in.readString();
		int questionMarks = CollectionUtils.count(StringWrapper.wrap(number), '?');
		for (int i = 0; i < (1 << questionMarks); i++) {
			long candidate = 0;
			int index = 0;
			for (int j = 0; j < number.length(); j++) {
				candidate <<= 1;
				if (number.charAt(j) == '1')
					candidate++;
				else if (number.charAt(j) == '?') {
					if ((i >> index & 1) != 0)
						candidate++;
					index++;
				}
			}
			long sqrt = (long) (Math.sqrt(candidate) - 2);
			while (sqrt * sqrt < candidate)
				sqrt++;
			if (sqrt * sqrt == candidate) {
				out.println("Case #" + testNumber + ": " + Long.toString(candidate, 2));
				return;
			}
		}
	}
}

