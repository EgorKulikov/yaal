package on2012_08.on2012_7_17.taskc;



import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        GeometryUtils.epsilon = 1e-4;
        Point[] points = new Point[3];
        for (int i = 0; i < 3; i++)
            points[i] = Point.readPoint(in);
        Line first = points[0].line(points[1]);
        Line second = points[0].line(points[2]);
        Line firstPerpendicular = first.perpendicular(new Segment(points[0], points[1]).middle());
        Line secondPerpendicular = second.perpendicular(new Segment(points[0], points[2]).middle());
        Point center = firstPerpendicular.intersect(secondPerpendicular);
        if (center == null) {
            out.printLine(0);
            return;
        }
        double[] angles = new double[3];
        for (int i = 0; i < 3; i++) {
            angles[i] = Math.atan2(points[i].y - center.y, points[i].x - center.x);
        }
        Arrays.sort(angles);
        double firstAngle = angles[1] - angles[0];
        double secondAngle = angles[2] - angles[1];
        for (int i = 3; i <= 30; i++) {
            double delta = 2 * Math.PI / i;
            double firstCount = firstAngle / delta;
            double secondCount = secondAngle / delta;
            if (Math.abs(firstCount - Math.round(firstCount)) < GeometryUtils.epsilon &&
                    Math.abs(secondCount - Math.round(secondCount)) < GeometryUtils.epsilon) {
                out.printLine(i);
                return;
            }
        }
        out.printLine(0);
	}
}
