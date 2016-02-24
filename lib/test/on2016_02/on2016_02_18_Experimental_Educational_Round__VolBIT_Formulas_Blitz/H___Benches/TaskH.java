package on2016_02.on2016_02_18_Experimental_Educational_Round__VolBIT_Formulas_Blitz.H___Benches;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[][] c = IntegerUtils.generateBinomialCoefficients(n + 5);
        out.printLine(120 * c[n][5] * c[n][5]);
    }
}
