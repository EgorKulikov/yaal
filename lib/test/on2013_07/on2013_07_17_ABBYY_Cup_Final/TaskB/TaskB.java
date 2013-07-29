package on2013_07.on2013_07_17_ABBYY_Cup_Final.TaskB;



import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(array);
		int[] position = ArrayUtils.reversePermutation(array);
		SumIntervalTree tree = new SumIntervalTree(count);
		int level = 0;
		for (int i = 1; i < count; i++) {
			if (position[i] < position[i - 1])
				level++;
			tree.update(i, i, level);
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (type == 1) {
				out.printLine(tree.query(to, to) - tree.query(from, from) + 1);
			} else {
				if (array[from] > 0 && position[array[from] - 1] > from && position[array[from] - 1] <= to)
					tree.update(array[from], count - 1, -1);
				if (array[from] < count - 1 && position[array[from] + 1] > from && position[array[from] + 1] <= to)
					tree.update(array[from] + 1, count - 1, 1);
				if (array[to] > 0 && position[array[to] - 1] < to && position[array[to] - 1] > from)
					tree.update(array[to], count - 1, 1);
				if (array[to] < count - 1 && position[array[to] + 1] < to && position[array[to] + 1] > from)
					tree.update(array[to] + 1, count - 1, -1);
				int temp = array[from];
				array[from] = array[to];
				array[to] = temp;
				position[array[from]] = from;
				position[array[to]] = to;
			}
		}
	}
}
