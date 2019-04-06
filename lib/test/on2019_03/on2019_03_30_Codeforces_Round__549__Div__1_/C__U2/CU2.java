package on2019_03.on2019_03_30_Codeforces_Round__549__Div__1_.C__U2;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.*;

public class CU2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        in.readIntArrays(x, y);
        int[] order = createOrder(n);
        sort(order, (a, b) -> {
            if (x[a] != x[b]) {
                return x[a] - x[b];
            }
            return y[a] - y[b];
        });
        int start = order[0];
        int[] next = createArray(n, -1);
        int[] last = createArray(n, -1);
        for (int i = 1; i < n; i++) {
            next[order[i - 1]] = order[i];
            last[order[i]] = order[i - 1];
        }
        for (int i = start; i != -1; i = next[i]) {
            if (next[i] != -1 && x[i] == x[next[i]]) {
                if (i == start) {
                    start = next[i];
                    last[start] = -1;
                } else {
                    int nx = next[i];
                    int ls = last[i];
                    next[ls] = nx;
                    last[nx] = ls;
                }
            }
        }
        int current = start;
        while (true) {
            if (next[current] == -1 || next[next[current]] == -1) {
                break;
            }
            int a = current;
            int b = next[current];
            int c = next[b];
            long x0 = x[a];
            long x1 = x[c];
            long y0 = y[a];
            long y1 = y[c];
            long bbNum = y0 - y1 + x1 * x1 - x0 * x0;
            long den = x0 - x1;
            if (den < 0) {
                den = -den;
                bbNum = -bbNum;
            }
            long ccNum = y0 * den - x0 * x0 * den - bbNum * x0;
            long xx = x[b];
            long yy = y[b];
            long valNum = xx * xx * den + bbNum * xx + ccNum;
            if (valNum >= yy * den) {
                next[a] = c;
                last[c] = a;
                if (last[a] != -1) {
                    current = last[a];
                }
            } else {
                current = next[current];
            }
        }
        int answer = 0;
        while (next[start] != -1) {
            start = next[start];
            answer++;
        }
        out.printLine(answer);
    }
}
