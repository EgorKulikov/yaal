package on2013_10.on2013_10_10_Codeforces_Round__205.A___Domino;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] high = new int[count];
		int[] low = new int[count];
		IOUtils.readIntArrays(in, high, low);
		if (ArrayUtils.sumArray(high) % 2 != ArrayUtils.sumArray(low) % 2) {
			out.printLine(-1);
			return;
		}
		if (ArrayUtils.sumArray(high) % 2 == 0) {
			out.printLine(0);
			return;
		}
		for (int i = 0; i < count; i++) {
			if (high[i] % 2 != low[i] % 2) {
				out.printLine(1);
				return;
			}
		}
		out.printLine(-1);
    }
}
