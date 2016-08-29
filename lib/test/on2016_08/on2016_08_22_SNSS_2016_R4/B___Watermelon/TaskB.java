package on2016_08.on2016_08_22_SNSS_2016_R4.B___Watermelon;



import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        Point start = Point.readPoint(in);
        int l = in.readInt();
        double alpha = in.readDouble();
        int t = in.readInt();
        double dx = cos(alpha);
        double dy = sin(alpha);
        Point end = new Point(start.x + dx * t, start.y + dy * t);
        Line l1 = new Line(start, alpha + PI / 2);
        Line l2 = new Line(end, alpha + PI / 2);
        Point p1 = new Point(start.x - dy * l / 2d, start.y + dx * l / 2d);
        Point p2 = new Point(start.x + dy * l / 2d, start.y - dx * l / 2d);
        Line l3 = new Line(p1, alpha);
        Line l4 = new Line(p2, alpha);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            Point p = Point.readPoint(in);
            if (l1.value(p) * l2.value(p) < 0 && l3.value(p) * l4.value(p) < 0) {
                answer++;
            }
        }
        out.printLine(answer);
    }
}
