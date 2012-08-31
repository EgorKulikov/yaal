package on2011_11.on2011_10_19.taskc0;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int colorCount = in.readInt();
		int maxSegment = in.readInt();
		int[] colors = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(colors);
		int[] perColor = new int[colorCount];
		for (int i : colors)
			perColor[i]++;
		int[][] indices = new int[colorCount][];
		for (int i = 0; i < colorCount; i++)
			indices[i] = new int[perColor[i]];
		for (int i1 = 0, colorsLength = colors.length; i1 < colorsLength; i1++) {
			int i = colors[i1];
			indices[i][--perColor[i]] = i1;
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < colorCount; i++) {
			int last = 0;
			Arrays.sort(indices[i]);
			int current = 0;
			for (int j : indices[i]) {
				current += (j - last + maxSegment - 1) / maxSegment;
				last = j + 1;
			}
			current += (count - last + maxSegment - 1) / maxSegment;
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
	}
}
