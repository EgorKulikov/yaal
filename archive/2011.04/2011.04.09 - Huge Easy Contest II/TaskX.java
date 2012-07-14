package April2011.UVaHugeEasyContestII;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskX implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int lightCount = in.readInt();
		int switchCount = in.readInt();
		int[] switches = new int[switchCount];
		for (int i = 0; i < switchCount; i++) {
			for (int j = 0; j < lightCount; j++)
				switches[i] += in.readInt() << j;
		}
		int[] result = new int[1 << lightCount];
		Arrays.fill(result, Integer.MIN_VALUE);
		result[0] = 0;
		int[] queue = new int[1 << lightCount];
		int size = 1;
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			for (int switchType : switches) {
				int next = current ^ switchType;
				if (result[next] == Integer.MIN_VALUE) {
					result[next] = result[current] + 1;
					queue[size++] = next;
				}
			}
		}
		int answer = result[(1 << lightCount) - 1];
		out.print("Case " + testNumber + ": ");
		if (answer == Integer.MIN_VALUE)
			out.println("IMPOSSIBLE");
		else
			out.println(answer);
	}
}

