package on2013_10.on2013_10_10_Codeforces_Round__205.C___Find_Maximum;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		char[] max = IOUtils.readCharArray(in, count);
		long[] sums = ArrayUtils.partialSums(numbers);
		long current = 0;
		long answer = 0;
		for (int i = count - 1; i >= 0; i--) {
			if (max[i] == '1') {
				answer = Math.max(answer, current + sums[i]);
				current += numbers[i];
			}
		}
		answer = Math.max(answer, current);
		out.printLine(answer);
	}
}
