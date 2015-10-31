package on2015_08.on2015_08_20_101_Hack_Aug_2015.Squares_and_Points;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class SquaresAndPoints {
    enum Type {
        ADD,
        CHECK,
        REMOVE
    }

    static class Event implements Comparable<Event> {
        int time;
        int id;
        Type type;

        @Override
        public int compareTo(Event o) {
            if (time != o.time) {
                return Integer.compare(time, o.time);
            }
            return type.ordinal() - o.type.ordinal();
        }

        public Event(int time, int id, Type type) {
            this.time = time;
            this.id = id;
            this.type = type;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int l = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        IOUtils.readIntArrays(in, x, y);
        int[] a = new int[m];
        int[] b = new int[m];
        IOUtils.readIntArrays(in, a, b);
        int[] x1 = new int[n];
        int[] y1 = new int[n];
        for (int i = 0; i < n; i++) {
            x1[i] = x[i] + l;
            y1[i] = y[i] + l;
        }
        ArrayUtils.compress(x, x1, a);
        int my = ArrayUtils.compress(y, y1, b).length;
        Event[] events = new Event[2 * n + m];
        for (int i = 0; i < n; i++) {
            events[2 * i] = new Event(x[i], i, Type.ADD);
            events[2 * i + 1] = new Event(x1[i], i, Type.REMOVE);
        }
        for (int i = 0; i < m; i++) {
            events[2 * n + i] = new Event(a[i], i, Type.CHECK);
        }
        Arrays.sort(events);
        IntervalTree tree = new SumIntervalTree(my);
        long answer = 0;
        for (Event event : events) {
            if (event.type == Type.CHECK) {
                answer = Math.max(answer, tree.query(b[event.id], b[event.id]));
            } else {
                tree.update(y[event.id], y1[event.id], event.type == Type.ADD ? 1 : -1);
            }
        }
        out.printLine(answer);
    }
}
