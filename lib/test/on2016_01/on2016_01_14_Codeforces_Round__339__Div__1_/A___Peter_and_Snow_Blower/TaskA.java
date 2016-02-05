package on2016_01.on2016_01_14_Codeforces_Round__339__Div__1_.A___Peter_and_Snow_Blower;



import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		Point p = Point.readPoint(in);
		double min = Double.POSITIVE_INFINITY;
		double max = 0;
		Point first = null;
		Point last = null;
		for (int i = 0; i < n; i++) {
			Point current = Point.readPoint(in);
			if (i == 0) {
				first = current;
			}
			double distance = p.distance(current);
			min = Math.min(min, distance);
			max = Math.max(max, distance);
			if (last != null) {
				min = Math.min(min, new Segment(last, current).distance(p));
			}
			last = current;
			if (i == n - 1) {
				min = Math.min(min, new Segment(first, current).distance(p));
			}
		}
		double answer = (max - min) * (max + min) * Math.PI;
		out.printLine(answer);
	}
}
