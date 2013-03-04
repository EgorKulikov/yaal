import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.Array;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int positionCount = in.readInt();
		int[] demons = new int[positionCount + 2];
		for (int i = 0; i < positionCount; i++)
			demons[i + 2] = in.readInt();
		int leftmost = SequenceUtils.find(Array.wrap(demons), new Filter<Integer>() {
			public boolean accept(Integer value) {
				return value != 0;
			}
		});
		int position = 0;
		positionCount += 2;
		int right = positionCount - 1;
		while (true) {
			while (right >= 0 && demons[right] == 0)
				right--;
			if (right < 0)
				break;
			int left = right;
			while (demons[left - 1] >= demons[left] || demons[left] == 1 && left > leftmost)
				left--;
			while (position < right - 1) {
				out.print("AR");
				position++;
			}
			if (position == right - 1)
				out.print('A');
			while (position > left - 2) {
				out.print('L');
				position--;
			}
			out.print('A');
			for (int i = left; i <= right; i++)
				demons[i] = Math.max(demons[i] - 1, 0);
		}
		out.println();
	}
}

