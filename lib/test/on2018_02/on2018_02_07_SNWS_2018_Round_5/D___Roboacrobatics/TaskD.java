package on2018_02.on2018_02_07_SNWS_2018_Round_5.D___Roboacrobatics;



import net.egork.collections.map.Counter;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.IntegerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Long.MAX_VALUE;
import static net.egork.misc.ArrayUtils.orderBy;

public class TaskD {
    IntList ps;
    List<IntList> rs;
    long[] x;
    long[] p;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        x = new long[n];
        p = new long[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.readLong();
            p[i] = in.readInt();
        }
        orderBy(p, x);
        ps = new IntArrayList();
        rs = new ArrayList<>();
        int start = 0;
        int height = 0;
        long modulo = 1;
        for (int i = 1; i <= n; i++) {
            if (i == n || p[i] != p[i - 1]) {
                Counter<Integer> r = new Counter<>();
                for (int j = start; j < i; j++) {
                    r.add((int) (x[j] % p[j]));
                }
                int max = 0;
                for (Long qty : r.values()) {
                    max = Math.max(max, (int)(long)qty);
                }
                height += max;
                ps.add((int) p[start]);
                IntList crs = new IntArrayList();
                for (Map.Entry<Integer, Long> entry : r.entrySet()) {
                    if (entry.getValue() == max) {
                        crs.add(entry.getKey());
                    }
                }
                modulo *= p[start];
                if (modulo > 1000000000) {
                    throw new RuntimeException();
                }
                rs.add(crs);
                start = i;
            }
        }
        long pos = go(0, 0L, 1L);
        int qty = 0;
        for (int i = 0; i < n; i++) {
            if (x[i] % p[i] == pos % p[i]) {
                qty++;
            }
        }
        out.printLine(pos, qty);
    }

    private long go(int position, long remainder, long modulo) {
        if (position == ps.size()) {
            long result = remainder;
            for (int i = 0; i < x.length; i++) {
                if (x[i] > result && x[i] % p[i] == remainder % p[i]) {
                    result += (x[i] - result + modulo - 1) / modulo * modulo;
                }
            }
            return result;
        }
        long result = MAX_VALUE;
        int p = ps.get(position);
        for (int r : rs.get(position)) {
            long nRemainder = IntegerUtils.findCommonFast(remainder, modulo, r, p);
            long call = go(position + 1, nRemainder, modulo * p);
            result = Math.min(result, call);
        }
        return result;
    }
}
