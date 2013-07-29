package on2013_07.on2013_07_01_The_COJ_Progressive_Contest__6.TheSockDrawerI;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheSockDrawerI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] number = IOUtils.readIntArray(in, count);
		out.printLine("Case", testNumber + ":", count + 1, ArrayUtils.maxElement(number) + 1);
    }
}
