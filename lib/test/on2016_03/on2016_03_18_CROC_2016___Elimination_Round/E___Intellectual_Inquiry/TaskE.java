package on2016_03.on2016_03_18_CROC_2016___Elimination_Round.E___Intellectual_Inquiry;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.order;

public class TaskE {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        char[] t = in.readString().toCharArray();
        long[] qty = new long[k];
        long total = 1;
        int[] pos = new int[k];
        fill(pos, -1);
        for (int i = 0; i < t.length; i++) {
            char c = t[i];
            c -= 'a';
            pos[c] = i;
            long current = total - qty[c];
            if (current < 0) {
                current += MOD;
            }
            qty[c] = total;
            total += current;
            if (total >= MOD) {
                total -= MOD;
            }
        }
        int[] order = order(pos);
        int at = 0;
        for (int i = 0; i < n; i++) {
            int c = order[at];
            long current = total - qty[c];
            if (current < 0) {
                current += MOD;
            }
            qty[c] = total;
            total += current;
            if (total >= MOD) {
                total -= MOD;
            }
            at++;
            if (at == k) {
                at = 0;
            }
        }
        out.printLine(total);
    }
}
