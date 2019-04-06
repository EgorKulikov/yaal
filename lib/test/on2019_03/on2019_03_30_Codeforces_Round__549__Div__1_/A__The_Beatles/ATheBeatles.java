package on2019_03.on2019_03_30_Codeforces_Round__549__Div__1_.A__The_Beatles;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Long.MIN_VALUE;
import static java.lang.Math.abs;
import static net.egork.numbers.IntegerUtils.gcd;

public class ATheBeatles {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int a = in.readInt();
        int b = in.readInt();
        int d1 = abs(a - b);
        int d2 = k - a - b;
        long all = (long)n * k;
        long min = MAX_VALUE;
        long max = MIN_VALUE;
        for (int i = 0; i < n; i++) {
            long g = gcd((long)k * i + d1, all);
            long c = all / g;
            min = Math.min(min, c);
            max = Math.max(max, c);
            g = gcd((long)k * i + d2, all);
            c = all / g;
            min = Math.min(min, c);
            max = Math.max(max, c);
        }
        out.printLine(min, max);
    }
}
