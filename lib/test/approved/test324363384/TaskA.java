package approved.test324363384;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.io.old.InputReader;

import java.io.PrintWriter;
import java.util.InputMismatchException;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size, queryCount;
		try {
			size = in.readInt();
			queryCount = in.readInt();
		} catch (InputMismatchException e) {
			Exit.exit(in, out);
			return;
		}
		int[] index = new int[size + 1];
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(size + queryCount);
		final int[] count = new int[size + queryCount];
		final long[] sum = new long[size + queryCount];
		for (int i = 0; i < size; i++) {
			index[i + 1] = i;
			count[i] = 1;
			sum[i] = i + 1;
		}
		setSystem.setListener(new IndependentSetSystem.Listener() {
			public void joined(int joinedRoot, int root) {
				count[root] += count[joinedRoot];
				sum[root] += sum[joinedRoot];
				count[joinedRoot] = 0;
				sum[joinedRoot] = 0;
			}
		});
		int next = size;
		for (int it = 0; it < queryCount; it++) {
			int type = in.readInt();
			if (type == 1) {
				int first = index[in.readInt()];
				int second = index[in.readInt()];
				setSystem.join(first, second);
			} else if (type == 2) {
				int first = in.readInt();
				int second = in.readInt();
				if (first == second)
					continue;
				int firstIndex = setSystem.get(index[first]);
				count[firstIndex]--;
				sum[firstIndex] -= first;
				index[first] = next;
				count[next] = 1;
				sum[next] = first;
				setSystem.join(index[second], next++);
			} else {
				int firstIndex = setSystem.get(index[in.readInt()]);
				out.println(count[firstIndex] + " " + sum[firstIndex]);
			}
		}
	}
}

