package on2013_10.on2013_10_17_ACM_Central_Subregional.TaskK;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[][][] qty = new int[3][count][count];
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] color = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, color);
		MiscUtils.decreaseByOne(from, to, color);
		for (int i = 0; i < edgeCount; i++) {
			qty[color[i]][from[i]][to[i]]++;
			qty[color[i]][to[i]][from[i]]++;
		}
		long answer = 0;
		for (int i = 0; i < edgeCount; i++) {
			for (int j = i + 1; j < edgeCount; j++) {
				if (color[i] == color[j])
					continue;
				if (from[i] == from[j])
					answer += qty[3 - color[i] - color[j]][to[i]][to[j]];
				else if (from[i] == to[j])
					answer += qty[3 - color[i] - color[j]][to[i]][from[j]];
				else if (to[i] == from[j])
					answer += qty[3 - color[i] - color[j]][from[i]][to[j]];
				else if (to[i] == to[j])
					answer += qty[3 - color[i] - color[j]][from[i]][from[j]];
			}
		}
		out.printLine(answer / 3);
    }
}
