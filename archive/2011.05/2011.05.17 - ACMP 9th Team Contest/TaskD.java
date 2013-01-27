import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		Arrays.sort(numbers);
		int[] result = new int[count];
		for (int i = 0; i < count; i += 2)
			result[i / 2] = numbers[i];
		for (int i = 1; i < count; i += 2)
			result[count - 1 - i / 2] = numbers[i];
		IOUtils.printArray(result, out);
	}
}

