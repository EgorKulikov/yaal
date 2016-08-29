package on2016_05.on2016_05_30_Single_Round_Match_691.Sunnygraphs;



import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;

public class Sunnygraphs {
    public long count(int[] a) {
        int n = a.length;
        IntSet fromZero = get(a, 0, new IntHashSet());
        IntSet fromOne = get(a, 1, new IntHashSet());
        int common = 0;
        for (int i = 0; i < n; i++) {
            if (fromZero.contains(i) && fromOne.contains(i)) {
                common++;
            }
        }
        int onlyZero = fromZero.size() - common;
        int onlyOne = fromOne.size() - common;
        long answer;
        if (common != 0) {
            answer = (1L << common) + ((1L << onlyZero) - 1) * ((1L << common) - 1) +
                    ((1L << onlyOne) - 1) * ((1L << common) - 1) + ((1L << onlyZero) - 1) * ((1L << onlyOne) - 1) *
                    (1L << common);
        } else {
            answer = ((1L << onlyZero) - 1) * ((1L << onlyOne) - 1);
        }
        answer <<= n - common - onlyZero - onlyOne;
        return answer;
    }

    private IntSet get(int[] a, int vertex, IntHashSet set) {
        if (set.contains(vertex)) {
            return set;
        }
        set.add(vertex);
        return get(a, a[vertex], set);
    }
}
