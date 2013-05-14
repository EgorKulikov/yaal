package on2013_03.on2013_03_11_USACO_2013_March_Contest__Gold.CowRun;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CowRun {
	long[][] left;
	long[][] right;
	int[] positions;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		positions = IOUtils.readIntArray(in, count);
		positions = Arrays.copyOf(positions, count + 1);
		Arrays.sort(positions);
		int zeroAt = Arrays.binarySearch(positions, 0);
		left = new long[count + 1][count + 1];
		ArrayUtils.fill(left, -1);
		right = new long[count + 1][count + 1];
		ArrayUtils.fill(right, -1);
		out.printLine(goLeft(zeroAt, zeroAt));
	}

	private long goLeft(int from, int to) {
		if (left[from][to] != -1)
			return left[from][to];
		long remaining = from + positions.length - 1 - to;
		if (remaining == 0)
			return left[from][to] = 0;
		left[from][to] = Long.MAX_VALUE;
		if (from != 0)
			left[from][to] = Math.min(left[from][to], goLeft(from - 1, to) + remaining * (positions[from] - positions[from - 1]));
		if (to != positions.length - 1)
			left[from][to] = Math.min(left[from][to], goRight(from, to + 1) + remaining * (positions[to + 1] - positions[from]));
		return left[from][to];
	}

	private long goRight(int from, int to) {
		if (right[from][to] != -1)
			return right[from][to];
		long remaining = from + positions.length - 1 - to;
		if (remaining == 0)
			return right[from][to] = 0;
		right[from][to] = Long.MAX_VALUE;
		if (from != 0)
			right[from][to] = Math.min(right[from][to], goLeft(from - 1, to) + remaining * (positions[to] - positions[from - 1]));
		if (to != positions.length - 1)
			right[from][to] = Math.min(right[from][to], goRight(from, to + 1) + remaining * (positions[to + 1] - positions[to]));
		return right[from][to];
	}
}
