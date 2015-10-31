package on2015_07.on2015_07_18_July_Clash__15.SomethingGenuine;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.collections.intervaltree.IntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SomethingGenuine {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] array = IOUtils.readIntArray(in, count);
        IntHashMap position = new IntHashMap();
        IntervalTree tree = new IntervalTree(count) {
            long[] sum;
            long[] sumSq;
            long[] delta;

            @Override
            protected void initData(int size, int nodeCount) {
                sum = new long[nodeCount];
                sumSq = new long[nodeCount];
                delta = new long[nodeCount];
            }

            @Override
            protected void initAfter(int root, int left, int right, int middle) {

            }

            @Override
            protected void initBefore(int root, int left, int right, int middle) {

            }

            @Override
            protected void initLeaf(int root, int index) {

            }

            @Override
            protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {
                sumSq[root] = sumSq[2 * root + 1] + sumSq[2 * root + 2];
                sum[root] = sum[2 * root + 1] + sum[2 * root + 2];
            }

            @Override
            protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {
                pushDown(root, left, middle, right);
            }

            @Override
            protected void updateFull(int root, int left, int right, int from, int to, long delta) {
                apply(root, delta, left, right);
            }

            private void apply(int root, long delta, int left, int right) {
                long qty = right - left + 1;
                sumSq[root] += (2 * sum[root] + delta * qty) % MOD * delta;
                sum[root] += delta * qty;
                this.delta[root] += delta;
                sumSq[root] %= MOD;
                sum[root] %= MOD;
                this.delta[root] %= MOD;
            }

            @Override
            protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
                return leftResult + rightResult;
            }

            @Override
            protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {
                pushDown(root, left, middle, right);
            }

            private void pushDown(int root, int left, int middle, int right) {
                apply(2 * root + 1, delta[root], left, middle);
                apply(2 * root + 2, delta[root], middle + 1, right);
                delta[root] = 0;
            }

            @Override
            protected long queryFull(int root, int left, int right, int from, int to) {
                return sumSq[root];
            }

            @Override
            protected long emptySegmentResult() {
                return 0;
            }
        };
        long answer = 0;
        for (int i = 0; i < array.length; i++) {
            if (position.contains(array[i])) {
                int at = position.get(array[i]);
                tree.update(at + 1, i, 1);
            } else {
                tree.update(0, i, 1);
            }
            position.put(array[i], i);
            answer += tree.query(0, i);
            answer %= MOD;
        }
        out.printLine(answer);
    }
}
