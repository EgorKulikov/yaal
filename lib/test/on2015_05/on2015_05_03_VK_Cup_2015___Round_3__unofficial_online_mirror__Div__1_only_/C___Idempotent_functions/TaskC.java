package on2015_05.on2015_05_03_VK_Cup_2015___Round_3__unofficial_online_mirror__Div__1_only_.C___Idempotent_functions;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] f = IOUtils.readIntArray(in, count);
        MiscUtils.decreaseByOne(f);
        boolean[] present = ArrayUtils.createArray(count, true);
        boolean[] next = new boolean[count];
        int min = 0;
        while (true) {
            Arrays.fill(next, false);
            for (int i = 0; i < count; i++) {
                if (present[i]) {
                    next[f[i]] = true;
                }
            }
            boolean same = true;
            for (int i = 0; i < count; i++) {
                if (present[i] != next[i]) {
                    same = false;
                    break;
                }
            }
            if (same) {
                break;
            }
            boolean[] temp = present;
            present = next;
            next = temp;
            min++;
        }
        BigInteger answer = BigInteger.ONE;
        for (int i = 0; i < count; i++) {
            if (!present[i]) {
                continue;
            }
            int length = 0;
            int j = i;
            do {
                j = f[j];
                length++;
            } while (j != i);
            BigInteger current = BigInteger.valueOf(length);
            answer = answer.multiply(current).divide(answer.gcd(current));
        }
        BigInteger atLeast = BigInteger.valueOf(min);
        BigInteger baseAnswer = answer;
        while (answer.compareTo(atLeast) < 0) {
            answer = answer.add(baseAnswer);
        }
        out.printLine(answer);
    }
}
