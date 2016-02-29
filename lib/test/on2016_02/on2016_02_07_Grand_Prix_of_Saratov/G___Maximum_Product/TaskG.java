package on2016_02.on2016_02_07_Grand_Prix_of_Saratov.G___Maximum_Product;



import net.egork.numbers.NumberIterator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskG {
    long answer;
    long product = -1;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.readLong();
        long b = in.readLong();
        answer = a;
        NumberIterator iterator = new NumberIterator() {
            @Override
            protected void process(long prefix, int remainingDigits) {
                long current = 1;
                long cp = prefix;
                while (prefix != 0) {
                    current *= prefix % 10;
                    prefix /= 10;
                }
                prefix = cp;
                for (int i = 0; i < remainingDigits; i++) {
                    current *= 9;
                    prefix *= 10;
                    prefix += 9;
                }
                if (current > product) {
                    product = current;
                    answer = prefix;
                }
            }
        };
        iterator.run(a, b);
        out.printLine(answer);
    }
}
