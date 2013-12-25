package on2012_06.on2012_5_1.littleelephantandcandies;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittleElephantAndCandies {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int candies = in.readInt();
		if (candies >= ArrayUtils.sumArray(IOUtils.readIntArray(in, count)))
			out.printLine("Yes");
		else
			out.printLine("No");
	}
}
