package on2018_01.on2018_01_02_HourRank_25.The_Strange_Function;


import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.*;
import static net.egork.numbers.IntegerUtils.gcd;

public class TheStrangeFunction {
    int n;
    int[] a;
    int[] toRight;
    int[] toLeft;
    LTree lTree;
    RTree rTree;

    class LTree {
        long[] sum;
        long[] maxTail;

        long[] resSum;
        long[] resTail;

        LTree() {
            sum = new long[4 * a.length];
            maxTail = new long[4 * a.length];
            resSum = new long[4 * a.length];
            resTail = new long[4 * a.length];
            init(0, 0, a.length - 1);
        }

        private void init(int root, int left, int right) {
            if (left == right) {
                sum[root] = a[left];
                maxTail[root] = max(sum[root], 0);
            } else {
                int middle = (left + right) >> 1;
                init(2 * root + 1, left, middle);
                init(2 * root + 2, middle + 1, right);
                sum[root] = sum[2 * root + 1] + sum[2 * root + 2];
                maxTail[root] = max(maxTail[2 * root + 2], sum[2 * root + 2] + maxTail[2 * root + 1]);
            }
        }

        long query(int from, int to) {
            if (from > to) {
                return 0;
            }
            query(0, 0, a.length - 1, from, to);
            return resTail[0];
        }

        private void query(int root, int left, int right, int from, int to) {
            if (left > to || right < from) {
                resSum[root] = 0;
                resTail[root] = 0;
                return;
            }
            if (left >= from && right <= to) {
                resSum[root] = sum[root];
                resTail[root] = maxTail[root];
                return;
            }
            int middle = (left + right) >> 1;
            query(2 * root + 1, left, middle, from, to);
            query(2 * root + 2, middle + 1, right, from, to);
            resSum[root] = resSum[2 * root + 1] + resSum[2 * root + 2];
            resTail[root] = max(resTail[2 * root + 2], resTail[2 * root + 1] + resSum[2 * root + 2]);
        }
    }

    class RTree {
        long[] sum;
        long[] maxHead;

        long[] resSum;
        long[] resHead;

        RTree() {
            sum = new long[4 * a.length];
            maxHead = new long[4 * a.length];
            resSum = new long[4 * a.length];
            resHead = new long[4 * a.length];
            init(0, 0, a.length - 1);
        }

        private void init(int root, int left, int right) {
            if (left == right) {
                sum[root] = a[left];
                maxHead[root] = max(sum[root], 0);
            } else {
                int middle = (left + right) >> 1;
                init(2 * root + 1, left, middle);
                init(2 * root + 2, middle + 1, right);
                sum[root] = sum[2 * root + 1] + sum[2 * root + 2];
                maxHead[root] = max(maxHead[2 * root + 1], sum[2 * root + 1] + maxHead[2 * root + 2]);
            }
        }

        long query(int from, int to) {
            if (from > to) {
                return 0;
            }
            query(0, 0, a.length - 1, from, to);
            return resHead[0];
        }

        private void query(int root, int left, int right, int from, int to) {
            if (left > to || right < from) {
                resSum[root] = 0;
                resHead[root] = 0;
                return;
            }
            if (left >= from && right <= to) {
                resSum[root] = sum[root];
                resHead[root] = maxHead[root];
                return;
            }
            int middle = (left + right) >> 1;
            query(2 * root + 1, left, middle, from, to);
            query(2 * root + 2, middle + 1, right, from, to);
            resSum[root] = resSum[2 * root + 1] + resSum[2 * root + 2];
            resHead[root] = max(resHead[2 * root + 1], resHead[2 * root + 2] + resSum[2 * root + 1]);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        a = in.readIntArray(n);
        lTree = new LTree();
        rTree = new RTree();
        toRight = new int[n];
        int[] stack = new int[n];
        int size = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (size > 0 && a[i] >= a[stack[size - 1]]) {
                size--;
            }
            toRight[i] = size == 0 ? n - 1 : stack[size - 1] - 1;
            stack[size++] = i;
        }
        toLeft = new int[n];
        size = 0;
        for (int i = 0; i < n; i++) {
            while (size > 0 && a[i] >= a[stack[size - 1]]) {
                size--;
            }
            toLeft[i] = size == 0 ? 0 : stack[size - 1] + 1;
            stack[size++] = i;
        }
        long[] gcds = new long[n + 1];
        int[] at = new int[n + 1];
        size = 0;
        long answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < size; j++) {
                if (gcd(gcds[j], a[i]) != gcds[j]) {
                    answer = max(answer, solve(at[j], i - 1) * gcds[j]);
                    gcds[j] = gcd(gcds[j], a[i]);
                }
            }
            gcds[size] = abs(a[i]);
            at[size++] = i;
            int copyTo = 0;
            for (int j = 0; j < size; j++) {
                if (j == 0 || gcds[j] != gcds[j - 1]) {
                    gcds[copyTo] = gcds[j];
                    at[copyTo++] = at[j];
                }
            }
            size = copyTo;
        }
        for (int i = 0; i < size; i++) {
            answer = max(answer, solve(at[i], n - 1) * gcds[i]);
            answer = answer;
        }
        out.printLine(answer);
    }

    private long solve(int l, int r) {
        long answer = 0;
        for (int i = l; i <= r; i++) {
            int tl = max(toLeft[i], l);
            int tr = min(toRight[i], r);
            answer = Math.max(answer, lTree.query(tl, i - 1) + rTree.query(i + 1, tr));
        }
        return answer;
    }
}
