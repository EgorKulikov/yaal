package on2017_04.on2017_04_16_RCC_2017_Second_qualification_round.B___________________________;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.numbers.IntegerUtils.gcd;
import static net.egork.numbers.IntegerUtils.lcm;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int c = in.readInt();
        int d = in.readInt();
        out.printLine(lcm(a, c), gcd(b, d));
    }
}
