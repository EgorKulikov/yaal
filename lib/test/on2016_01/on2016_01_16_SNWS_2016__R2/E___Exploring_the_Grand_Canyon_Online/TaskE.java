package on2016_01.on2016_01_16_SNWS_2016__R2.E___Exploring_the_Grand_Canyon_Online;



import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Segment;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int v = in.readInt();
		Point[] canyon = new Point[v];
		for (int i = 0; i < v; i++) {
			canyon[i] = Point.readPoint(in);
		}
		int s = in.readInt();
		Point[] ends = new Point[s];
		for (int i = 0; i < s; i++) {
			ends[i] = Point.readPoint(in);
		}
		Polygon polygon = new Polygon(canyon);
		Graph graph = new BidirectionalGraph(v + s);
		Point[] all = Arrays.copyOf(canyon, v + s);
		System.arraycopy(ends, 0, all, v, s);
		for (int i = 0; i < v + s; i++) {
			for (int j = 0; j < i; j++) {
				Segment segment = new Segment(all[i], all[j]);
				if (i < v && j == i - 1 || i == v - 1 && j == 0) {
					graph.addWeightedEdge(i, j, Math.round(segment.length() * 1e9));
					continue;
				}
				boolean good = true;
				for (Segment side : polygon.sides()) {
					Point p = segment.intersect(side, true);
					if (p != null && !p.equals(all[i]) && !p.equals(all[j])) {
						good = false;
						break;
					}
				}
				if (good) {
					if (polygon.contains(segment.middle(), false)) {
						good = false;
					}
				}
				if (good) {
					graph.addWeightedEdge(i, j, Math.round(segment.length() * 1e9));
				}
			}
		}
		long answer = 0;
		for (int i = v; i < v + s; i++) {
			long[] dst = ShortestDistance.dijkstraAlgorithm(graph, i).first;
			for (int j = v; j < i; j++) {
				answer = Math.max(answer, dst[j]);
			}
		}
		out.printLine(answer / 1e9);
	}
}
