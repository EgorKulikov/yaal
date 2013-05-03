package on2012_08.on2012_08_31_Codeforces_Round__136__Div__1_.A___Little_Elephant_and_Problem;



import net.egork.misc.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[] sorted = array.clone();
		ArrayUtils.sort(sorted, IntComparator.DEFAULT);
		int differs = 0;
		for (int i = 0; i < count; i++) {
			if (array[i] != sorted[i])
				differs++;
		}
		if (differs <= 2)
			out.printLine("YES");
		else
			out.printLine("NO");
	}
}
