package on2015_08.on2015_08_22_SNSS_2015_R4.E____________________;



import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        Point[] sats = new Point[n];
        for (int i = 0; i < n; i++) {
            sats[i] = Point.readPoint(in);
        }
        int l = in.readInt();
        in.readInt();
        Segment segment = new Segment(Point.readPoint(in), Point.readPoint(in));
        Line base = new Line(0, 1, 0);
        double[] from = new double[n];
        double[] to = new double[n];
        int qty = 0;
        Line segLine = segment.line();
        for (Point sat : sats) {
            if (sat.y <= segment.a.y && sat.y <= segment.b.y) {
                continue;
            }
            if (sat.y <= segment.a.y || sat.y <= segment.b.y) {
                double pos = sat.y <= segment.a.y ? sat.line(segment.b).intersect(base).x : sat.line(segment.a).intersect(base).x;
                Line parallel = new Line(segLine.a, segLine.b, -segLine.a * sat.x - segLine.b * sat.y);
                double posParallel = parallel.intersect(base).x;
                if (posParallel < pos) {
                    from[qty] = pos;
                    to[qty++] = Double.POSITIVE_INFINITY;
                } else {
                    from[qty] = Double.NEGATIVE_INFINITY;
                    to[qty++] = pos;
                }
                continue;
            }
            double first = sat.line(segment.a).intersect(base).x;
            double second = sat.line(segment.b).intersect(base).x;
            from[qty] = Math.min(first, second);
            to[qty++] = Math.max(first, second);
        }
        double answer = l;
        from = Arrays.copyOf(from, qty);
        to = Arrays.copyOf(to, qty);
        int[] order = ArrayUtils.order(from);
        for (int i : order) {
            if (from[i] > answer + 1e-8) {
                out.printLine(answer);
                return;
            }
            answer = Math.max(answer, to[i]);
        }
        out.printLine(answer);
    }
}
