package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		double time = in.readDouble();
		Point[] start = new Point[count];
		for (int i = 0; i < count; i++)
			start[i] = Point.readPoint(in);
		Point[] finish = new Point[count];
		for (int i = 0; i < count; i++)
			finish[i] = Point.readPoint(in);
		double left = 0;
		double right = 2 * Math.sqrt(2) / time;
		Segment[] sides = {
			new Segment(new Point(0, 0), new Point(1, 0)),
			new Segment(new Point(0, 0), new Point(0, 1)),
			new Segment(new Point(1, 1), new Point(1, 0)),
			new Segment(new Point(1, 1), new Point(0, 1)),
		};
		double[] enter = new double[count];
		double[] exit = new double[count];
		Arrays.fill(enter, Double.POSITIVE_INFINITY);
		Arrays.fill(exit, Double.POSITIVE_INFINITY);
		double[][] distance = new double[count][count];
		for (int i = 0; i < count; i++) {
			for (Segment side : sides) {
				enter[i] = Math.min(enter[i], side.distance(start[i]));
				exit[i] = Math.min(exit[i], side.distance(finish[i]));
			}
			for (int j = 0; j < count; j++)
				distance[i][j] = start[i].distance(finish[j]);
		}
		for (int i = 0; i < 15; i++) {
			double middle = (left + right) / 2;
			Graph graph = new Graph(2 * count + 2);
			for (int j = 0; j < count; j++) {
				graph.addFlowEdge(count * 2, j, 1);
				graph.addFlowEdge(j + count, 2 * count + 1, 1);
				for (int k = 0; k < count; k++) {
					double total = enter[j] + exit[k] + 2 * distance[j][k];
					if (total / middle < time)
						graph.addFlowEdge(j, k + count, 1);
				}
			}
			if (MaxFlow.dinic(graph, 2 * count, 2 * count + 1) == count)
				right = middle;
			else
				left = middle;
		}
		out.printLine((left + right) / 2);
    }
}
