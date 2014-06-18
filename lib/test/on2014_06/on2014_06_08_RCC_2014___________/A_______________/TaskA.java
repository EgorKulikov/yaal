package on2014_06.on2014_06_08_RCC_2014___________.A_______________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int taskCount = in.readInt();
		int perTask = in.readInt();
		int change = in.readInt();
		char[][] can = IOUtils.readTable(in, count, taskCount);
		int[][] result = new int[taskCount + 1][count + 1];
		for (int i = 0; i < taskCount; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < count; j++) {
				if (can[j][i] == '1')
					result[i + 1][j] = Math.min(result[i][j], result[i][count] + 1);
				else
					result[i + 1][j] = Integer.MAX_VALUE;
				min = Math.min(min, result[i + 1][j]);
			}
			result[i + 1][count] = min;
		}
		int answer = ArrayUtils.minElement(result[taskCount]) * change + taskCount * perTask;
		out.printLine(answer);
    }
}
