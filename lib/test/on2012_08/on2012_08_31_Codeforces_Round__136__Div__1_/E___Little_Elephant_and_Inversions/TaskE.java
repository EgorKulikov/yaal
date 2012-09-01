package on2012_08.on2012_08_31_Codeforces_Round__136__Div__1_.E___Little_Elephant_and_Inversions;



import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long max = in.readLong();
		final int[] array = IOUtils.readIntArray(in, count);
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (array[o1] != array[o2])
					return array[o1] - array[o2];
				return o1 - o2;
			}
		};
		NavigableSet<Integer> start = new TreapSet<Integer>(comparator);
		NavigableSet<Integer> end = new TreapSet<Integer>(comparator);
		long answer = 0;
		long current = 0;
		for (int i = count - 1; i >= 0; i--) {
			current += end.headSet(i, false).size();
			end.add(i);
		}
		int from = 0;
		for (int i = 0; i < count && from < count; i++) {
			if (from == i) {
				end.remove(i);
				current -= start.tailSet(i, false).size() + end.headSet(i, false).size();
			}
			from = Math.max(from, i + 1);
			current += end.headSet(i, false).size() + start.tailSet(i, false).size();
			start.add(i);
			while (current > max && from < count) {
				end.remove(from);
				current -= start.tailSet(from, false).size() + end.headSet(from, false).size();
				from++;
			}
			answer += count - from;
		}
		out.printLine(answer);
	}
}
