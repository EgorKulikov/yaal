package on2013_08.on2013_08_24_101_Hack_August.TwoArrays;



import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TwoArrays {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int threshold = in.readInt();
		int[] first = IOUtils.readIntArray(in, count);
		int[] second = IOUtils.readIntArray(in, count);
		Arrays.sort(first);
		ArrayUtils.sort(second, IntComparator.REVERSE);
		for (int i = 0; i < count; i++) {
			if (first[i] + second[i] < threshold) {
				out.printLine("NO");
				return;
			}
		}
		out.printLine("YES");
    }
}
