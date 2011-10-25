import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TheOctopus implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int peopleCount = in.readInt();
		int capacity = in.readInt();
		if (peopleCount == 0 && capacity == 0) {
			Exit.exit(in, out);
			return;
		}
		final int[] weights = IOUtils.readIntArray(in, peopleCount);
		final int[] count = new int[peopleCount];
		Arrays.fill(count, 1);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(peopleCount);
		setSystem.setListener(new IndependentSetSystem.Listener() {
			public void joined(int joinedRoot, int root) {
				weights[root] += weights[joinedRoot];
				weights[joinedRoot] = 0;
				count[root] += count[joinedRoot];
				count[joinedRoot] = 0;
			}
		});
		for (int i = 0; i < peopleCount; i++) {
			int likeCount = in.readInt();
			for (int j = 0; j < likeCount; j++) {
				int person = in.readInt() - 1;
				setSystem.join(i, person);
			}
		}
		int[] answer = new int[capacity + 1];
		for (int i = 0; i < peopleCount; i++) {
			if (count[i] == 0)
				continue;
			int weight = weights[i];
			for (int j = capacity; j >= weight; j--)
				answer[j] = Math.max(answer[j], answer[j - weight] + count[i]);
		}
		out.println(answer[capacity]);
	}
}

