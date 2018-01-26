package on2017_07.on2017_07_08_IPSC_2017.J;





import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Random;

import static net.egork.misc.ArrayUtils.fill;

public class J {
    int[][] last = new int[1002][1002];
    Random random = new Random(239);

    {
        fill(last, -1);
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        if (n == 17) {
            out.printLine(6);
            out.printLine(0, 0);
            out.printLine(0, 2);
            out.printLine(1, 0);
            out.printLine(-1, 1);
            out.printLine(-1, 0);
            out.printLine(-1, 2);
            return;
        }
//for (int n = 1; n <= 1000; n++) {
    for (int i = 0; ; i++) {
        if (1 + i * (i + 1) / 2 >= n) {
            if (can(i, n)) {
                out.printLine(i);
                int[] kk = generate(i);
                int x = 0;
                while (i != 0) {
                    int k = kk[x++];//random.nextInt(20001) - 10000;
                    int[] bb = generate(last[i][n]);
                    for (int j = 0; j < last[i][n]; j++) {
                        out.printLine(k, bb[j]);//random.nextInt(20001));
                    }
                    int nn = n;
                    n -= last[i][n] + last[i][n] * (i - last[i][n]);
                    i -= last[i][nn];
                }
                return;
//break;
            }
//                    if (!can(i, n)) {
//                        System.err.println("SAD " + i + " " + n);
//                    }
//                    break;
        }
    }
}

    private int[] generate(int qty) {
        int[] result = new int[qty];
        for (int i = 0; i < qty; i++) {
            while (true) {
                result[i] = random.nextInt(20001) - 10000;
                boolean good = true;
                for (int j = 0; j < i; j++) {
                    if (result[i] == result[j]) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    break;
                }
            }
        }
        return result;
    }
//    }

    private boolean can(int l, int n) {
        if (n <= 0) {
            return false;
        }
        if (last[l][n] != -1) {
            if (last[l][n] == -2) {
                return false;
            }
            return true;
        }
        if (l == 0) {
            if (n == 1) {
                last[l][n] = 0;
                return true;
            }
            last[l][n] = -2;
            return false;
        }
        for (int i = 1; i <= l; i++) {
            if (can(l - i, n - i - i * (l - i))) {
                last[l][n] = i;
                return true;
            }
        }
        last[l][n] = -2;
        return false;
    }
}
