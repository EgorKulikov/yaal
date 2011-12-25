package on2011_11_1.task1726;



import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Task1726 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		long result = go(x) + go(y);
		result /= count;
		result /= count - 1;
		out.printLine(result);
	}

	private long go(int[] array) {
		Arrays.sort(array);
		long result = 0;
		long current = 0;
		for (int i = 1; i < array.length; i++)
			current += array[i] - array[0];
		result += current;
		for (int i = 1; i < array.length; i++) {
			current += (long)(2 * i - array.length) * (array[i] - array[i - 1]);
			result += current;
		}
		return result;
	}
}
