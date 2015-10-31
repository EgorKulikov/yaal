package on2015_06.on2015_06_14_RCC_2015_________________.C________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskC {
    static class Event {
        int at;
        int delta;

        public Event(int at, int delta) {
            this.at = at;
            this.delta = delta;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] x = new int[count];
        int[] y = new int[count];
        IOUtils.readIntArrays(in, x, y);
        int[] realY = ArrayUtils.compress(y);
        int[][] xs = new int[realY.length - 1][2];
        int[] size = new int[xs.length];
        for (int i = 0; i < count; i++) {
            int x0 = x[i];
            int y0 = y[i];
            int x1 = x[(i + 1) % count];
            int y1 = y[(i + 1) % count];
            if (y0 != y1) {
                for (int j = Math.min(y0, y1); j < Math.max(y0, y1); j++) {
                    xs[j][size[j]++] = x0;
                }
            }
        }
        List<Event> events = new ArrayList<>();
        long answer = 0;
        for (int i = 0; i < xs.length; i++) {
            if (xs[i][1] < xs[i][0]) {
                ArrayUtils.reverse(xs[i]);
            }
            answer += (long)(xs[i][1] - xs[i][0]) * (realY[i + 1] - realY[i]);
            events.add(new Event(xs[i][0], realY[i] - realY[i + 1]));
            events.add(new Event(xs[i][1], realY[i] - realY[i + 1]));
            events.add(new Event((xs[i][0] + xs[i][1]) / 2, realY[i + 1] - realY[i]));
            events.add(new Event((xs[i][0] + xs[i][1] + 1) / 2, realY[i + 1] - realY[i]));
        }
        Collections.sort(events, (f, s) -> (f.at - s.at));
        long delta = 0;
        long start = 0;
        long current = answer;
        for (Event event : events) {
            current += delta * (event.at - start);
            start = event.at;
            delta += event.delta;
            answer = Math.min(answer, current);
        }
        out.printLine(answer);
    }
}
