package on2016_06.on2016_06_23_Codeforces_Round__359__Div__1_.D___Kay_and_Eternity;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static java.lang.Integer.MIN_VALUE;
import static java.util.Arrays.sort;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.ArrayUtils.orderBy;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        readIntArrays(in, x, y);
        orderBy(x, y);
        Event[] horizontal = new Event[n * 2];
        for (int i = 0; i < n; i++) {
            horizontal[2 * i] = new Event(EventType.ADD, x[i], i);
            horizontal[2 * i + 1] = new Event(EventType.REMOVE, x[i] + k, i);
        }
        Event[] add = new Event[n];
        Event[] remove = new Event[n];
        for (int i = 0; i < n; i++) {
            add[i] = new Event(EventType.ADD, y[i], i);
            remove[i] = new Event(EventType.REMOVE, y[i] + k, i);
        }
        sort(horizontal);
        NavigableSet<Event> set = new TreeSet<>();
        int last = MIN_VALUE;
        long[] answer = new long[n];
        for (Event event : horizontal) {
            if (last != event.at && !set.isEmpty()) {
                int current = 0;
                int cLast = MIN_VALUE;
                for (Event cEvent : set) {
                    if (cLast != cEvent.at && current != 0) {
                        answer[current - 1] += (cEvent.at - cLast) * (event.at - last);
                    }
                    cLast = cEvent.at;
                    if (cEvent.type == EventType.ADD) {
                        current++;
                    } else {
                        current--;
                    }
                }
            }
            last = event.at;
            if (event.type == EventType.ADD) {
                set.add(add[event.id]);
                set.add(remove[event.id]);
            } else {
                set.remove(add[event.id]);
                set.remove(remove[event.id]);
            }
        }
        out.printLine(answer);
    }

    static class Event implements Comparable<Event> {
        EventType type;
        int at;
        int id;

        public Event(EventType type, int at, int id) {
            this.type = type;
            this.at = at;
            this.id = id;
        }

        @Override
        public int compareTo(Event o) {
            if (at != o.at) {
                return at - o.at;
            }
            return id - o.id;
        }
    }

    enum EventType {
        ADD, REMOVE
    }
}
