package on2013_01.on2013_01_19_SnarkNews_Winter_Series_Round__3.Communications;



import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Communications {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int from = in.readInt() - 1;
		int to = in.readInt() - 1;
		Segment[][] islands = new Segment[count][];
		for (int i = 0; i < count; i++) {
			int vertexCount = in.readInt();
			Point[] points = new Point[vertexCount];
			for (int j = 0; j < vertexCount; j++)
				points[j] = Point.readPoint(in);
			islands[i] = new Segment[vertexCount];
			islands[i][0] = new Segment(points[vertexCount - 1], points[0]);
			for (int j = 1; j < vertexCount; j++)
				islands[i][j] = new Segment(points[j - 1], points[j]);
		}
		Graph graph = new BidirectionalGraph(count);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				double distance = Double.POSITIVE_INFINITY;
				for (Segment s1 : islands[i]) {
					for (Segment s2 : islands[j])
						distance = Math.min(distance, Math.min(s1.distance(s2.a), s2.distance(s1.a)));
				}
				graph.addWeightedEdge(i, j, Math.round(distance * 1e9));
			}
		}
		out.printLine(ShortestDistance.dijkstraAlgorithm(graph, from, to).first / 1e9);
    }
}
