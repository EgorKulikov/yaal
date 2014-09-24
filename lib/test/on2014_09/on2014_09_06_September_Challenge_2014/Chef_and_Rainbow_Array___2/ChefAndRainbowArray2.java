package on2014_09.on2014_09_06_September_Challenge_2014.Chef_and_Rainbow_Array___2;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndRainbowArray2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(IntegerUtils.binomialCoefficient((in.readInt() - 1) / 2, 6, (long) (1e9 + 7)));
    }
}
