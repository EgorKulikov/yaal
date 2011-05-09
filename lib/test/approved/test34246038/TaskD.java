package approved.test34246038;

import net.egork.io.IOUtils;
import net.egork.numbers.Rational;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int racerCount = in.readInt();
		int length = in.readInt();
		int[] speeds = IOUtils.readIntArray(in, racerCount);
		boolean[] finished = new boolean[racerCount];
		int finishedCount = 0;
		for (int i = 0; ; i++) {
			Rational last = Rational.MIN_VALUE;
			int index = 0;
			for (int j = 0; j < racerCount; j++) {
				if (!finished[j]) {
					Rational finishTime = new Rational(length + speeds[j] * (index++), speeds[j]);
					if (last.compareTo(finishTime) < 0) {
						last = finishTime;
						finished[j] = true;
						finishedCount++;
					}
				}
			}
			if (finishedCount == racerCount) {
				out.println(i + 1);
				return;
			}
		}
	}
}

