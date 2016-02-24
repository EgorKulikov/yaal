package on2016_02.on2016_02_20_February_Clash__16.Square;



import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class Square {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = Point.readPoint(in);
        }
        Polygon hull = Polygon.convexHull(points);
        n = hull.vertices.length;
        double[] angles = new double[4 * n];
        int at = 0;
        for (Segment side : hull.sides()) {
            double angle = atan2(side.b.y - side.a.y, side.b.x - side.a.x);
            angles[at++] = GeometryUtils.canonicalAngle(angle);
            angles[at++] = GeometryUtils.canonicalAngle(angle + PI / 2);
            angles[at++] = GeometryUtils.canonicalAngle(angle + PI);
            angles[at++] = GeometryUtils.canonicalAngle(angle + 3 * PI / 2);
        }
        sort(angles);
        double start = 0;
        double answer = Double.POSITIVE_INFINITY;
        for (double angle : angles) {
            if (start == PI / 2) {
                break;
            }
            if (angle < start) {
                continue;
            }
            angle = Math.min(angle, PI / 2);
            double middle = (angle + start) / 2;
            double dx = cos(middle);
            double dy = sin(middle);
            Point left = hull.vertices[0];
            double lVal = dx * left.x + dy * left.y;
            Point right = hull.vertices[0];
            double rVal = lVal;
            Point top = hull.vertices[0];
            double tVal = dy * left.x - dx * left.y;
            Point bottom = hull.vertices[0];
            double bVal = tVal;
            for (Point p : hull.vertices) {
                double hValue = dx * p.x + dy * p.y;
                if (hValue < lVal) {
                    lVal = hValue;
                    left = p;
                }
                if (hValue > rVal) {
                    rVal = hValue;
                    right = p;
                }
                double vValue = dy * p.x - dx * p.y;
                if (vValue < tVal) {
                    tVal = vValue;
                    top = p;
                }
                if (vValue > bVal) {
                    bVal = vValue;
                    bottom = p;
                }
            }
            double from = start;
            double to = angle;
            start = angle;
            for (int i = 0; i < 100; i++) {
                double fMiddle = (2 * from + to) / 3;
                double tMiddle = (2 * to + from) / 3;
                double fValue = calculate(fMiddle, left, right, top, bottom);
                double tValue = calculate(tMiddle, left, right, top, bottom);
                if (fValue > tValue) {
                    from = fMiddle;
                } else {
                    to = tMiddle;
                }
            }
            answer = Math.min(answer, calculate((from + to) / 2, left, right, top, bottom));
        }
        out.printFormat("%.4f\n", answer);
    }

    private double calculate(double angle, Point left, Point right, Point top, Point bottom) {
        double dx = cos(angle);
        double dy = sin(angle);
        return max(dx * right.x + dy * right.y - dx * left.x - dy * left.y, dy * bottom.x - dx * bottom.y - dy * top
                .x + dx * top.y);
    }
}
