package net.egork;

import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.floor;
import static java.lang.Math.round;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[][] price = in.readTable(2 * n, 2 * n);
        int m = in.readInt();
        Line[] lines = new Line[4 * n + 2];
        int at = 0;
        for (int i = -n; i <= n; i++) {
            lines[at++] = new Line(1, 0, i);
            lines[at++] = new Line(0, 1, i);
        }
        for (int i = 0; i < m; i++) {
            int x1 = in.readInt();
            int y1 = in.readInt();
            int x2 = in.readInt();
            int y2 = in.readInt();
            Line road = new Point(x1, y1).line(new Point(x2, y2));
            List<Point> points = new ArrayList<>();
            for (Line line : lines) {
                points.add(road.intersect(line));
            }
            points.sort(Comparator.comparingDouble(a -> a.x));
            Point last = null;
            double answer = 0;
            for (Point p : points) {
                if (last != null) {
                    Segment segment = new Segment(last, p);
                    Point middle = segment.middle();
                    if (segment.length() > 1e-8 && middle.x >= -n && middle.x <= n && middle.y >= -n && middle.y <= n) {
                        int row = (int) (-round(floor(middle.y)) + n - 1);
                        int col = (int) (round(floor(middle.x)) + n);
                        answer += (price[row][col] - '0') * segment.length();
                    }
                }
                last = p;
            }
            out.printLine(answer);
        }
    }
}
