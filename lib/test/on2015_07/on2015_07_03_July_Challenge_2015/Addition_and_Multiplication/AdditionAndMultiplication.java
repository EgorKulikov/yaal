package on2015_07.on2015_07_03_July_Challenge_2015.Addition_and_Multiplication;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class AdditionAndMultiplication {
    private static final long MOD = (long) (1e9 + 7);

    int updateType;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int count = in.readInt();
        int[] array = IOUtils.readIntArray(in, size);
        IntervalTree tree = new IntervalTree(size, true) {
            long[] value;
            long[] addDelta;
            long[] multDelta;
            long[] setDelta;

            @Override
            protected void initData(int size, int nodeCount) {
                value = new long[nodeCount];
                addDelta = new long[nodeCount];
                multDelta = new long[nodeCount];
                setDelta = new long[nodeCount];
                Arrays.fill(multDelta, 1);
            }

            @Override
            protected void initAfter(int root, int left, int right, int middle) {
                value[root] = (value[2 * root + 1] + value[2 * root + 2]) % MOD;
            }

            @Override
            protected void initBefore(int root, int left, int right, int middle) {
            }

            @Override
            protected void initLeaf(int root, int index) {
                value[root] = array[index];
            }

            @Override
            protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {
                initAfter(root, left, right, middle);
            }

            @Override
            protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {
                pushDownFull(root, left, right, middle);
            }

            private void pushDownFull(int root, int left, int right, int middle) {
                pushDown(root, 2 * root + 1, left, middle);
                pushDown(root, 2 * root + 2, middle + 1, right);
                setDelta[root] = 0;
                addDelta[root] = 0;
                multDelta[root] = 1;
            }

            private void pushDown(int root, int childRoot, int left, int right) {
                if (setDelta[root] != 0) {
                    addDelta[childRoot] = 0;
                    multDelta[childRoot] = 1;
                    setDelta[childRoot] = setDelta[root];
                    value[childRoot] = setDelta[root] * (right - left + 1) % MOD;
                }
                addDelta[childRoot] *= multDelta[root];
                addDelta[childRoot] %= MOD;
                multDelta[childRoot] *= multDelta[root];
                multDelta[childRoot] %= MOD;
                value[childRoot] *= multDelta[root];
                value[childRoot] %= MOD;
                addDelta[childRoot] += addDelta[root];
                addDelta[childRoot] %= MOD;
                value[childRoot] += addDelta[root] * (right - left + 1);
                value[childRoot] %= MOD;
            }

            @Override
            protected void updateFull(int root, int left, int right, int from, int to, long delta) {
                if (updateType == 1) {
                    addDelta[root] += delta;
                    addDelta[root] %= MOD;
                    value[root] += delta * (right - left + 1);
                    value[root] %= MOD;
                } else if (updateType == 2) {
                    addDelta[root] *= delta;
                    addDelta[root] %= MOD;
                    multDelta[root] *= delta;
                    multDelta[root] %= MOD;
                    value[root] *= delta;
                    value[root] %= MOD;
                } else if (updateType == 3) {
                    addDelta[root] = 0;
                    multDelta[root] = 1;
                    setDelta[root] = delta;
                    value[root] = delta * (right - left + 1) % MOD;
                }
            }

            @Override
            protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
                return (leftResult + rightResult) % MOD;
            }

            @Override
            protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {
                pushDownFull(root, left, right, middle);
            }

            @Override
            protected long queryFull(int root, int left, int right, int from, int to) {
                return value[root];
            }

            @Override
            protected long emptySegmentResult() {
                return 0;
            }
        };
        for (int i = 0; i < count; i++) {
            int type = in.readInt();
            int from = in.readInt() - 1;
            int to = in.readInt() - 1;
            if (type <= 3) {
                int value = in.readInt();
                updateType = type;
                tree.update(from, to, value);
            } else {
                out.printLine(tree.query(from, to));
            }
        }
    }
}
