package on2016_02.on2016_02_18_Experimental_Educational_Round__VolBIT_Formulas_Blitz.J___Divisibility;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.IntegerUtils;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        long x = 1;
        for (int i = 2; i <= 10; i++) {
            x = IntegerUtils.lcm(i, x);
        }
        out.printLine(n / x);
    }
}
