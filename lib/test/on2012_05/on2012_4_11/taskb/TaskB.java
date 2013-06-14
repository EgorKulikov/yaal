package on2012_05.on2012_4_11.taskb;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int carCount = in.readInt();
		int roundCount = in.readInt();
		int[][][] time = new int[carCount][][];
		for (int i = 0; i < carCount; i++)
			time[i] = IOUtils.readIntTable(in, count, count);
		for (int i = 0; i < carCount; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					for (int l = 0; l < count; l++)
						time[i][k][l] = Math.min(time[i][k][l], time[i][k][j] + time[i][j][l]);
				}
			}
		}
		int[][] minTime = new int[count][count];
		ArrayUtils.fill(minTime, Integer.MAX_VALUE);
		for (int i = 0; i < carCount; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++)
					minTime[j][k] = Math.min(minTime[j][k], time[i][j][k]);
			}
		}
		int[][][] answer = new int[count][count][count + 1];
		ArrayUtils.fill(answer, Integer.MAX_VALUE / 2);
		for (int i = 0; i < count; i++) {
			Arrays.fill(answer[i][i], 0);
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					for (int l = 0; l < count; l++)
						answer[i][l][j + 1] = Math.min(answer[i][l][j + 1], answer[i][k][j] + minTime[k][l]);
				}
			}
		}
		for (int i = 0; i < roundCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int changes = in.readInt() + 1;
			out.printLine(answer[from][to][Math.min(changes, count)]);
		}
	}
}
