package on2013_01.on2013_01_25_ACM_ICPC_Amritapuri_onsite_regionals_2012___Replay.Entmoot;



import net.egork.geometry.Circle;
import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Entmoot {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		int[] speed = new int[count];
		IOUtils.readIntArrays(in, x, y, speed);
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++)
			points[i] = new Point(x[i], y[i]);
		double answer = Double.POSITIVE_INFINITY;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				double xx = ((double)x[i] * speed[j] + (double)x[j] * speed[i]) / (speed[i] + speed[j]);
				double yy = ((double)y[i] * speed[j] + (double)y[j] * speed[i]) / (speed[i] + speed[j]);
				answer = Math.min(answer, check(xx, yy, x, y, speed));
				double distance = points[i].distance(points[j]);
				for (int k = j + 1; k < count; k++) {
					double min = 0;
					double max = Math.max(Math.hypot(x[i] - x[j], y[i] - y[j]) / speed[j], Math.hypot(x[i] - x[k], y[i] - y[k]) / speed[k]);
					for (int l = 0; l < 100; l++) {
						double middle = (max + min) / 2;
						if (distance + GeometryUtils.epsilon > (speed[i] + speed[j]) * middle) {
							min = middle;
							continue;
						}
						if (distance - GeometryUtils.epsilon < Math.abs(speed[i] - speed[j]) * middle) {
							max = middle;
							continue;
						}
						Circle first = new Circle(points[i], speed[i] * middle);
						Circle second = new Circle(points[j], speed[j] * middle);
						Point[] intersect = first.intersect(second);
						if (intersect.length == 0) {
							if ((speed[i] + speed[j]) * middle - distance > distance - Math.abs(speed[i] - speed[j]) * middle)
								max = middle;
							else
								min = middle;
						} else if (intersect.length == 1) {
							if (intersect[0].distance(points[k]) < speed[k] * middle)
								max = middle;
							else
								min = middle;
						} else {
							if (new Segment(intersect[0], intersect[1]).distance(points[k]) < speed[k] * middle)
								max = middle;
							else
								min = middle;
						}
					}
					Circle first = new Circle(points[i], speed[i] * max);
					Circle second = new Circle(points[j], speed[j] * max);
					for (Point p : first.intersect(second))
						answer = Math.min(answer, check(p.x, p.y, x, y, speed));
				}
			}
		}
		out.printLine(answer);
	}

	private double check(double xx, double yy, int[] x, int[] y, int[] speed) {
		double answer = 0;
		for (int i = 0; i < x.length; i++)
			answer = Math.max(answer, Math.hypot(xx - x[i], yy - y[i]) / speed[i]);
		return answer;
	}
}
