package Timus.Part3;

import net.egork.geometry.GeometryUtils;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.WeightedEdge;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.List;

public class Task1205 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		double walkSpeed = in.readDouble();
		double metroSpeed = in.readDouble();
		int stationCount = in.readInt();
		double[] x = new double[stationCount];
		double[] y = new double[stationCount];
		in.readDoubleArrays(x, y);
		Graph graph = new BidirectionalGraph(stationCount + 2);
		double multiplier = 10000000000.;
		while (true) {
			int from = in.readInt();
			int to = in.readInt();
			if (from == 0 && to == 0)
				break;
			graph.add(new WeightedEdge(from, to,
				(long) (GeometryUtils.fastHypot(x[from - 1] - x[to - 1], y[from - 1] - y[to - 1]) * multiplier / metroSpeed)));
		}
		double ax = in.readDouble();
		double ay = in.readDouble();
		double bx = in.readDouble();
		double by = in.readDouble();
		for (int i = 0; i < stationCount; i++) {
			graph.add(new WeightedEdge(0, i + 1, (long) (GeometryUtils.fastHypot(x[i] - ax, y[i] - ay) * multiplier / walkSpeed)));
			graph.add(new WeightedEdge(stationCount + 1, i + 1, (long) (GeometryUtils.fastHypot(x[i] - bx, y[i] - by) * multiplier / walkSpeed)));
			for (int j = i + 1; j < stationCount; j++) {
				graph.add(new WeightedEdge(i + 1, j + 1, (long) (GeometryUtils.fastHypot(x[i] - x[j], y[i] - y[j]) * multiplier / walkSpeed)));
			}
		}
		graph.add(new WeightedEdge(stationCount + 1, 0, (long) (GeometryUtils.fastHypot(ax - bx, ay - by) * multiplier / walkSpeed)));
		GraphAlgorithms.DistanceResult result = GraphAlgorithms.leviteAlgorithm(graph, 0);
		out.printf("%.9f\n", result.getDistances()[stationCount + 1] / multiplier);
		List<Integer> path = MiscUtils.getPath(result.getLast(), stationCount + 1);
		if (path.size() == 2) {
			out.println(0);
			return;
		}
		out.print((path.size() - 2) + " ");
		IOUtils.printCollection(path.subList(1, path.size() - 1), out);
	}
}

