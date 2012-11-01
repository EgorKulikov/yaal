package on2012_10.on2012_10_22_ACM_ICPC_NEERC_Southern_Subregional.TaskD;



import net.egork.geometry.Circle;
import net.egork.geometry.Point;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] x = IOUtils.readIntArray(in, count);
		int[] lengths = IOUtils.readIntArray(in, count);
		int[] cuts = IOUtils.readIntArray(in, count - 1);
		int mask = (1 << count) - 1;
		double[] xx = new double[2 * count - 1];
		double[] yy = new double[2 * count - 1];
		int[] hangedBy = new int[2 * count - 1];
		for (int i = 0; i < count; i++) {
			double minY = Double.NEGATIVE_INFINITY;
			double minX = 0;
			for (int j = 0; j < count; j++) {
				if ((mask >> j & 1) == 0)
					continue;
				if (minY < -lengths[j]) {
					minY = -lengths[j];
					minX = x[j];
				}
				for (int k = j + 1; k < count; k++) {
					if ((mask >> k & 1) == 0)
						continue;
					Circle first = new Circle(new Point(x[j], 0), lengths[j]);
					Circle second = new Circle(new Point(x[k], 0), lengths[k]);
					Point[] intersect = first.intersect(second);
					if (intersect.length == 0)
						continue;
					Point interesting = intersect[0];
					if (interesting.y > 0 && intersect.length == 2)
						interesting = intersect[1];
					if (interesting.x > Math.min(x[j], x[k]) && interesting.x < Math.max(x[j], x[k]) && interesting.y > minY) {
						minY = interesting.y;
						minX = interesting.x;
					}
				}
			}
			xx[2 * i] = minX;
			yy[2 * i] = minY;
			if (i != count - 1) {
				mask -= 1 << (cuts[i] - 1);
				minY = Double.NEGATIVE_INFINITY;
				int index = -1;
				for (int j = 0; j < count; j++) {
					if ((mask >> j & 1) == 0)
						continue;
					double curY = -Math.sqrt(lengths[j] * lengths[j] - (minX - x[j]) * (minX - x[j]));
					if (curY > minY) {
						minY = curY;
						index = j;
					}
				}
				xx[2 * i + 1] = minX;
				yy[2 * i + 1] = minY;
				hangedBy[2 * i + 1] = index;
			}
		}
		double[] time = IOUtils.readDoubleArray(in, queryCount);
		double passed = 0;
		int i = 0;
		for (double t : time) {
			double next = 0;
			double alpha = 0;
			t -= passed;
			while (i < 2 * count - 2) {
				if (i % 2 == 0)
					next = Math.hypot(xx[i] - xx[i + 1], yy[i] - yy[i + 1]);
				else {
					alpha = Math.atan2(yy[i], xx[i] - x[hangedBy[i]]) - Math.atan2(yy[i + 1], xx[i + 1] - x[hangedBy[i]]);
					alpha = Math.abs(alpha);
					if (alpha > Math.PI)
						alpha = 2 * Math.PI - alpha;
					next = lengths[hangedBy[i]] * alpha;
				}
				if (next == 0) {
					i++;
					continue;
				}
				if (next < t) {
					passed += next;
					t -= next;
					i++;
				} else
					break;
			}
			if (i == 2 * count - 2) {
				out.printFormat("%.10f %.10f\n", xx[i], yy[i]);
				continue;
			}
			double xxx;
			double yyy;
			if (i % 2 == 0) {
				xxx = (xx[i] * (next - t) + xx[i + 1] * t) / next;
				yyy = (yy[i] * (next - t) + yy[i + 1] * t) / next;
			} else {
				double delta = alpha * t / next;
				double angle = Math.atan2(yy[i], xx[i] - x[hangedBy[i]]);
				if (xx[i] < x[hangedBy[i]])
					angle += delta;
				else
					angle -= delta;
				xxx = x[hangedBy[i]] + lengths[hangedBy[i]] * Math.cos(angle);
				yyy = lengths[hangedBy[i]] * Math.sin(angle);
			}
			out.printFormat("%.10f %.10f\n", xxx, yyy);
		}
	}
}
