package on2016_03.on2016_03_13_GP_of_Tatarstan.E___The_Carpet;



import net.egork.geometry.Angle;
import net.egork.geometry.Circle;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.geometry.Vector;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.*;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Point p1 = Point.readPoint(in);
        Point p2 = Point.readPoint(in);
        Point c = Point.readPoint(in);
        int radius = in.readInt();
        if (p1.equals(p2)) {
            out.printLine(0);
            return;
        }
        Circle circle = new Circle(c, radius);
        Line line = p1.line(p2);
        Point[] intersection = line.intersect(circle);
        boolean canDirect = true;
        boolean wasNotStrict = false;
        Segment segment = new Segment(p1, p2);
        for (Point p : intersection) {
            if (segment.contains(p, false)) {
                canDirect = false;
                break;
            } else if (segment.contains(p, true)) {
                if (wasNotStrict) {
                    canDirect = false;
                } else {
                    wasNotStrict = true;
                }
            }
        }
        if (canDirect) {
            double answer = Double.POSITIVE_INFINITY;
            Point p = steiner(circle.center, p1, p2);
            if (circle.center.distance(p) >= circle.radius) {
                out.printLine(value(circle.center, p1, p2) - radius);
                return;
            }
            for (int i = 0; i < 360; i++) {
                answer = Math.min(answer, solve(circle, PI * i / 180d, PI * (i + 1) / 180d, p1, p2));
            }
            out.printLine(answer);
            return;
        }
        Point[] v1 = get(p1, circle);
        Point[] v2 = get(p2, circle);
        double answer = Double.POSITIVE_INFINITY;
        for (Point e1 : v1) {
            for (Point e2 : v2) {
                answer = Math.min(answer, e1.distance(p1) + e2.distance(p2) + byCircle(e1, e2, circle));
            }
        }
        out.printLine(answer);
    }

    private double solve(Circle circle, double left, double right, Point p1, Point p2) {
        for (int i = 0; i < 200; i++) {
            double mLeft = (2 * left + right) / 3;
            double mRight = (2 * right + left) / 3;
            Point pLeft = get(circle, mLeft);
            Point pRight = get(circle, mRight);
            if (value(pLeft, p1, p2) < value(pRight, p1, p2)) {
                right = mRight;
            } else {
                left = mLeft;
            }
        }
        return value(get(circle, (left + right) / 2), p1, p2);
    }

    private Point get(Circle circle, double ang) {
        return new Point(circle.center.x + cos(ang) * circle.radius, circle.center.y + sin(ang) * circle.radius);
    }

    private Point[] get(Point p, Circle circle) {
        return circle.findTouchingPoints(p);
    }

    private double byCircle(Point e1, Point e2, Circle circle) {
        Angle angle = new Angle(circle.center, e1, e2);
        double val = angle.value();
        return val * circle.radius;
    }

    double value(Point a, Point b, Point c) {
        Point s = steiner(a, b, c);
        return s.distance(a) + s.distance(b) + s.distance(c);
    }

    Point steiner(Point a, Point b, Point c) {
        if (a.equals(b)) {
            return a;
        }
        if (a.equals(c)) {
            return a;
        }
        if (b.equals(c)) {
            return b;
        }
        if (new Angle(a, b, c).value() >= PI * 2 / 3) {
            return a;
        }
        if (new Angle(b, a, c).value() >= PI * 2 / 3) {
            return b;
        }
        if (new Angle(c, b, a).value() >= PI * 2 / 3) {
            return c;
        }
        Point x1 = get(a, b, PI * 2 / 3, c);
        Point x2 = get(b, c, PI * 2 / 3, a);
        Circle c1 = new Circle(x1, x1.distance(b));
        Circle c2 = new Circle(x2, x2.distance(b));
        Point[] intersect = c1.intersect(c2);
        Point best = null;
        for (Point p : intersect) {
            if (best == null || best.distance(b) < p.distance(b)) {
                best = p;
            }
        }
        return best;
    }

    private Point get(Point m1, Point m2, double a1, Point c) {
        Point[] result = new Point[2];
        Point middle = new Segment(m1, m2).middle();
        double dist = m1.distance(m2) * abs(tan(PI / 2 - a1)) / 2;
        Line line = m1.line(m2);
        result[0] = middle.apply(new Vector(line.a, line.b), dist);
        result[1] = middle.apply(new Vector(line.a, line.b), -dist);
        if (Math.signum(line.value(c)) != Math.signum(line.value(result[0]))) {
            return result[0];
        }
        return result[1];
    }
}
