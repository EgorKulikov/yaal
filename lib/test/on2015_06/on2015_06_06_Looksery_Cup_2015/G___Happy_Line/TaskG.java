package on2015_06.on2015_06_06_Looksery_Cup_2015.G___Happy_Line;


import net.egork.generated.collections.comparator.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] money = IOUtils.readIntArray(in, count);
        ArrayUtils.reverse(money);
        for (int i = 0; i < count; i++) {
            money[i] -= i;
        }
        ArrayUtils.sort(money, IntComparator.REVERSE);
        for (int i = 0; i < count; i++) {
            money[i] += i;
        }
        for (int i = 1; i < count; i++) {
            if (money[i] > money[i - 1]) {
                out.printLine(":(");
                return;
            }
        }
        ArrayUtils.reverse(money);
        out.printLine(money);
    }
}
