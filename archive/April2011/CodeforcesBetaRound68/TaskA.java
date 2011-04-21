package April2011.CodeforcesBetaRound68;

import net.egork.collections.sequence.ArrayWrapper;
import net.egork.collections.sequence.SequenceUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int participantCount = in.readInt();
		String[] names = new String[participantCount];
		int[] points = new int[participantCount];
		for (int i = 0; i < participantCount; i++) {
			names[i] = in.readString();
			points[i] += 100 * in.readInt();
			points[i] -= 50 * in.readInt();
			for (int j = 0; j < 5; j++)
				points[i] += in.readInt();
		}
		out.println(names[SequenceUtils.maxIndex(ArrayWrapper.wrap(points))]);
	}
}

