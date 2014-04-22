package on2014_03.on2014_03_29_IndiaHacks2014.GottaCatchEmAll;



import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GottaCatchEmAll {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] daysRequired = IOUtils.readIntArray(in, count);
		ArrayUtils.sort(daysRequired, IntComparator.REVERSE);
		int answer = 0;
		for (int i = 0; i < count; i++)
			answer = Math.max(answer, daysRequired[i] + i + 2);
		out.printLine(answer);
	}
}
