package on2015_07.on2015_07_07_The_COJ_Progressive_Contest__9.TheEduardosGame;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheEduardosGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long count = in.readLong();
        out.printLine(IntegerUtils.power(2, count, (long) (1e9 + 7)) - 1);
    }
}
