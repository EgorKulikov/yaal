import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count1 = in.readInt();
		int count2 = in.readInt();
		int count3 = in.readInt();
		int time1 = in.readInt();
		int time2 = in.readInt();
		int time3 = in.readInt();
		int count = in.readInt();
		long[] arrival = IOUtils.readLongArray(in, count);
		long[] time = arrival.clone();
		apply(time, count1, time1);
		apply(time, count2, time2);
		apply(time, count3, time3);
		long answer = 0;
		for (int i = 0; i < count; i++)
			answer = Math.max(answer, time[i] - arrival[i]);
		out.println(answer);
	}

	private void apply(long[] time, int windowCount, int length) {
		windowCount = Math.min(windowCount, time.length);
		long[] ended = new long[windowCount];
		for (int i = 0; i < time.length; i++)
			time[i] = ended[i % windowCount] = Math.max(time[i], ended[i % windowCount]) + length;
	}
}

