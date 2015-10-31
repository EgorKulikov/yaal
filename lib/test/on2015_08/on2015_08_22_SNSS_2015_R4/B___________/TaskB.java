package on2015_08.on2015_08_22_SNSS_2015_R4.B___________;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[][] discs = IOUtils.readIntTable(in, n, 3);
        MiscUtils.decreaseByOne(discs);
        int[] last = new int[8];
        long ways = IntegerUtils.power(3, 8);
        for (int i = 0; i < ways; i++) {
            int current = i;
            for (int j = 0; j < 8; j++) {
                last[j] = current % 3;
                current /= 3;
            }
            boolean ok = true;
            for (int j = n - 1; j >= n - 6 && j >= 0; j--) {
                int allowed = n - j + 2;
                int at = 0;
                for (int k = 0; k < allowed && at < 3; k++) {
                    if (discs[j][at] == last[k]) {
                        at++;
                    }
                }
                if (at != 3) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                out.printLine(n + 2);
                return;
            }
        }
        out.printLine(n + 3);
    }
}
