package April2011.CodeforcesUkrainianSchoolOlympiad;

import net.egork.collections.function.Function;
import net.egork.collections.sequence.ArrayWrapper;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.collections.sequence.SequenceUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskF implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int eventCount = in.readInt();
		int[] position = new int[eventCount];
		int[] time = new int[eventCount];
		in.readIntArrays(position, time);
		long speed = in.readInt();
		final long[] leftValue = new long[eventCount];
		final long[] rightValue = new long[eventCount];
		for (int i = 0; i < eventCount; i++) {
			leftValue[i] = time[i] * speed - position[i];
			rightValue[i] = time[i] * speed + position[i];
		}
		Integer[] order = SequenceUtils.order(ArrayWrapper.wrap(leftValue), ArrayWrapper.wrap(rightValue));
		int[] answer = new int[2];
		for (int i = 0; i < 2; i++) {
			final long[] bestRightValue = new long[eventCount];
			int length = 0;
			for (final int j : order) {
				if (i == 0 && Math.abs(position[j]) > speed * time[j])
					continue;
				int index = (int) MiscUtils.binarySearch(0, length, new Function<Long, Boolean>() {
					public Boolean value(Long argument) {
						return bestRightValue[(int) (long) argument] > rightValue[j];
					}
				});
				bestRightValue[index] = rightValue[j];
				if (index == length)
					length++;
			}
			answer[i] = length;
		}
		IOUtils.printArray(answer, out);
	}
}

