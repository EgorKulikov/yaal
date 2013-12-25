package on2013_09.on2013_09_28_COCI_Round__1.TaskC;



import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[][] profit = IOUtils.readIntTable(in, size, size);
		int[][] sums = new int[size + 1][size + 1];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				sums[i + 1][j + 1] = sums[i][j + 1] + sums[i + 1][j] - sums[i][j] + profit[i][j];
		}
		long answer = 0;
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				Counter<Integer> counter = new Counter<Integer>();
				for (int k = 0; k < i; k++) {
					for (int l = 0; l < j; l++)
						counter.add(sums[i][j] - sums[i][l] - sums[k][j] + sums[k][l]);
				}
				for (int k = i + 1; k <= size; k++) {
					for (int l = j + 1; l <= size; l++)
						answer += counter.get(sums[i][j] - sums[i][l] - sums[k][j] + sums[k][l]);
				}
				counter.clear();
				for (int k = 0; k < i; k++) {
					for (int l = j + 1; l <= size; l++)
						counter.add(sums[i][j] - sums[i][l] - sums[k][j] + sums[k][l]);
				}
				for (int k = i + 1; k <= size; k++) {
					for (int l = 0; l < j; l++)
						answer += counter.get(sums[i][j] - sums[i][l] - sums[k][j] + sums[k][l]);
				}
			}
		}
		out.printLine(answer);
    }
}
