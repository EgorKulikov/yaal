package on2016_02.on2016_02_18_Experimental_Educational_Round__VolBIT_Formulas_Blitz.F___Selection_of_Personnel;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long answer = 0;
        long[][] c = IntegerUtils.generateBinomialCoefficients(n + 1);
        for (int i = 5; i <= 7; i++) {
            answer += c[n][i];
        }
        out.printLine(answer);
    }
}
