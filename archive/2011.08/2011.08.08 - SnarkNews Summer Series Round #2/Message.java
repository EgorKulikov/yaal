import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Message implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		long[] heights = IOUtils.readLongArray(in, 2 * count);
		long currentWidth = 0;
		for (int i = 0; i < count; i++)
			currentWidth += heights[i];
		long answer = currentWidth * (heights[0] + heights[count]);
		for (int i = 1; i <= count; i++) {
			currentWidth -= heights[count - i];
			currentWidth += heights[2 * count - 2 * i];
			answer = Math.min(answer, currentWidth * (heights[0] + heights[count - i]));
		}
		out.println(answer);
	}
}

