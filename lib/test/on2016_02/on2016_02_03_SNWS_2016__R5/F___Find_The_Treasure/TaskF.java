package on2016_02.on2016_02_03_SNWS_2016__R5.F___Find_The_Treasure;



import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point[] points = new Point[3];
		for (int i = 0; i < 3; i++) {
			points[i] = Point.readPoint(in);
		}
		if (!Polygon.over(points[0], points[2], points[1])) {
			reverse(points);
		}
		Line[] lines = new Line[2];
		for (int i = 0; i < 2; i++) {
			Point next = points[i + 1];
			Point current = points[i];
			Point top = points[(i + 2) % 3];
			double a1 = atan2(next.y - current.y, next.x - current.x);
			double a2 = atan2(top.y - current.y, top.x - current.x);
			double angle = a1 + (a2 - a1) / 2;
			lines[i] = new Line(current, angle);
		}
		Point answer = lines[0].intersect(lines[1]);
		out.printLine(answer.x, answer.y);
	}
}
