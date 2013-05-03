package on2012_12.on2012_12_26_Volume_10._1935___Tears_of_Drowned;



import net.egork.misc.ArrayUtils;
import net.egork.collections.intcollection.IntArray;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1935 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] pages = IOUtils.readIntArray(in, count);
        long answer = ArrayUtils.sumArray(pages) + new IntArray(pages).max();
        out.printLine(answer);
    }
}
