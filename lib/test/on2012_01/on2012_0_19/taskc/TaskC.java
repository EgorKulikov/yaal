package on2012_01.on2012_0_19.taskc;



import net.egork.geometry.*;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        List<Point> points = new ArrayList<Point>();
        while (!in.isExhausted()) {
            double x = in.readDouble();
            double y = in.readDouble();
            points.add(new Point(x, y));
        }
        int answer = Math.min(points.size(), 1);
        for (Point first : points) {
            for (Point second : points) {
                if (first == second)
                    break;
                double distance = first.distance(second);
                if (distance > 5 + GeometryUtils.epsilon)
                    continue;
                double sideDistance = Math.sqrt(25 - distance * distance) / 2;
                Line line = first.line(second);
                Point middle = new Segment(first, second).middle();
                Line perpendicular = line.perpendicular(middle);
                Point center = new Point(middle.x + perpendicular.b * sideDistance, middle.y - perpendicular.a * sideDistance);
                Circle circle = new Circle(center, 2.5);
                answer = Math.max(answer, check(circle, points));
                center = new Point(middle.x - perpendicular.b * sideDistance, middle.y + perpendicular.a * sideDistance);
                circle = new Circle(center, 2.5);
                answer = Math.max(answer, check(circle, points));
            }
        }
        out.printLine(answer);
	}

    private int check(Circle circle, List<Point> points) {
        int result = 0;
        for (Point point : points) {
            if (circle.contains(point))
                result++;
        }
        return result;
    }
}
