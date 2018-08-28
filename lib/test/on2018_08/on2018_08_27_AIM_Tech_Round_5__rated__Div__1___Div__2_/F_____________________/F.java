package on2018_08.on2018_08_27_AIM_Tech_Round_5__rated__Div__1___Div__2_.F_____________________;



import net.egork.collections.map.CPPMap;
import net.egork.collections.map.Counter;
import net.egork.collections.set.EHashSet;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Map;
import java.util.Set;

import static java.lang.Math.abs;
import static net.egork.numbers.IntegerUtils.gcd;

public class F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int q = in.readInt();
        Counter<IntIntPair> delta = new Counter<>();
        Set<IntIntPair> set = new EHashSet<>();
        Map<Long, Set<IntIntPair>> map = new CPPMap<>(EHashSet::new);
        for (int i = 0; i < q; i++) {
            int t = in.readInt();
            int x = in.readInt();
            int y = in.readInt();
            int g = gcd(x, y);
            int gx = x / g;
            int gy = y / g;
            if (t == 3) {
                out.printLine(set.size() - delta.get(new IntIntPair(gx, gy)));
            } else {
                IntIntPair p = new IntIntPair(x, y);
                long s = (long)x * x + (long)y * y;
                if (t == 2) {
                    map.get(s).remove(p);
                    set.remove(p);
                }
                int by = 3 - 2 * t;
                for (IntIntPair r : map.get(s)) {
                    int dx = abs(r.second - p.second);
                    int dy = abs(r.first - p.first);
                    int gg = gcd(dx, dy);
                    dx /= gg;
                    dy /= gg;
                    delta.add(new IntIntPair(dx, dy), 2 * by);
                }
                delta.add(new IntIntPair(gx, gy), by);
                if (t == 1) {
                    map.get(s).add(p);
                    set.add(p);
                }
            }
        }
    }
}
