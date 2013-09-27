package on2013_09.on2013_09_25_Codeforces_Trainings_Season_1_Episode_3.TaskB;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count];
		int[] to = new int[count];
		int[] size = new int[count];
		IOUtils.readIntArrays(in, from, to, size);
		MiscUtils.decreaseByOne(from, to);
		int[] next = ArrayUtils.createOrder(50000);
		LongIntervalTree tree = new SumIntervalTree(50000);
		int[] order = ArrayUtils.order(to);
		for (int i : order) {
			long already = tree.query(from[i], to[i]);
			int remaining = (int) (size[i] - already);
			int current = to[i];
			for (int j = 0; j < remaining; j++) {
				current = get(current, next);
				next[current] = current - 1;
				tree.update(current, current, 1);
				current--;
			}
		}
		out.printLine(tree.query(0, 49999));
    }

	private int get(int current, int[] next) {
		if (next[current] == current)
			return current;
		return next[current] = get(next[current], next);
	}
}
