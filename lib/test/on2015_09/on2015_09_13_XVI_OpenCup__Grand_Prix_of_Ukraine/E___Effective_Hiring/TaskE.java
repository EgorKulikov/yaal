package on2015_09.on2015_09_13_XVI_OpenCup__Grand_Prix_of_Ukraine.E___Effective_Hiring;


import net.egork.generated.collections.comparator.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        final int[] a = new int[n];
        final int[] c = new int[n];
        int[] s = new int[n];
        IOUtils.readIntArrays(in, a, c, s);
        int[][] current = new int[k + 1][k + 1];
        ArrayUtils.fill(current, Integer.MAX_VALUE / 2);
        current[0][0] = 0;
        int[] order = ArrayUtils.createOrder(n);
        for (int i = 0; i < n; i++) {
            if (a[i] == 2) {
                a[i] = 1;
            } else if (a[i] == 3) {
                a[i] = 2;
            } else {
                a[i] = 3;
            }
        }
        ArrayUtils.sort(order, new IntComparator() {
            @Override
            public int compare(int x, int y) {
                return c[x] != c[y] ? c[x] - c[y] : a[x] - a[y];
            }
        });
        for (int i : order) {
            for (int j = k; j >= 0; j--) {
                for (int l = k - j; l >= 0; l--) {
                    if (a[i] >= 2 && l != 0) {
                        current[j + 1][l - 1] = Math.min(current[j + 1][l - 1], current[j][l] + s[i]);
                    }
                    if (a[i] <= 2 && j + l != k) {
                        current[j][l + 1] = Math.min(current[j][l + 1], current[j][l] + s[i]);
                    }
                }
            }
        }
        out.printLine(current[k][0]);
    }
}
