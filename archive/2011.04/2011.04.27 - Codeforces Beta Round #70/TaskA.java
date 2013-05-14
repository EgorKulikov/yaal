import net.egork.collections.CollectionUtils;
import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.StringWrapper;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int[] correctCount = {5, 7, 5};
		for (int i = 0; i < 3; i++) {
			String phrase = in.readLine();
			int vowelCount = CollectionUtils.count(StringWrapper.wrap(phrase), new Filter<Character>() {
				public boolean accept(Character value) {
					return MiscUtils.isVowel(value) && Character.toUpperCase(value) != 'Y';
				}
			});
			if (vowelCount != correctCount[i]) {
				out.println("NO");
				return;
			}
		}
		out.println("YES");
	}
}

