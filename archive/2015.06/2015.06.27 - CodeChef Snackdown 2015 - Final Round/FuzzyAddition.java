package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FuzzyAddition {
    private static final int SIZE = 1000000;

    double[] value;
    double[] delta;
    double[] stepValue;
    boolean[] clearDown;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        boolean reachedLimit = false;
        int start = 0;
        int current = 0;
        value = new double[4 * SIZE];
        delta = new double[4 * SIZE];
        stepValue = new double[4 * SIZE];
        clearDown = new boolean[4 * SIZE];
        init(0, 0, SIZE - 1);
        for (int i = 0; i < count; i++) {
            int number = in.readInt();
            if (!reachedLimit) {
                current += number;
                if (current >= SIZE) {
                    reachedLimit = true;
                }
            } else {
                double toDistribute;
                if (number > start) {
                    toDistribute = queryValue(0, 0, SIZE - 1, 0, start - 1) + queryValue(0, 0, SIZE - 1, SIZE - (number - start), SIZE - 1);
                    clear(0, 0, SIZE - 1, 0, start - 1);
                    clear(0, 0, SIZE - 1, SIZE - (number - start), SIZE - 1);
                } else {
                    toDistribute = queryValue(0, 0, SIZE - 1, start - number, start - 1);
                    clear(0, 0, SIZE - 1, start - number, start - 1);
                }
                add(0, 0, SIZE - 1, 0, SIZE - 1, toDistribute / SIZE);
                start -= number;
                if (start < 0) {
                    start += SIZE;
                }
            }
            if (!reachedLimit) {
                out.printLine(current);
            } else {
                double answer = queryStepValue(0, 0, SIZE - 1, start, SIZE - 1) +
                    queryStepValue(0, 0, SIZE - 1, 0, start - 1) + SIZE * queryValue(0, 0, SIZE - 1, 0, start - 1) - start;
                out.printLine(answer);
            }
        }
    }

    private void init(int root, int left, int right) {
        if (left == right) {
            value[root] = 1d / SIZE;
            stepValue[root] = (double)left / SIZE;
        } else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle);
            init(2 * root + 2, middle + 1, right);
            value[root] = value[2 * root + 1] + value[2 * root + 2];
            stepValue[root] = stepValue[2 * root + 1] + stepValue[2 * root + 2];
        }
    }

    double queryValue(int root, int left, int right, int from, int to) {
        if (left >= from && right <= to) {
            return value[root];
        }
        if (left > to || right < from) {
            return 0;
        }
        pushDown(root, left, right);
        int middle = (left + right) >> 1;
        return queryValue(2 * root + 1, left, middle, from, to) + queryValue(2 * root + 2, middle + 1, right, from, to);
    }

    double queryStepValue(int root, int left, int right, int from, int to) {
        if (left >= from && right <= to) {
            return stepValue[root];
        }
        if (left > to || right < from) {
            return 0;
        }
        pushDown(root, left, right);
        int middle = (left + right) >> 1;
        return queryStepValue(2 * root + 1, left, middle, from, to) + queryStepValue(2 * root + 2, middle + 1, right, from, to);
    }

    void clear(int root, int left, int right, int from, int to) {
        if (left >= from && right <= to) {
            value[root] = 0;
            stepValue[root] = 0;
            delta[root] = 0;
            clearDown[root] = true;
            return;
        }
        if (left > to || right < from) {
            return;
        }
        pushDown(root, left, right);
        int middle = (left + right) >> 1;
        clear(2 * root + 1, left, middle, from, to);
        clear(2 * root + 2, middle + 1, right, from, to);
        value[root] = value[2 * root + 1] + value[2 * root + 2];
        stepValue[root] = stepValue[2 * root + 1] + stepValue[2 * root + 2];
    }

    void add(int root, int left, int right, int from, int to, double by) {
        if (left >= from && right <= to) {
            value[root] += (right - left + 1d) * by;
            stepValue[root] += by * ((right - left + 1d) * (right + left) / 2d);
            delta[root] += by;
            return;
        }
        if (left > to || right < from) {
            return;
        }
        pushDown(root, left, right);
        int middle = (left + right) >> 1;
        add(2 * root + 1, left, middle, from, to, by);
        add(2 * root + 2, middle + 1, right, from, to, by);
        value[root] = value[2 * root + 1] + value[2 * root + 2];
        stepValue[root] = stepValue[2 * root + 1] + stepValue[2 * root + 2];
    }

    private void pushDown(int root, int left, int right) {
        int middle = (left + right) >> 1;
        pushDown(root, 2 * root + 1, left, middle);
        pushDown(root, 2 * root + 2, middle + 1, right);
        delta[root] = 0;
        clearDown[root] = false;
    }

    private void pushDown(int root, int child, int left, int right) {
        if (clearDown[root]) {
            value[child] = 0;
            stepValue[child] = 0;
            delta[child] = 0;
            clearDown[child] = true;
        }
        value[child] += delta[root] * (right - left + 1);
        delta[child] += delta[root];
        stepValue[child] += delta[root] * ((right - left + 1d) * (right + left) / 2d);
//        if (left != right && clearDown[child]) {
//            value[2 * child + 1] = 0;
//            delta[2 * child + 1] = 0;
//            stepValue[2 * child + 1] = 0;
//            value[2 * child + 2] = 0;
//            delta[2 * child + 2] = 0;
//            stepValue[2 * child + 2] = 0;
//            clearDown[2 * child + 1] = true;
//            clearDown[2 * child + 2] = true;
//            clearDown[child] = false;
//        }
    }
}
