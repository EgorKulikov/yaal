package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    long[] qty;
    int size;
    int start;
    int end;
    int remd;
    int remaining;
    OutputWriter out;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        this.out = out;
        size = in.readInt();
        int count = in.readInt();
        qty = new long[Integer.highestOneBit(size + 1) * 4 + 100];
        init(0, 0, size);
        remaining = size;
        for (int i = 0; i < count; i++) {
            String type = in.readString();
            remd = -1;
            if ("ADD".equals(type)) {
                int skip = in.readInt();
                int add = in.readInt();
                add(0, 0, size, skip, add);
            } else {
                int skip = in.readInt();
                int remove = in.readInt();
                remove(0, 0, size, skip, remove);
            }
//            for (int j = 0; j <= remd; j++) {
//                if (j != 0) {
//                    out.print(", ");
//                }
//                out.print(start[j] + "-" + end[j]);
//                remaining -= end[j] - start[j] + 1;
//            }
            if (remd != -1) {
                out.print(start + "-" + end + "; ");
            }
            out.print(qty[0] - remaining);
            out.print("; ");
            long xor = 0;
            for (int j = 1; j <= size; j *= 2) {
                if (query(0, 0, size, j, j) != 0) {
                    xor ^= query(0, 0, size, 0, j - 1) + 1;
                }
            }
            out.printLine(xor);
        }
    }

    void init(int root, int left, int right) {
        if (left == right) {
            qty[root] = left == 0 ? 0 : 1;
        } else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle);
            init(2 * root + 2, middle + 1, right);
            qty[root] = qty[2 * root + 1] + qty[2 * root + 2];
        }
    }

    void add(int root, int left, int right, long after, long toAdd) {
        if (left == right) {
            qty[root] += toAdd;
            return;
        }
        if (qty[2 * root + 1] >= after) {
            add(2 * root + 1, left, (left + right) >> 1, after, toAdd);
        } else {
            add(2 * root + 2, ((left + right) >> 1) + 1, right, after - qty[2 * root + 1], toAdd);
        }
        qty[root] = qty[2 * root + 1] + qty[2 * root + 2];
    }

    long query(int root, int left, int right, int from, int to) {
        if (left > to || right < from) {
            return 0;
        }
        if (left >= from && right <= to) {
            return qty[root];
        }
        int middle = (left + right) >> 1;
        return query(2 * root + 1, left, middle, from, to) + query(2 * root + 2, middle + 1, right, from, to);
    }

    long remove(int root, int left, int right, long after, long toRemove) {
        if (qty[root] == 0) {
            return toRemove;
        }
        if (left == right) {
            long curRemove = Math.min(qty[root] - after, toRemove);
            qty[root] -= curRemove;
            if (after == 0 && left != 0) {
                remaining--;
                if (remd == -1) {
                    remd = 0;
                    start = left;
                    end = left;
                } else {
                    if (end == left - 1) {
                        end = left;
                    } else {
                        out.print(start + "-" + end);
                        out.print(", ");
                        start = end = left;
                        remd++;
                    }
                }
            }
            if (after == 0 && qty[root] > 0 && left != 0) {
                add(0, 0, size, query(0, 0, size, 0, left - 1), qty[root]);
                qty[root] = 0;
            }
            return toRemove - curRemove;
        }
        int middle = (left + right) >> 1;
        if (qty[2 * root + 1] >= after) {
            toRemove = remove(2 * root + 1, left, middle, after, toRemove);
            if (toRemove > 0) {
                toRemove = remove(2 * root + 2, middle + 1, right, 0, toRemove);
            }
        } else {
            toRemove = remove(2 * root + 2, middle + 1, right, after - qty[2 * root + 1], toRemove);
        }
        qty[root] = qty[2 * root + 1] + qty[2 * root + 2];
        return toRemove;
    }
}
