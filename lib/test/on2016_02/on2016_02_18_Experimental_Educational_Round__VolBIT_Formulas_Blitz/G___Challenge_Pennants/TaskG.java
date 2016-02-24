package on2016_02.on2016_02_18_Experimental_Educational_Round__VolBIT_Formulas_Blitz.G___Challenge_Pennants;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[][] c = IntegerUtils.generateBinomialCoefficients(n + 5);
        long answer = c[n + 2][3] * c[n + 4][5];
        out.printLine(answer);
    }
}
