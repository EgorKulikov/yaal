package on2018_06.on2018_06_17_CodeChef___June_Cook_Off_2018_Division_1.Sonya_and_Segments;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.Combinations;

import static net.egork.misc.ArrayUtils.order;
import static net.egork.misc.ArrayUtils.orderBy;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.trueMod;

public class SonyaAndSegments {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] l = new int[n];
        int[] r = new int[n];
        in.readIntArrays(l, r);
        Combinations c = new Combinations(n + 1, MOD7);
        orderBy(l, r);
        int[] order = order(r);
        int at = 0;
        int qty = 0;
        long answer = c.c(n, k);
        for (int i = 0; i < n; i++) {
            while (r[order[at]] < l[i]) {
                at++;
                qty--;
            }
            answer -= c.c(qty, k - 1);
            qty++;
        }
        answer = trueMod(answer, MOD7);
        out.printLine(answer);
    }
}
