package on2018_01.on2018_01_29_Codeforces_Round__459__Div__1_.C___Pollywog;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.bitCount;
import static java.lang.Long.MAX_VALUE;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.ArrayUtils.orderBy;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.readInt();
        int k = in.readInt();
        int n = in.readInt();
        int q = in.readInt();
        int[] c = in.readIntArray(k);
        int[] p = new int[q];
        int[] w = new int[q];
        in.readIntArrays(p, w);
        orderBy(p, w);
        decreaseByOne(p);
        int[] id = createArray(1 << k, -1);
        int[] byId = new int[1 << k];
        int size = 0;
        for (int i = 0; i < (1 << k); i++) {
            if (bitCount(i) == x) {
                id[i] = size;
                byId[size++] = i;
            }
        }
        long[][] baseMat;
        long[][][] specMat;
        baseMat = new long[size][size];
        fill(baseMat, MAX_VALUE);
        specMat = new long[q][size][size];
        fill(specMat, MAX_VALUE);
        for (int i = 0; i < size; i++) {
            int ii = byId[i];
            if ((ii & 1) == 0) {
                baseMat[id[ii]][id[ii >> 1]] = 0;
                for (int j = 0; j < q; j++) {
                    specMat[j][id[ii]][id[ii >> 1]] = 0;
                }
            } else {
                int ni = ii >> 1;
                for (int j = 0; j < k; j++) {
                    if ((ni >> j & 1) == 0) {
                        baseMat[id[ii]][id[ni + (1 << j)]] = c[j];
                        for (int l = 0; l < q; l++) {
                            specMat[l][id[ii]][id[ni + (1 << j)]] = c[j] + w[l];
                        }
                    }
                }
            }
        }
        long[][] current = new long[size][size];
        makeOne(current);
        long[][] temp = new long[size][size];
        long[][] temp1 = new long[size][size];
        long[][] temp2 = new long[size][size];
        int start = 0;
        for (int i = 0; i <= q; i++) {
            int stop = n - x;
            if (i < q) {
                stop = Math.min(stop, p[i]);
            }
            power(baseMat, stop - start, temp1, temp2);
            multiply(temp, current, temp1);
            if (stop == n - x) {
                current = temp;
                break;
            } else {
                multiply(current, temp, specMat[i]);
                start = stop + 1;
            }
        }
        long answer = current[id[(1 << x) - 1]][id[(1 << x) - 1]];
        for (int i = 0; i < q; i++) {
            if (p[i] >= n - x) {
                answer += w[i];
            }
        }
        out.printLine(answer);
    }

    private void power(long[][] base, int exponent, long[][] result, long[][] temp) {
        if (exponent == 0) {
            makeOne(result);
            return;
        }
        if ((exponent & 1) == 0) {
            power(base, exponent >> 1, temp, result);
            multiply(result, temp, temp);
        } else {
            power(base, exponent - 1, temp, result);
            multiply(result, temp, base);
        }
    }

    private void multiply(long[][] c, long[][] a, long[][] b) {
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                c[i][j] = MAX_VALUE;
                for (int k = 0; k < c.length; k++) {
                    if (a[i][k] != MAX_VALUE && b[k][j] != MAX_VALUE) {
                        c[i][j] = Math.min(c[i][j], a[i][k] + b[k][j]);
                    }
                }
            }
        }
    }

    private void makeOne(long[][] current) {
        fill(current, MAX_VALUE);
        for (int i = 0; i < current.length; i++) {
            current[i][i] = 0;
        }
    }
}
