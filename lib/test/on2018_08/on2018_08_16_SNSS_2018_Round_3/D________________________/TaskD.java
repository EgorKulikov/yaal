package on2018_08.on2018_08_16_SNSS_2018_Round_3.D________________________;



import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Segment;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.abs;
import static java.util.Arrays.sort;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Point[] points = new Point[4];
        for (int i = 0; i < 4; i++) {
            points[i] = Point.readPoint(in);
        }
        Polygon all = new Polygon(points);
        double s = all.area();
        for (int i = 0; i < 4; i++) {
            Polygon triangle = new Polygon(points[(i + 1) & 3], points[(i + 2) & 3], points[(i + 3) & 3]);
            Polygon toDivide = new Polygon(points[i], points[(i + 1) & 3], points[(i + 3) & 3]);
            for (int j = 0; j < 3; j++) {
                Point middle = new Segment(toDivide.vertices[(j + 1) % 3], toDivide.vertices[(j + 2) % 3]).middle();
                Polygon one = new Polygon(toDivide.vertices[j], toDivide.vertices[(j + 1) % 3], middle);
                Polygon two = new Polygon(toDivide.vertices[j], toDivide.vertices[(j + 2) % 3], middle);
                if (accept(triangle, one, two)) {
                    out.printLine("YES");
                    return;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                Segment segment = new Segment(points[(i + 2 * j + 1) & 3], points[(i + 2) & 3]);
                double left = 0;
                double right = 1;
                boolean toRight = false;
                for (int k = 0; k < 100; k++) {
                    double middle = (left + right) / 2;
                    double x = segment.a.x * (1 - middle) + segment.b.x * middle;
                    double y = segment.a.y * (1 - middle) + segment.b.y * middle;
                    Point p = new Point(x, y);
                    Polygon triangle = new Polygon(points[i], segment.a, p);
                    if (triangle.area() > s / 3) {
                        right = middle;
                        toRight = true;
                    } else {
                        left = middle;
                    }
                }
                if (!toRight) {
                    continue;
                }
                double middle = (left + right) / 2;
                double x = segment.a.x * (1 - middle) + segment.b.x * middle;
                double y = segment.a.y * (1 - middle) + segment.b.y * middle;
                Point p = new Point(x, y);
                Polygon triangle = new Polygon(points[i], segment.a, p);
                Polygon a = new Polygon(points[i], p, points[(i + 2 * (1 - j) + 1) & 3]);
                Polygon b = new Polygon(segment.b, p, points[(i + 2 * (1 - j) + 1) & 3]);
                if (accept(triangle, a, b)) {
                    out.printLine("YES");
                    return;
                }
            }
        }
        out.printLine("NO");
    }

    private boolean accept(Polygon...p) {
        double[][] sides = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sides[i][j] = p[i].sides()[j].length();
            }
            sort(sides[i]);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < 3; k++) {
                    if (abs(sides[i][k] - sides[j][k]) > 1e-5) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
