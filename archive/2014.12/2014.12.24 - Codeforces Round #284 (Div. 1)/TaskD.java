package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    int[] period;
    int[][] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        period = IOUtils.readIntArray(in, count);
        answer = new int[60][count << 2];
        init(0, 0, count);
        int queryCount = in.readInt();
        for (int i = 0; i < queryCount; i++) {
            char type = in.readCharacter();
            if (type == 'C') {
                int at = in.readInt() - 1;
                period[at] = in.readInt();
                update(0, 0, count, at);
            } else {
                int from = in.readInt() - 1;
                int to = in.readInt() - 1;
                out.printLine(calculate(0, 0, count, from, to, 0));
            }
        }
    }

    private int calculate(int root, int from, int to, int left, int right, int shift) {
        if (from >= right || to <= left) {
            return -1;
        }
        if (from >= left && to <= right) {
            return shift + answer[shift % 60][root];
        }
        int middle = (from + to) >> 1;
        if (right <= middle) {
            return calculate(2 * root + 1, from, middle, left, right, shift);
        }
        if (left >= middle) {
            return calculate(2 * root + 2, middle, to, left, right, shift);
        }
        int result = calculate(2 * root + 1, from, middle, left, right, shift);
        return calculate(2 * root + 2, middle, to, left, right, result);
    }

    private void update(int root, int from, int to, int at) {
        if (from > at || to <= at) {
            return;
        }
        if (from + 1 == to) {
            for (int i = 0; i < 60; i++) {
                if (i % period[from] == 0) {
                    answer[i][root] = 2;
                } else {
                    answer[i][root] = 1;
                }
            }
        } else {
            int middle = (from + to) >> 1;
            update(2 * root + 1, from, middle, at);
            update(2 * root + 2, middle, to, at);
            for (int i = 0; i < 60; i++) {
                answer[i][root] = answer[i][2 * root + 1] + answer[(i + answer[i][2 * root + 1]) % 60][2 * root + 2];
            }
        }
    }

    private void init(int root, int from, int to) {
        if (from + 1 == to) {
            for (int i = 0; i < 60; i++) {
                if (i % period[from] == 0) {
                    answer[i][root] = 2;
                } else {
                    answer[i][root] = 1;
                }
            }
        } else {
            int middle = (from + to) >> 1;
            init(2 * root + 1, from, middle);
            init(2 * root + 2, middle, to);
            for (int i = 0; i < 60; i++) {
                answer[i][root] = answer[i][2 * root + 1] + answer[(i + answer[i][2 * root + 1]) % 60][2 * root + 2];
            }
        }
    }
}
