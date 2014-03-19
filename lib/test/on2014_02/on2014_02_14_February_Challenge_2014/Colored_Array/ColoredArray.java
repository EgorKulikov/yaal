package on2014_02.on2014_02_14_February_Challenge_2014.Colored_Array;



import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ColoredArray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int colorCount = in.readInt();
		int repaintCount = in.readInt();
		int[] start = IOUtils.readIntArray(in, count);
		int[][] win = IOUtils.readIntTable(in, count, colorCount);
		int[][] cost = IOUtils.readIntTable(in, count, colorCount);
		MiscUtils.decreaseByOne(start);
		int[] delta = new int[count];
		int answer = 0;
		for (int i = 0; i < count; i++) {
			int base = win[i][start[i]];
			answer += base;
			for (int j = 0; j < colorCount; j++)
				delta[i] = Math.max(delta[i], win[i][j] - cost[i][j] - base);
		}
		ArrayUtils.sort(delta, IntComparator.REVERSE);
		for (int i = 0; i < repaintCount && i < count; i++)
			answer += Math.max(0, delta[i]);
		out.printLine(answer);
    }
}
