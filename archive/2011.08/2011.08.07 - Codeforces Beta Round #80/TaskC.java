import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long slotCount = in.readLong();
		long bulletCount = in.readLong();
		int positionCount = in.readInt();
		long[] positions = IOUtils.readLongArray(in, positionCount);
		for (long position : positions)
			out.print(getState(slotCount, bulletCount, position - 1));
		out.println();
	}

	private char getState(long slotCount, long bulletCount, long position) {
		if (bulletCount == 0)
			return '.';
		long freeCount = slotCount - bulletCount;
		if (slotCount % 2 == 1) {
			if (position == slotCount - 1)
				return 'X';
			bulletCount--;
		}
		long pairs = Math.min(freeCount, bulletCount);
		long freeStart = freeCount - pairs;
		long bulletEnd = bulletCount - pairs;
		if (position < freeStart)
			return '.';
		else
			position -= freeStart;
		if (position < 2 * pairs)
			return position % 2 == 0 ? '.' : 'X';
		else
			return 'X';
	}
}

