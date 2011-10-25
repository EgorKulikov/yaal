import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int markCount = in.readInt();
		int first = in.readInt();
		int second = in.readInt();
		int[] marks = IOUtils.readIntArray(in, markCount);
		int[] firstMarks = new int[6];
		int[] secondMarks = new int[6];
		int[] result = new int[markCount];
		if (first == second) {
			for (int i = 0; i < first; i++)
				result[i] = 1;
			for (int i = first; i < markCount; i++)
				result[i] = 2;
		} else {
			int[] marksCopy = marks.clone();
			Arrays.sort(marksCopy);
			int[] small = first > second ? firstMarks : secondMarks;
			int[] high = first > second ? secondMarks : firstMarks;
			for (int i = 0; i < Math.max(first, second); i++)
				small[marksCopy[i]]++;
			for (int i = Math.max(first, second); i < markCount; i++)
				high[marksCopy[i]]++;
			for (int i = 0; i < markCount; i++) {
				if (firstMarks[marks[i]] != 0) {
					result[i] = 1;
					firstMarks[marks[i]]--;
				} else
					result[i] = 2;
			}
		}
		IOUtils.printArray(result, out);
	}
}

