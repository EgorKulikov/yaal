package on2013_04.on2013_04_19_Codeforces_Round__180.C___Splitting_the_Uniqueness;



import net.egork.misc.ArrayUtils;
import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		if (count <= 2) {
			out.printLine("YES");
			out.printLine(array);
			out.printLine(new int[count]);
			return;
		}
		int[] order = ArrayUtils.order(array);
		int first = (count + 2) / 3;
		int second = (count + 1) / 3;
		int[] answer = new int[count];
		int[] other = new int[count];
		Set<Integer> used = new EHashSet<Integer>();
		int current = array[order[first + second]];
		for (int i = first + second; i < count; i++) {
			other[order[i]] = current--;
			used.add(answer[order[i]] = array[order[i]] - other[order[i]]);
		}
		for (int i = 0; i < first; i++) {
			other[order[i]] = i;
			answer[order[i]] = array[order[i]] - other[order[i]];
		}
		current = 0;
		for (int i = first; i < first + second; i++) {
			while (used.contains(current))
				current++;
			answer[order[i]] = current++;
			other[order[i]] = array[order[i]] - answer[order[i]];
		}
		out.printLine("YES");
		out.printLine(answer);
		out.printLine(other);
    }
}
