package on2015_08.on2015_08_07_August_Challenge_2015.DevuAndChuru;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DevuAndChuru {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = IOUtils.readIntArray(in, n);
        char[] c = new char[m];
        int[] k = new int[m];
        char[] x = new char[m];
        for (int i = 0; i < m; i++) {
            c[i] = in.readCharacter();
            k[i] = in.readInt();
            x[i] = in.readCharacter();
        }
        int total = ArrayUtils.compress(a, k).length;
        int[] order = ArrayUtils.order(a);
        int[] left = ArrayUtils.createArray(n, -1);
        int[] right = ArrayUtils.createArray(n, -1);
        long[] base = new long[total];
        for (int i : order) {
            long toLeft = 1;
            left[i] = right[i] = i;
            if (i < n - 1 && right[i + 1] != -1) {
                toLeft = right[i + 1] - i + 1;
                right[i] = right[i + 1];
                left[right[i + 1]] = i;
            }
            long toRight = 1;
            if (i > 0 && left[i - 1] != -1) {
                toRight = i - left[i - 1] + 1;
                left[right[i]] = left[i - 1];
                right[left[i - 1]] = right[i];
            }
            base[a[i]] += toLeft * toRight;
        }
        long[] sums = new long[total + 1];
        for (int i = 0; i < total; i++) {
            sums[i + 1] = sums[i] + base[i];
        }
        for (int i = 0; i < m; i++) {
            long qty;
            if (c[i] == '<') {
                qty = sums[k[i]];
            } else if (c[i] == '>') {
                qty = sums[total] - sums[k[i] + 1];
            } else {
                qty = base[k[i]];
            }
            if ((qty & 1) == 1) {
                out.print(x[i]);
            } else {
                out.print((char)('C' + 'D' - x[i]));
            }
        }
        out.printLine();
    }
}
