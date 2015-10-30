package on2015_07.on2015_07_07_The_COJ_Progressive_Contest__9.HelpingTheArchitect;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HelpingTheArchitect {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int g = IntegerUtils.gcd(a, b);
        out.printLine((2 * a + 2 * b) / g);
    }
}
