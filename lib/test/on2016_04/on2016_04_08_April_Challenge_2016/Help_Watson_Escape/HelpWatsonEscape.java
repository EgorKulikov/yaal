package on2016_04.on2016_04_08_April_Challenge_2016.Help_Watson_Escape;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.misc.MiscUtils.MOD7;

public class HelpWatsonEscape {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        out.printLine(k * IntegerUtils.power(k - 1, n - 1, MOD7) % MOD7);
    }
}
