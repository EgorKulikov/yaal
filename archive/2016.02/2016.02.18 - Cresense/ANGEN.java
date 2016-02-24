package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class ANGEN {
    public static final long MASK = ((1L << 32) - 1);
    long[] sum;
    long[] max;
    long[] sMax;
    long[] min;
    long[] sMin;
    int n;

    void init(int[] values) {
        n = values.length;
        sum = new long[4 * n];
        max = new long[4 * n];
        sMax = new long[4 * n];
        min = new long[4 * n];
        sMin = new long[4 * n];
        init(0, 0, n - 1, values);
    }
    
    void init(int root, int left, int right, int[] values) {
        if (left == right) {
            sum[root] = values[left];
            max[root] = values[left];
            min[root] = values[left];
            sMin[root] = Integer.MAX_VALUE;
        } else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle, values);
            init(2 * root + 2, middle + 1, right, values);
            join(root);
        }
    }

    long sum(int left, int right) {
        return sum(0, 0, n - 1, left, right);
    }

    long max(int left, int right) {
        return max(0, 0, n - 1, left, right) & MASK;
    }

    long secondMax(int left, int right) {
        return max(0, 0, n - 1, left, right) >> 32;
    }

    private long max(int root, int from, int to, int left, int right) {
        if (from > right || to < left) {
            return 0;
        }
        if (from >= left && to <= right) {
            return max[root] + (sMax[root] << 32);
        }
        int middle = (from + to) >> 1;
        long fromLeft = max(2 * root + 1, from, middle, left, right);
        long fromRight = max(2 * root + 2, middle + 1, to, left, right);
        long lMax = fromLeft & MASK;
        long lSMax = fromLeft >> 32;
        long rMax = fromRight & MASK;
        long rSMax = fromRight >> 32;
        if (lMax > rMax) {
            return lMax + (Math.max(rMax, lSMax) << 32);
        } else {
            return rMax + (Math.max(lMax, rSMax) << 32);
        }
    }

    long min(int left, int right) {
        return min(0, 0, n - 1, left, right) & MASK;
    }

    long secondMin(int left, int right) {
        return min(0, 0, n - 1, left, right) >> 32;
    }

    private long min(int root, int from, int to, int left, int right) {
        if (from > right || to < left) {
            return Integer.MAX_VALUE + (((long)Integer.MAX_VALUE) << 32);
        }
        if (from >= left && to <= right) {
            return min[root] + (sMin[root] << 32);
        }
        int middle = (from + to) >> 1;
        long fromLeft = min(2 * root + 1, from, middle, left, right);
        long fromRight = min(2 * root + 2, middle + 1, to, left, right);
        long lMin = fromLeft & MASK;
        long lSMin = fromLeft >> 32;
        long rMin = fromRight & MASK;
        long rSMin = fromRight >> 32;
        if (lMin < rMin) {
            return lMin + (Math.min(rMin, lSMin) << 32);
        } else {
            return rMin + (Math.min(lMin, rSMin) << 32);
        }
    }

    private long sum(int root, int from, int to, int left, int right) {
        if (from > right || to < left) {
            return 0;
        }
        if (from >= left && to <= right) {
            return sum[root];
        }
        int middle = (from + to) >> 1;
        return sum(2 * root + 1, from, middle, left, right) + sum(2 * root + 2, middle + 1, to, left, right);
    }

    private void join(int root) {
        sum[root] = sum[2 * root + 1] + sum[2 * root + 2];
        if (max[2 * root + 1] > max[2 * root + 2]) {
            max[root] = max[2 * root + 1];
            sMax[root] = Math.max(sMax[2 * root + 1], max[2 * root + 2]);
        } else {
            max[root] = max[2 * root + 2];
            sMax[root] = Math.max(sMax[2 * root + 2], max[2 * root + 1]);
        }
        if (min[2 * root + 1] < min[2 * root + 2]) {
            min[root] = min[2 * root + 1];
            sMin[root] = Math.min(sMin[2 * root + 1], min[2 * root + 2]);
        } else {
            min[root] = min[2 * root + 2];
            sMin[root] = Math.min(sMin[2 * root + 2], min[2 * root + 1]);
        }
    }

    void update(int position, int value) {
        update(0, 0, n - 1, position, value);
    }

    private void update(int root, int left, int right, int position, int value) {
        if (position < left || position > right) {
            return;
        }
        if (left == right) {
            sum[root] = value;
            min[root] = value;
            max[root] = value;
        } else {
            int middle = (left + right) >> 1;
            update(2 * root + 1, left, middle, position, value);
            update(2 * root + 2, middle + 1, right, position, value);
            join(root);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] values = readIntArray(in, n);
        init(values);
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            char type = in.readCharacter();
            switch (type) {
            case 'U':
                int position = in.readInt() - 1;
                int value = in.readInt();
                update(position, value);
                break;
            case 'A':
                int left = in.readInt() - 1;
                int right = in.readInt() - 1;
                out.printLine(sum(left, right));
                break;
            case 'M':
                left = in.readInt() - 1;
                right = in.readInt() - 1;
                out.printLine(max(left, right));
                break;
            case 'm':
                left = in.readInt() - 1;
                right = in.readInt() - 1;
                out.printLine(min(left, right));
                break;
            case 'S':
                left = in.readInt() - 1;
                right = in.readInt() - 1;
                if (left == right) {
                    out.printLine("NA");
                } else {
                    out.printLine(secondMax(left, right));
                }
                break;
            case 's':
                left = in.readInt() - 1;
                right = in.readInt() - 1;
                if (left == right) {
                    out.printLine("NA");
                } else {
                    out.printLine(secondMin(left, right));
                }
                break;
            default:
                out.printLine("!!!");
            }
        }
    }
}
