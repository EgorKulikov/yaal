package on2012_08.on2012_7_14.taskc;



import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int workDays = in.readInt();
		int daysOff = in.readInt();
		int capacity = in.readInt();
		int[] answer;
		if (capacity > 1) {
			if (workDays > daysOff) {
				answer = new int[2 * capacity];
				Arrays.fill(answer, 0, capacity, 1);
				answer[capacity] = workDays;
				Arrays.fill(answer, capacity + 1, 2 * capacity, workDays + 1);
			} else {
				answer = new int[2 * capacity + 1];
				Arrays.fill(answer, 0, capacity, 1);
				answer[capacity] = workDays;
				Arrays.fill(answer, capacity + 1, 2 * capacity, workDays + 1);
				answer[2 * capacity] = 2 * workDays;
			}
		} else {
			if (workDays > daysOff + 1) {
				answer = new int[2];
				answer[0] = 1;
				answer[1] = workDays;
			} else if (workDays != 2 || daysOff == 1) {
				answer = new int[3];
				answer[0] = 1;
				answer[1] = workDays;
				answer[2] = 2 * workDays - 1;
			} else {
				answer = new int[4];
				answer[0] = 1;
				answer[1] = 2;
				answer[2] = 3;
				answer[3] = 4;
			}
		}
		out.printLine(answer.length);
		out.printLine(Array.wrap(answer).toArray());
	}
}
