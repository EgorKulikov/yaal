package on2016_04.on2016_04_22_Vekua_Cup_2016_Personal_Pre_Run.H___Hacker_s_World;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.util.Arrays.fill;
import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.sumArray;
import static net.egork.misc.MiscUtils.MOD7;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int r = in.readInt();
        int l = in.readInt();
        int[] d = readIntArray(in, n);
        int sum = (int) sumArray(d);
        if (sum % 9 != ((r + l) % 9)) {
            int answer = 0;
            if (sum % 9 == r % 9) {
                answer++;
            }
            if (sum % 9 == l % 9) {
                answer++;
            }
            out.printLine(answer);
            return;
        }
        long[] ways = new long[9];
        ways[0] = 1;
        long[] next = new long[9];
        for (int i : d) {
            fill(next, 0);
            for (int j = 0; j < 9; j++) {
                next[j] += ways[j];
                next[(j + i) % 9] += ways[j];
            }
            for (int j = 0; j < 9; j++) {
                ways[j] = next[j] % MOD7;
            }
        }
        out.printLine(ways[l % 9]);
    }
}
