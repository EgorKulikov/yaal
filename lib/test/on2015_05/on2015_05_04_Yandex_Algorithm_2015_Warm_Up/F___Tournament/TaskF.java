package on2015_05.on2015_05_04_Yandex_Algorithm_2015_Warm_Up.F___Tournament;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] level = IOUtils.readIntArray(in, count);
        long index = in.readLong();
        int[] copy = level.clone();
        Arrays.sort(copy);
        int threshold = copy[count / 2 - 1];
        boolean[] isLow = new boolean[count];
        for (int i = 0; i < count; i++) {
            isLow[i] = level[i] <= threshold;
        }
        int evenLow = 0;
        for (int i = 0; i < count; i += 2) {
            evenLow += isLow[i] ? 1 : 0;
        }
        if (evenLow != count / 4) {
            out.printLine("NO");
            return;
        }
        long total = IntegerUtils.factorial(count / 4);
        total *= total;
        if (index > total) {
            out.printLine("OVER");
            return;
        }
        index--;
        int[] answer = new int[count];
        int[] divisor = ArrayUtils.createArray(2, count / 4);
        for (int i = 0; i < count; i++) {
            if (answer[i] != 0) {
                continue;
            }
            total /= divisor[i & 1]--;
            long at = index / total;
            for (int j = i & 1; j < count; j += 2) {
                if (answer[j] == 0 && isLow[j] != isLow[i]) {
                    if (at-- == 0) {
                        answer[i] = j + 1;
                        answer[j] = i + 1;
                        break;
                    }
                }
            }
            index %= total;
        }
        out.printLine("YES");
        out.printLine(answer);
    }
}
