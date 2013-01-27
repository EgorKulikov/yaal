package on2013_01.on2013_01_25_.SortTest;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArray;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SortTest {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		out.printLine(new IntArray(array).inPlaceSort(IntComparator.REVERSE));
	}
}
