package on2013_08.on2013_08_14_SnarkNews_Summer_Series_Round__3.TaskF;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Segment[] segments = new Segment[count + 2];
		int x = in.readInt();
		int y = in.readInt();
		int z = in.readInt();
		segments[count] = new Segment(x, y, z, x, y, z);
		x = in.readInt();
		y = in.readInt();
		z = in.readInt();
		segments[count + 1] = new Segment(x, y, z, x, y, z);
		for (int i = 0; i < count; i++) {
			int x0 = in.readInt();
			int y0 = in.readInt();
			int z0 = in.readInt();
			int x1 = in.readInt();
			int y1 = in.readInt();
			int z1 = in.readInt();
			segments[i] = new Segment(x0, y0, z0, x1, y1, z1);
		}
		Graph graph = new BidirectionalGraph(count + 2);
		for (int i = 0; i < count + 2; i++) {
			for (int j = i + 1; j < count + 2; j++)
				graph.addWeightedEdge(i, j, segments[i].distance(segments[j]));
		}
		out.printLine(ShortestDistance.dijkstraAlgorithm(graph, count, count + 1).first / 1e12);
    }

	static class Segment {
		double x0, y0, z0, x1, y1, z1;
		double length;
		double realLength;

		Segment(double x0, double y0, double z0, double x1, double y1, double z1) {
			this.x0 = x0;
			this.y0 = y0;
			this.z0 = z0;
			this.x1 = x1;
			this.y1 = y1;
			this.z1 = z1;
			length = distance(x0, y0, z0, x1, y1, z1);
			realLength = Math.sqrt(length);
		}

		private double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
			double dx = x0 - x1;
			double dy = y0 - y1;
			double dz = z0 - z1;
			return dx * dx + dy * dy + dz * dz;
		}


		public long distance(Segment segment) {
			double left = 0;
			double right = 1;
			for (int i = 0; i < 100; i++) {
				double midLeft = (left * 2 + right) / 3;
				double midRight = (right * 2 + left) / 3;
				double leftValue = calculate(midLeft, segment);
				double rightValue = calculate(midRight, segment);
				if (leftValue < rightValue)
					right = midRight;
				else
					left = midLeft;
			}
			double ratio = (left + right) / 2;
			return Math.round(Math.sqrt(calculate(ratio, segment)) * 1e12);
		}

		private double calculate(double ratio, Segment segment) {
			double x = x0 * ratio + x1 * (1 - ratio);
			double y = y0 * ratio + y1 * (1 - ratio);
			double z = z0 * ratio + z1 * (1 - ratio);
			double a = segment.length;
			double b = distance(x, y, z, segment.x0, segment.y0, segment.z0);
			double c = distance(x, y, z, segment.x1, segment.y1, segment.z1);
			if (b + a < c)
				return b;
			if (a + c < b)
				return c;
			if (a == 0)
				return b;
			a = segment.realLength;
			b = Math.sqrt(b);
			c = Math.sqrt(c);
			double p = (a + b + c) / 2;
			double s = p * (p - a) * (p - b) * (p - c);
			return 4 * s / segment.length;
		}
	}
}
