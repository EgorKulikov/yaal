package on2013_07.on2013_07_02_World_Finals_Dress_Rehearsal.TaskL;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskL {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		out.printLine("Case", testNumber + ":", ArrayUtils.minElement(numbers), ArrayUtils.maxElement(numbers), ArrayUtils.maxElement(numbers) - ArrayUtils.minElement(numbers));
    }
}
