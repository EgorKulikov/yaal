package on2016_08.on2016_08_23_August_Circuits.Destroying_houses;



import net.egork.geometry.Circle;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.*;
import static net.egork.geometry.GeometryUtils.epsilon;
import static net.egork.geometry.Point.ORIGIN;

public class DestroyingHouses {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int d = in.readInt();
        int r = in.readInt();
        Circle base = new Circle(ORIGIN, r);
        double answer = 0;
        for (int i = 0; i < n; i++) {
            Point house = Point.readPoint(in);
            Circle area = new Circle(house, d);
            Point[] points = area.intersect(base);
            if (points == null || points.length <= 1) {
                double dst = ORIGIN.distance(house);
                if (d + dst < r + epsilon) {
                    answer += PI * d * d;
                } else if (r + dst < d + epsilon) {
                    answer += PI * r * r;
                }
            } else {
                answer += get(base, area, points) + get(area, base, points);
            }
        }
        answer /= PI * r * r;
        out.printFormat("%.4f\n", answer);
    }

    private double get(Circle base, Circle area, Point[] points) {
        double a1 = atan2(points[0].y - base.center.y, points[0].x - base.center.x);
        double a2 = atan2(points[1].y - base.center.y, points[1].x - base.center.x);
        double a = Math.abs(a1 - a2);
        Point p1 = new Point(base.center.x + base.radius * cos((a1 + a2) / 2), base.center.y + base.radius * sin((a1 +
            a2) / 2));
        if (!area.contains(p1)) {
            a = 2 * PI - a;
        }
        double result = a / 2 * base.radius * base.radius;
        double triangle = sin(a) * base.radius * base.radius / 2;
        result -= triangle;
        return result;
    }
}
