package April2011.CodechefAprilLongContest;

import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Gravel implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int size = in.readInt();
		int queryCount = in.readInt();
		int startQuantity = in.readInt();
		SumIntervalTree tree = new SumIntervalTree(size);
		tree.putSegment(0, size, startQuantity);
		for (int it = 0; it < queryCount; it++) {
			char type = in.readCharacter();
			if (type == 'Q') {
				int position = in.readInt() - 1;
				out.println(tree.get(position));
			} else {
				int from = in.readInt() - 1;
				int to = in.readInt();
				int quantity = in.readInt();
				tree.putSegment(from, to, quantity);
			}
		}
	}
}

