import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] sequence = IOUtils.readIntArray(in, count);
		for (int i = 0; i < count; i++)
			sequence[i]--;
		int[] cycle = new int[count];
		int[][] twoCycles = new int[count][2];
		int[][] threeCycles = new int[count][3];
		int twoCycleCount = 0;
		int threeCycleCount = 0;
		int answer = 0;
		int[][] initial = new int[count][];
		int[][] after = new int[count][];
		boolean[] processed = new boolean[count];
		for (int i = 0; i < count; i++) {
			if (processed[i] || sequence[i] == i)
				continue;
			int current = i;
			int length = 0;
			do {
				processed[current] = true;
				cycle[length++] = current;
				current = sequence[current];
			} while (current != i);
			while (length > 5) {
				initial[answer] = new int[5];
				after[answer] = new int[5];
				System.arraycopy(cycle, length - 5, initial[answer], 0, 5);
				System.arraycopy(cycle, length - 4, after[answer], 0, 4);
				after[answer][4] = cycle[length - 5];
//				cycle[length - 5] = cycle[length - 1];
				length -= 4;
				answer++;
			}
			if (length >= 4) {
				initial[answer] = new int[length];
				after[answer] = new int[length];
				System.arraycopy(cycle, 0, initial[answer], 0, length);
				System.arraycopy(cycle, 1, after[answer], 0, length - 1);
				after[answer][length - 1] = cycle[0];
				answer++;
			} else if (length == 3) {
				System.arraycopy(cycle, 0, threeCycles[threeCycleCount++], 0, 3);
			} else {
				System.arraycopy(cycle, 0, twoCycles[twoCycleCount++], 0, 2);
			}
		}
		while (threeCycleCount > twoCycleCount && threeCycleCount >= 2) {
			initial[answer] = new int[5];
			after[answer] = new int[5];
			System.arraycopy(threeCycles[threeCycleCount - 1], 0, initial[answer], 0, 3);
			System.arraycopy(threeCycles[threeCycleCount - 1], 1, after[answer], 0, 2);
			after[answer][2] = threeCycles[threeCycleCount - 1][0];
			System.arraycopy(threeCycles[threeCycleCount - 2], 1, initial[answer], 3, 2);
			System.arraycopy(threeCycles[threeCycleCount - 2], 2, after[answer], 3, 1);
			after[answer][4] = threeCycles[threeCycleCount - 2][1];
//			threeCycles[threeCycleCount - 2][1] = threeCycles[threeCycleCount - 2][2];
			System.arraycopy(threeCycles[threeCycleCount - 2], 0, twoCycles[twoCycleCount++], 0, 2);
			threeCycleCount -= 2;
			answer++;
		}
		if (threeCycleCount == 1 && twoCycleCount == 0) {
			initial[answer] = new int[3];
			after[answer] = new int[3];
			System.arraycopy(threeCycles[0], 0, initial[answer], 0, 3);
			System.arraycopy(threeCycles[0], 1, after[answer], 0, 2);
			after[answer][2] = threeCycles[0][0];
			answer++;
			threeCycleCount = 0;
		}
		for (int i = 0; i < threeCycleCount; i++) {
			initial[answer] = new int[5];
			after[answer] = new int[5];
			System.arraycopy(threeCycles[i], 0, initial[answer], 0, 3);
			System.arraycopy(threeCycles[i], 1, after[answer], 0, 2);
			after[answer][2] = threeCycles[i][0];
			System.arraycopy(twoCycles[i], 0, initial[answer], 3, 2);
			System.arraycopy(twoCycles[i], 1, after[answer], 3, 1);
			after[answer][4] = twoCycles[i][0];
			answer++;
		}
		for (int i = threeCycleCount; i + 1 < twoCycleCount; i += 2) {
			initial[answer] = new int[4];
			after[answer] = new int[4];
			System.arraycopy(twoCycles[i], 0, initial[answer], 0, 2);
			System.arraycopy(twoCycles[i], 1, after[answer], 0, 1);
			after[answer][1] = twoCycles[i][0];
			System.arraycopy(twoCycles[i + 1], 0, initial[answer], 2, 2);
			System.arraycopy(twoCycles[i + 1], 1, after[answer], 2, 1);
			after[answer][3] = twoCycles[i + 1][0];
			answer++;
		}
		if ((twoCycleCount - threeCycleCount) % 2 == 1) {
			initial[answer] = new int[2];
			after[answer] = new int[2];
			System.arraycopy(twoCycles[twoCycleCount - 1], 0, initial[answer], 0, 2);
			System.arraycopy(twoCycles[twoCycleCount - 1], 1, after[answer], 0, 1);
			after[answer][1] = twoCycles[twoCycleCount - 1][0];
			answer++;
		}
		out.println(answer);
		for (int i = 0; i < answer; i++) {
			int length = initial[i].length;
			for (int j = 0; j < length; j++) {
				initial[i][j]++;
				after[i][j]++;
			}
			out.println(length);
			IOUtils.printArray(Arrays.copyOf(initial[i], length), out);
			IOUtils.printArray(Arrays.copyOf(after[i], length), out);
		}
	}
}

