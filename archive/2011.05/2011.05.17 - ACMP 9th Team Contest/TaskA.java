import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		int[] leftMax = new int[count];
		int[] rightMax = new int[count];
		leftMax[0] = numbers[0];
		for (int i = 1; i < count; i++)
			leftMax[i] = Math.max(leftMax[i - 1], numbers[i]);
		rightMax[count - 1] = numbers[count - 1];
		for (int i = count - 2; i >= 0; i--)
			rightMax[i] = Math.max(rightMax[i + 1], numbers[i]);
		boolean[] skip = new boolean[count];
		int skipped = 0;
		for (int i = 1; i < count - 1; i++) {
			if (numbers[i] < leftMax[i - 1] && numbers[i] < rightMax[i + 1]) {
				skip[i] = true;
				skipped++;
			}
		}
		out.println(count - skipped);
		out.print(numbers[0]);
		for (int i = 1; i < count; i++) {
			if (!skip[i])
				out.print(" " + numbers[i]);
		}
		out.println();
	}
}

