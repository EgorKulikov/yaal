package on2016_02.on2016_02_07_IndiaHacks__Algorithms.A___Hourglasses;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class AHourglasses {
    int limit;
    int a;
    int b;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        a = in.readInt();
        b = in.readInt();
        int g = IntegerUtils.gcd(a, b);
        a /= g;
        b /= g;
        boolean[] can = new boolean[a * b + 1];
        NavigableSet<State> set = new TreeSet<>();
        set.add(new State(0, 0, 0));
        limit = a * b;
        int last = 0;
        int inARow = 1;
        while (!set.isEmpty()) {
            State current = set.pollFirst();
            if (current.time > last) {
                if (current.time - last > 1) {
                    inARow = 0;
                }
                inARow++;
                last = current.time;
            }
            can[current.time] = true;
            if (current.aa == 1 || current.bb == 1 || inARow >= min(a, b)) {
                Arrays.fill(can, current.time, can.length, true);
                break;
            }
            for (int i = 0; i < 2; i++) {
                current.aa = a - current.aa;
                for (int j = 0; j < 2; j++) {
                    current.bb = b - current.bb;
                    int max = max(current.aa, current.bb);
                    if (max == 0) {
                        continue;
                    }
                    int min = Math.min(current.aa, current.bb);
                    if (min != 0 && current.time + min <= limit) {
                        set.add(new State(current.time + min, current.aa - min, current.bb - min));
                    }
                    if (current.time + max <= limit) {
                        set.add(new State(current.time + Math.max(current.aa, current.bb), 0, 0));
                    }
                }
            }
        }
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            int z = in.readInt();
            if (z % g != 0) {
                out.print(0);
            } else {
                z /= g;
                if (z <= a * b) {
                    out.print(can[z] ? 1 : 0);
                } else {
                    out.print(1);
                }
            }
        }
        out.printLine();
    }

    class State implements Comparable<State> {
        int time;
        int aa;
        int bb;

        public State(int time, int aa, int bb) {
            this.time = time;
            this.aa = min(aa, a - aa);
            this.bb = min(bb, b - bb);
            if (aa == 1 || bb == 1) {
                limit = Math.min(limit, time);
            }
        }

        @Override
        public int compareTo(State o) {
            if (time != o.time) {
                return time - o.time;
            }
            if (aa != o.aa) {
                return aa - o.aa;
            }
            return bb - o.bb;
        }
    }
}
