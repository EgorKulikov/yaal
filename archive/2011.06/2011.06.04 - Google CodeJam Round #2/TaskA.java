import net.egork.collections.sequence.Array;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Locale;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		Locale.setDefault(Locale.US);
		int length = in.readInt();
		int speed = in.readInt();
		int runSpeed = in.readInt() - speed;
		double runTime = in.readInt();
		int walkwayCount = in.readInt();
		int[] walkwayLengths = new int[walkwayCount + 1];
		int[] walkwaySpeeds = new int[walkwayCount + 1];
		walkwayLengths[walkwayCount] = length;
		walkwaySpeeds[walkwayCount] = speed;
		for (int i = 0; i < walkwayCount; i++) {
			int begin = in.readInt();
			int end = in.readInt();
			walkwayLengths[i] = end - begin;
			walkwayLengths[walkwayCount] -= walkwayLengths[i];
			walkwaySpeeds[i] = in.readInt() + speed;
		}
		Integer[] order = SequenceUtils.order(Array.wrap(walkwaySpeeds));
		double answer = 0;
		for (int i : order) {
			double currentRunTime = Math.min(runTime, (double)walkwayLengths[i] / (walkwaySpeeds[i] + runSpeed));
			answer += currentRunTime;
			runTime -= currentRunTime;
			double remainingDistance = walkwayLengths[i] - currentRunTime * (walkwaySpeeds[i] + runSpeed);
			answer += remainingDistance / walkwaySpeeds[i];
		}
		out.printf("Case #" + testNumber + ": %.9f\n", answer);
	}
}

