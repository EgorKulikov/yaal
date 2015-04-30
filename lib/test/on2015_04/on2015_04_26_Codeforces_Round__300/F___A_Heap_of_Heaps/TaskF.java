package on2015_04.on2015_04_26_Codeforces_Round__300.F___A_Heap_of_Heaps;



import net.egork.collections.FenwickTree;
import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] array = IOUtils.readIntArray(in, count);
        long[] answer = new long[count - 1];
        int[] order = ArrayUtils.createOrder(count);
        ArrayUtils.sort(order, (a, b) -> {
            if (array[a] != array[b]) {
                return array[b] - array[a];
            }
            return b - a;
        });
        FenwickTree tree = new FenwickTree(count);
        for (int i = 0; i < count; i++) {
            tree.add(i, 1);
        }
        for (int i : order) {
            tree.add(i, -1);
            for (int j = 1; i * j + 1 < count && j < count; j++) {
                answer[j - 1] += tree.get(i * j + 1, i * j + j);
            }
        }
        out.printLine(answer);
    }
}
