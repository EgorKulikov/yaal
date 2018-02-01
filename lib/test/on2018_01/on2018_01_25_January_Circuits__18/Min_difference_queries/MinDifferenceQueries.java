package on2018_01.on2018_01_25_January_Circuits__18.Min_difference_queries;



import net.egork.collections.intcollection.IntTreeSet;
import net.egork.collections.map.CPPMap;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Map;

import static java.util.Arrays.copyOfRange;
import static net.egork.misc.ArrayUtils.sort;
import static net.egork.misc.ArrayUtils.unique;

public class MinDifferenceQueries {
    Map<Integer, IntTreeSet> positions = new CPPMap<>(IntTreeSet::new);
    int[][] numbers;
    IntHashSet processed;
    IntHashSet values;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] a = in.readIntArray(n);
        for (int i = 0; i < n; i++) {
            positions.get(a[i]).add(i);
        }
        numbers = new int[4 * n][];
        init(0, 0, n - 1, a);
        int last = 0;
        for (int i = 0; i < q; i++) {
            int u = in.readInt();
            int v = in.readInt();
            int l = (u + last) % n;
            int r = (v + last) % n;
            processed = new IntHashSet();
            values = new IntHashSet();
            boolean equal = query(0, 0, n - 1, l, r);
            if (equal) {
                last = 0;
            } else if (values.size() == 1) {
                last = -1;
            } else {
                int[] vals = values.toArray();
                sort(vals);
                last = n;
                for (int j = 1; j < vals.length; j++) {
                    last = Math.min(last, vals[j] - vals[j - 1]);
                }
            }
            out.printLine(last);
        }
    }

    private boolean query(int root, int left, int right, int from, int to) {
        if (from > right || to < left) {
            return false;
        }
        if (left >= from && right <= to) {
            for (int i : numbers[root]) {
                if (processed.contains(i)) {
                    continue;
                }
                processed.add(i);
                int qty = positions.get(i).subSet(from, true, to, true).size();
                if (values.contains(qty)) {
                    return true;
                }
                values.add(qty);
            }
            return false;
        }
        int middle = (left + right) >> 1;
        return query(2 * root + 1, left, middle, from, to) || query(2 * root + 2, middle + 1, right, from, to);
    }

    private void init(int root, int left, int right, int[] a) {
        numbers[root] = copyOfRange(a, left, right + 1);
        sort(numbers[root]);
        numbers[root] = unique(numbers[root]);
        if (left != right) {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle, a);
            init(2 * root + 2, middle + 1, right, a);
        }
    }
}
