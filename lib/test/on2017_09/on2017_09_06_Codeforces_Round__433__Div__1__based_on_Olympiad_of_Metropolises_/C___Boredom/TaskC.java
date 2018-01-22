package on2017_09.on2017_09_06_Codeforces_Round__433__Div__1__based_on_Olympiad_of_Metropolises_.C___Boredom;


import net.egork.collections.FenwickTree;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] p = in.readIntArray(n);
        int[] l = new int[q];
        int[] d = new int[q];
        int[] r = new int[q];
        int[] u = new int[q];
        in.readIntArrays(l, d, r, u);
        decreaseByOne(l, d);
        int[] firstL = createArray(n + 1, -1);
        int[] nextL = new int[q];
        int[] firstR = createArray(n + 1, -1);
        int[] nextR = new int[q];
        for (int i = 0; i < q; i++) {
            nextL[i] = firstL[l[i]];
            firstL[l[i]] = i;
            nextR[i] = firstR[r[i]];
            firstR[r[i]] = i;
        }
        FenwickTree tree = new FenwickTree(n + 1);
        long[][] values = new long[q][9];
        for (int i = 0; i <= n; i++) {
            for (int j = firstL[i]; j != -1; j = nextL[j]) {
                values[j][0] = tree.get(1, d[j]);
                values[j][3] = tree.get(d[j] + 1, u[j]);
                values[j][6] = tree.get(u[j] + 1, n);
            }
            for (int j = firstR[i]; j != -1; j = nextR[j]) {
                values[j][1] = tree.get(1, d[j]) - values[j][0];
                values[j][4] = tree.get(d[j] + 1, u[j]) - values[j][3];
                values[j][7] = tree.get(u[j] + 1, n) - values[j][6];
            }
            if (i < n) {
                tree.add(p[i], 1);
            }
        }
        for (int i = 0; i < q; i++) {
            values[i][2] = tree.get(1, d[i]) - values[i][0] - values[i][1];
            values[i][5] = tree.get(d[i] + 1, u[i]) - values[i][3] - values[i][4];
            values[i][8] = tree.get(u[i] + 1, n) - values[i][6] - values[i][7];
            long aa = values[i][0];
            long bb = values[i][1];
            long cc = values[i][2];
            long dd = values[i][3];
            long ee = values[i][4];
            long ff = values[i][5];
            long gg = values[i][6];
            long hh = values[i][7];
            long ii = values[i][8];
            out.printLine(aa * (ee + ff + hh + ii) + bb * (dd + ee + ff + gg + hh + ii) + cc * (dd + ee + gg + hh) +
                    dd * (ee + ff + hh + ii) + ee * (ff + gg + hh + ii) + ff * (gg + hh) + ee * (ee - 1) / 2);
        }
    }
}
