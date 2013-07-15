package on2013_07.on2013_07_July_Challenge_2013.Circle_of_death;



import net.egork.geometry.Circle;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CircleOfDeath {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++)
			points[i] = Point.readPoint(in);
		int total = 0;
		int killed = 0;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				for (int k = j + 1; k < count; k++) {
					Line line = points[i].line(points[j]);
					Circle circle;
					if (line.contains(points[k]))
						circle = new Circle(new Point(Long.MIN_VALUE, Long.MIN_VALUE), 0);
					else {
						Line first = line.perpendicular(new Segment(points[i], points[j]).middle());
						Line second = points[i].line(points[k]).perpendicular(new Segment(points[i], points[k]).middle());
						Point center = first.intersect(second);
						circle = new Circle(center, center.distance(points[i]));
					}
					for (int l = 0; l < count; l++) {
						if (l == i || l == j || l == k)
							continue;
						total++;
						if (circle.contains(points[l]))
							killed++;
					}
				}
			}
		}
		out.printLine((double)killed / total);
    }
}
