import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int floorCount = in.readInt();
		for (int i = 0; i < count; i++) {
			int from = in.readInt();
			int to = in.readInt();
			int time = in.readInt();
			out.println(go(floorCount, from, to, time) + time);
		}
	}

	private int go(int floorCount, int from, int to, int time) {
		if (from == to)
			return 0;
		floorCount--;
		time %= 2 * floorCount;
		if (from < to) {
			if (time <= from - 1)
				return to - 1 - time;
			else
				return 2 * floorCount + to - 1 - time;
		} else {
			if (time <= 2 * floorCount - from + 1)
				return 2 * floorCount - to + 1 - time;
			else
				return 4 * floorCount - to + 1 - time;
		}
	}
}

