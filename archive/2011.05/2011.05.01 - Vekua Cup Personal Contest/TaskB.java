import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int boxCount = in.readInt();
		int queryCount = in.readInt() + in.readInt();
		SumIntervalTree tree = new SumIntervalTree(boxCount);
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == 'P') {
				int index = in.readInt() - 1;
				int quantity = in.readInt();
				tree.put(index, quantity);
			} else {
				int from = in.readInt() - 1;
				int to = in.readInt();
				out.println(tree.getSegment(from, to));
			}
		}
	}
}

