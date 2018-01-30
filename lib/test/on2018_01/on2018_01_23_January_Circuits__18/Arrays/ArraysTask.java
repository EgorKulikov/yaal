package on2018_01.on2018_01_23_January_Circuits__18.Arrays;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.binarySearch;
import static net.egork.misc.ArrayUtils.sort;
import static net.egork.misc.ArrayUtils.unique;

public class ArraysTask {
    int[] delta;
    int[] interesting;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        in.readIntArrays(a, b, c);
        int[] pos = new int[5];
        pos[4] = k;
        interesting = new int[3 * n + 1];
        for (int i = 0; i < n; i++) {
            interesting[3 * i] = k - a[i] == k ? 0 : k - a[i];
            interesting[3 * i + 1] = k - b[i] == k ? 0 : k - b[i];
            interesting[3 * i + 2] = k - c[i] == k ? 0 : k - c[i];
        }
        sort(interesting);
        interesting = unique(interesting);
        delta = new int[4 * interesting.length];
        init(0, 0, interesting.length - 1);
        for (int i = 0; i < n; i++) {
            pos[1] = k - a[i];
            pos[2] = k - b[i];
            pos[3] = k - c[i];
            sort(pos);
            for (int j = 0; j < 4; j++) {
                if (pos[j] != pos[j + 1]) {
                    int sum = -3 * pos[j];
                    sum += (a[i] + pos[j]) % k;
                    sum += (b[i] + pos[j]) % k;
                    sum += (c[i] + pos[j]) % k;
                    int left = binarySearch(interesting, pos[j]);
                    int right = binarySearch(interesting, pos[j + 1]);
                    if (right < 0) {
                        right = interesting.length;
                    }
                    update(0, 0, interesting.length - 1, left, right - 1, sum);
                }
            }
        }
        out.printLine(answer(0, 0, interesting.length - 1));
    }

    private int answer(int root, int left, int right) {
        if (left == right) {
            return delta[root] + 3 * interesting[left];
        }
        pushDown(root);
        int middle = (left + right) >> 1;
        return min(answer(2 * root + 1, left, middle), answer(2 * root + 2, middle + 1, right));
    }

    private void update(int root, int left, int right, int from, int to, int value) {
        if (left > to || right < from) {
            return;
        }
        if (left >= from && right <= to) {
            delta[root] = Math.max(delta[root], value);
            return;
        }
        pushDown(root);
        int middle = (left + right) >> 1;
        update(2 * root + 1, left, middle, from, to, value);
        update(2 * root + 2, middle + 1, right, from, to, value);
    }

    private void pushDown(int root) {
        pushDown(root, 2 * root + 1);
        pushDown(root, 2 * root + 2);
        delta[root] = MIN_VALUE;
    }

    private void pushDown(int root, int child) {
        delta[child] = Math.max(delta[child], delta[root]);
    }

    private void init(int root, int left, int right) {
        if (left != right) {
            delta[root] = MIN_VALUE;
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle);
            init(2 * root + 2, middle + 1, right);
        } else {
            delta[root] = -3 * interesting[left];
        }
    }
}
