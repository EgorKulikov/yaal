package on2012_12.on2012_12_19_Volume_3._1215___Exactness_of_Projectile_Hit;



import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1215 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point shot = Point.readPoint(in);
		int count = in.readInt();
		Point[] target = new Point[count];
		for (int i = 0; i < count; i++) {
			target[i] = Point.readPoint(in);
		}
		Polygon polygon = new Polygon(target);
		if (polygon.contains(shot)) {
			out.printLine("0.000");
			return;
		}
		double answer = new Segment(target[count - 1], target[0]).distance(shot);
		for (int i = 1; i < count; i++)
			answer = Math.min(answer, new Segment(target[i - 1], target[i]).distance(shot));
		out.printFormat("%.3f\n", 2 * answer);
	}
}
