package on2013_07.on2013_07_24_Codeforces_Round__193.D___Theft_of_Blueprints;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int groups = in.readInt();
		if (groups > 2 && groups != count - 1)
			throw new RuntimeException();
		int[][] graph = new int[count][];
		for (int i = 0; i < count; i++)
			graph[i] = IOUtils.readIntArray(in, count - i - 1);
		if (groups == 1 || groups == count - 1) {
			long sum = 0;
			for (int[] row : graph) {
				for (int i : row)
					sum += Math.max(i, 0);
			}
			out.printLine(sum * 2 / count);
			return;
		}
		for (int i = 0; i < count; i++) {
			boolean good = true;
			for (int j = 0; j < i; j++) {
				if (graph[j][i - j - 1] == -1) {
					good = false;
					break;
				}
			}
			for (int j : graph[i]) {
				if (j == -1)
					good = false;
			}
			if (!good)
				continue;
			long sumGood = 0;
			long sum = 0;
			for (int j = 0; j < i; j++)
				sumGood += graph[j][i - j - 1];
			sumGood += ArrayUtils.sumArray(graph[i]);
			for (int[] row : graph) {
				for (int j : row)
					sum += Math.max(j, 0);
			}
			sum -= sumGood;
			out.printLine(((count - 1) * sumGood + sum * 2) / (count * (count - 1) / 2));
			return;
		}
		throw new RuntimeException();
    }
}
