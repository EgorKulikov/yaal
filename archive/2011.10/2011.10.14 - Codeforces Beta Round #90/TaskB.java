import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int theoremCount = in.readInt();
		int taskCount = in.readInt();
		int[] knowledge = IOUtils.readIntArray(in, theoremCount);
		boolean[] inTask = new boolean[theoremCount];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int knownTasks = in.readInt();
		int taskSize = theoremCount / taskCount;
		for (int i = 0; i < knownTasks; i++) {
			int currentScore = 0;
			for (int j = 0; j < taskSize; j++) {
				int index = in.readInt() - 1;
				currentScore += knowledge[index];
				inTask[index] = true;
			}
			min = Math.min(min, currentScore);
			max = Math.max(max, currentScore);
		}
		List<Integer> unknown = new ArrayList<Integer>();
		for (int i = 0; i < theoremCount; i++) {
			if (!inTask[i])
				unknown.add(knowledge[i]);
		}
		Collections.sort(unknown);
		if ((theoremCount - unknown.size()) / taskSize != taskCount) {
			int score = 0;
			for (int i = 0; i < taskSize; i++)
				score += unknown.get(i);
			min = Math.min(min, score);
			score = 0;
			for (int i = unknown.size() - taskSize; i < unknown.size(); i++)
				score += unknown.get(i);
			max = Math.max(max, score);
		}
		out.printf("%.8f %.8f\n", (double)min / taskSize, (double)max / taskSize);
	}
}

