import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int folderCount = in.readInt();
		int width = in.readInt();
		int firstIndex = in.readInt() - 1;
		int secondIndex = in.readInt() - 1;
		if (secondIndex == folderCount - 1)
			secondIndex = (secondIndex / width + 1) * width - 1;
		int firstRow = firstIndex / width;
		int secondRow = secondIndex / width;
		int firstColumn = firstIndex % width;
		int secondColumn = secondIndex % width;
		if (firstRow == secondRow) {
			out.println(1);
			return;
		}
		if (firstColumn == 0 && secondColumn == width - 1) {
			out.println(1);
			return;
		}
		if (firstRow + 1 == secondRow) {
			out.println(2);
			return;
		}
		if (firstColumn == 0 || secondColumn == width - 1 || firstColumn - 1 == secondColumn) {
			out.println(2);
			return;
		}
		out.println(3);
	}
}

