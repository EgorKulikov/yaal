package on2016_02.on2016_02_04_AIM_Tech_Round__Div__1_.B___Array_GCD;



import net.egork.generated.collections.list.IntArray;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long a = in.readInt();
        long b = in.readInt();
        int[] array = readIntArray(in, n);
        if (new IntArray(array).reduce(IntegerUtils::gcd) > 1) {
            out.printLine(0);
            return;
        }
        IntSet viable = new IntHashSet();
        for (int j = array[0] - 1; j <= array[0] + 1; j++) {
            addDivisors(viable, j);
        }
        for (int j = array[n - 1] - 1; j <= array[n - 1] + 1; j++) {
            addDivisors(viable, j);
        }
        long answer = Long.MAX_VALUE;
        long[] cost = new long[n + 1];
        for (int i : viable) {
            long best = 0;
            for (int j = 0; j < n; j++) {
                int current = array[j] + 1;
                int remainder = current % i;
                if (remainder <= 2) {
                    if (remainder != 1) {
                        best += b;
                    }
                } else {
                    best = Long.MAX_VALUE / 2;
                }
                cost[j + 1] = min(cost[j] + a, best);
            }
            answer = Math.min(answer, cost[n]);
            best = 0;
            for (int j = n - 1; j >= 0; j--) {
                int current = array[j] + 1;
                int remainder = current % i;
                if (remainder <= 2) {
                    if (remainder != 1) {
                        best += b;
                    }
                } else {
                    break;
                }
                answer = Math.min(answer, cost[j] + best);
            }
        }
        out.printLine(answer);
    }

    private void addDivisors(IntSet set, int number) {
        for (int i = 2; i * i <= number; i++) {
            while (number % i == 0) {
                set.add(i);
                number /= i;
            }
        }
        if (number != 1) {
            set.add(number);
        }
    }
}
