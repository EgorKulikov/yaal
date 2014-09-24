package on2014_07.on2014_07_July_Challenge_2014.Count_Substrings;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CountSubstrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		char[] s = IOUtils.readCharArray(in, length);
		long count = ArrayUtils.count(s, '1');
		out.printLine(count * (count + 1) / 2);
    }
}
