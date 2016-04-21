package on2016_03.on2016_03_13_GP_of_Tatarstan.A___Three_Seamarks;



import net.egork.geometry.Circle;
import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.geometry.Vector;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.*;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        GeometryUtils.epsilon = 1e-8;
        Point m1 = Point.readPoint(in);
        Point m2 = Point.readPoint(in);
        Point m3 = Point.readPoint(in);
        double a1 = in.readDouble() / 180d * PI;
        double a2 = in.readDouble() / 180d * PI;
        Point[] c1 = get(m1, m2, a1);
        Point[] c2 = get(m2, m3, a2);
        Point answer = null;
        Point[] points = {m1, m2, m3};
        double[] at2 = new double[3];
        for (Point p1 : c1) {
            for (Point p2 : c2) {
                Circle circle = new Circle(p1, p1.distance(m2));
                Point[] inters = circle.intersect(new Circle(p2, p2.distance(m2)));
                if (inters == null) {
                    for (int i = 0; i < 3; i++) {
                        at2[i] = atan2(points[i].y - p1.y, points[i].x - p1.x);
                    }
                    for (int i = 0; i < points.length; i++) {
                        Point pp1 = points[i];
                        for (int i1 = 0; i1 < points.length; i1++) {
                            Point pp2 = points[i1];
                            if (pp1 == pp2) {
                                break;
                            }
                            double b1 = at2[i];
                            double b2 = at2[i1];
                            double d1 = (b1 + b2) / 2;
                            Point dp = get(circle, d1);
                            answer = getPoint(m1, m2, m3, a1, a2, answer, dp);
                            dp = get(circle, d1 + PI);
                            answer = getPoint(m1, m2, m3, a1, a2, answer, dp);
                        }
                    }
                    continue;
                }
                for (Point p : inters) {
                    answer = getPoint(m1, m2, m3, a1, a2, answer, p);
                }
            }
        }
        out.printLine(answer.x, answer.y);
    }

    protected Point getPoint(Point m1, Point m2, Point m3, double a1, double a2, Point answer, Point dp) {
        if (m2.distance(dp) < GeometryUtils.epsilon) {
            return answer;
        }
        if (angle(m1, m2, dp, a1) && angle(m2, m3, dp, a2)) {
            if (answer == null) {
                answer = dp;
            } else if (dp.distance(m2) > answer.distance(m2)) {
                answer = dp;
            }
        }
//        if (answer != null && (m2.distance(answer) < GeometryUtils.epsilon ||
//                m1.distance(answer) < GeometryUtils.epsilon ||
//                m3.distance(answer) < GeometryUtils.epsilon)) {
//            return null;
//        }
        return answer;
    }

    private Point get(Circle circle, double ang) {
        return new Point(circle.center.x + cos(ang) * circle.radius, circle.center.y + sin(ang) * circle.radius);
    }



    private boolean angle(Point m1, Point m2, Point p1, double a1) {
        if (abs(a1 - PI / 2) < GeometryUtils.epsilon) {
            return true;
        }
//        Angle angle = new Angle(p1, m1, m2);
        boolean value = value(m1, m2, p1);
        return value ^ (a1 > PI / 2);
//        return (abs(value - a1) < abs(value - (PI - a1)));
    }

    boolean value(Point a, Point b, Point o) {
        double x1 = a.x - o.x;
        double y1 = a.y - o.y;
        double x2 = b.x - o.x;
        double y2 = b.y - o.y;
        double scal = x1 * x2 + y1 * y2;
//        double vect = x1 * y2 - x2 * y1;
        return scal > 0;
//        return Math.abs(Math.atan2(vect, scal));
    }


    private Point[] get(Point m1, Point m2, double a1) {
        Point[] result = new Point[2];
        Point middle = new Segment(m1, m2).middle();
        double dist = m1.distance(m2) * abs(tan(PI / 2 - a1)) / 2;
        Line line = m1.line(m2);
        result[0] = middle.apply(new Vector(line.a, line.b), dist);
        result[1] = middle.apply(new Vector(line.a, line.b), -dist);
        return result;
    }
}
