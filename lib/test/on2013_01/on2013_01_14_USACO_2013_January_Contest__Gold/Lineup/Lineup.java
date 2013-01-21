package on2013_01.on2013_01_14_USACO_2013_January_Contest__Gold.Lineup;



import net.egork.collections.map.Counter;
import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Lineup {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int canRemove = in.readInt();
        int[] ids = IOUtils.readIntArray(in, count);
        Counter<Integer> qty = new Counter<Integer>();
        int left = -1;
        final Map<Integer, Integer> last = new EHashMap<Integer, Integer>();
        NavigableSet<Integer> order = new TreeSet<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return last.get(o1) - last.get(o2);
            }
        });
        long answer = 0;
        for (int i = 0; i < count; i++) {
            qty.add(ids[i]);
            if (last.containsKey(ids[i]))
                order.remove(ids[i]);
            last.put(ids[i], i);
            order.add(ids[i]);
            if (order.size() > canRemove + 1) {
                int key = order.pollFirst();
                int upTo = last.remove(key);
                for (int j = left + 1; j <= upTo; j++)
                    qty.add(ids[j], -1);
                left = upTo;
            }
            answer = Math.max(answer, qty.get(ids[i]));
        }
        out.printLine(answer);
    }
}
