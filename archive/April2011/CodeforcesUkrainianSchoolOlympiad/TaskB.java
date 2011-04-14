package April2011.CodeforcesUkrainianSchoolOlympiad;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int miceCount = in.readInt();
		int cheeseCount = in.readInt();
		in.readIntArray(2);
		int[] micePosition = in.readIntArray(miceCount);
		int[] cheesePosition = in.readIntArray(cheeseCount);
		int cheeseIndex = 0;
		int lastLength = Math.abs(micePosition[0] - cheesePosition[0]);
		for (; cheeseIndex + 1 < cheeseCount; cheeseIndex++) {
			if (lastLength <= Math.abs(micePosition[0] - cheesePosition[cheeseIndex + 1]))
				break;
			lastLength = Math.abs(micePosition[0] - cheesePosition[cheeseIndex + 1]);
		}
		int hungry = 0;
		for (int i = 1; i < miceCount; i++) {
			int curLength = Math.abs(micePosition[i] - cheesePosition[cheeseIndex]);
			int nextLength = cheeseIndex == cheeseCount - 1 ? Integer.MAX_VALUE : Math.abs(micePosition[i] - cheesePosition[cheeseIndex + 1]);
			if (curLength < nextLength) {
				if (curLength != lastLength)
					hungry++;
				lastLength = Math.min(lastLength, curLength);
			} else if (curLength > nextLength) {
				curLength = nextLength;
				cheeseIndex++;
				while (cheeseIndex + 1 < cheeseCount && Math.abs(micePosition[i] - cheesePosition[cheeseIndex + 1]) < curLength)
					curLength = Math.abs(micePosition[i] - cheesePosition[++cheeseIndex]);
				lastLength = curLength;
			} else {
				if (lastLength != curLength) {
					cheeseIndex++;
					lastLength = curLength;
				}
			}
		}
		out.println(hungry);
	}
}

