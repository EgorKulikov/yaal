package on2014_03.on2014_03_23_OpenCup_Kazan_GP.TaskL;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskL {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[][] table = IOUtils.readIntTable(in, size, size);
		int sum = 0;
		int empty = 0;
		for (int[] row : table) {
			sum += ArrayUtils.sumArray(row);
			empty += ArrayUtils.count(row, 0);
		}
		if (empty * 2 <= size * size)
			out.printLine(sum);
		else
			out.printLine(-1);
    }
}
