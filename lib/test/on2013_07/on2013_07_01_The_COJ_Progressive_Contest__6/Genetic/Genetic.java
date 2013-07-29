package on2013_07.on2013_07_01_The_COJ_Progressive_Contest__6.Genetic;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Genetic {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String generated = in.readString();
		String original = in.readString();
		out.printLine(StringUtils.contains(generated, original) && StringUtils.count(generated, 'T') > 0 ? 'Y' : 'N');
    }
}
