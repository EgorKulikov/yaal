package on2011_11.on2011_10_18.taskb;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		long sum = ArrayUtils.sumArray(numbers);
		int head = 0;
		for (int i = 0; i < count; i++) {
			head += numbers[i];
			if ((head << 1) == sum) {
				out.printLine(i + 1);
				return;
			}
		}
		out.printLine(-1);
	}
}
