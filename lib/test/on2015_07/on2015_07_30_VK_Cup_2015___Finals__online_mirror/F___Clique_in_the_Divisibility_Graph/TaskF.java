package on2015_07.on2015_07_30_VK_Cup_2015___Finals__online_mirror.F___Clique_in_the_Divisibility_Graph;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] a = IOUtils.readIntArray(in, count);
        int[] div = IntegerUtils.generateDivisorTable(1000001);
        int[] ans = new int[1000001];
        for (int i = 1; i <= 1000000; i++) {
            int cur = i;
            while (cur > 1) {
                ans[i] = Math.max(ans[i], ans[i / div[cur]]);
                int d = div[cur];
                do {
                    cur /= d;
                } while (cur % d == 0);
            }
            if (Arrays.binarySearch(a, i) >= 0) {
                ans[i]++;
            }
        }
        out.printLine(ArrayUtils.maxElement(ans));
    }
}
