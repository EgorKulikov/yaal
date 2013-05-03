package on2013_02.on2013_02_16_Bayan_Contest_Final.TheWearyButcher;



import net.egork.misc.ArrayUtils;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheWearyButcher {
	int width, height, count;
	Segment[] segments;
	Line[] perpendiculars;
	double[][] result;
	Line[] edges;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		width = in.readInt();
		height = in.readInt();
		count = in.readInt();
		Point[] points = new Point[count];
		segments = new Segment[count];
		for (int i = 0; i < count; i++)
			points[i] = Point.readPoint(in);
		perpendiculars = new Line[count];
		for (int i = 0; i < count; i++) {
			segments[i] = new Segment(points[i], points[(i + 1) % count]);
			perpendiculars[i] = segments[i].line().perpendicular(segments[i].middle());
		}
		edges = new Line[4];
		edges[0] = new Point(0, 0).line(new Point(width, 0));
		edges[1] = new Point(0, 0).line(new Point(0, height));
		edges[2] = new Point(width, height).line(new Point(width, 0));
		edges[3] = new Point(width, height).line(new Point(0, height));
		result = new double[count * 2][count * 2];
		ArrayUtils.fill(result, -1);
		double answer = Double.POSITIVE_INFINITY;
		for (int i = 0; i < count; i++) {
			Line line = segments[i].line();
			double minPositive = Double.POSITIVE_INFINITY;
			double maxNegative = Double.NEGATIVE_INFINITY;
			for (Line edge : edges) {
				Point intersect = edge.intersect(line);
				if (intersect == null)
					continue;
				double value = perpendiculars[i].value(intersect);
				if (value > 0)
					minPositive = Math.min(minPositive, value);
				else
					maxNegative = Math.max(maxNegative, value);
			}
			answer = Math.min(answer, go(i, i + count) + minPositive - maxNegative);
		}
		out.printFormat("%.2f\n", answer * 100);
    }

	private double go(int from, int to) {
		if (from >= count) {
			from -= count;
			to -= count;
		}
		if (result[from][to] != -1)
			return result[from][to];
		if (from == to - 1)
			return result[from][to] = 0;
		result[from][to] = Double.POSITIVE_INFINITY;
		int realTo = to;
		if (to >= count)
			realTo -= count;
		for (int j = from + 1; j < to; j++) {
			int i = j;
			if (i >= count)
				i -= count;
			double current = go(from, j) + go(j, to);
			Line line = segments[i].line();
			double minPositive = Double.POSITIVE_INFINITY;
			double maxNegative = Double.NEGATIVE_INFINITY;
			for (Line edge : edges) {
				Point intersect = edge.intersect(line);
				if (intersect == null)
					continue;
				double value = perpendiculars[i].value(intersect);
				if (value > 0)
					minPositive = Math.min(minPositive, value);
				else
					maxNegative = Math.max(maxNegative, value);
			}
			Point intersect = segments[from].line().intersect(line);
			if (intersect == null)
				continue;
			double value = perpendiculars[i].value(intersect);
			if (value > 0)
				minPositive = Math.min(minPositive, value);
			else
				maxNegative = Math.max(maxNegative, value);
			intersect = segments[realTo].line().intersect(line);
			if (intersect == null)
				continue;
			value = perpendiculars[i].value(intersect);
			if (value > 0)
				minPositive = Math.min(minPositive, value);
			else
				maxNegative = Math.max(maxNegative, value);
			current += minPositive - maxNegative;
			result[from][to] = Math.min(result[from][to], current);
		}
		return result[from][to];
	}
}
