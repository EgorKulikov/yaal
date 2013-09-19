package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	int[] color;
	Graph graph;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		double[] x = new double[count];
		double[] y = new double[count];
		IOUtils.readDoubleArrays(in, x, y);
		graph = new BidirectionalGraph(count);
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				if (Math.hypot(x[i] - x[j], y[i] - y[j]) <= 20 + GeometryUtils.epsilon)
					graph.addSimpleEdge(i, j);
			}
		}
		color = new int[count];
		Arrays.fill(color, -1);
		int answer = go(0, 0);
		out.printLine("The towers in case", testNumber, "can be covered in", answer, "frequencies.");
    }

	private int go(int current, int used) {
		if (current == graph.vertexCount())
			return used;
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i <= used; i++) {
			boolean valid = true;
			for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
				int next = graph.destination(j);
				if (color[next] == i) {
					valid = false;
					break;
				}
			}
			if (!valid)
				continue;
			color[current] = i;
			answer = Math.min(answer, go(current + 1, Math.max(used, i + 1)));
			color[current] = -1;
		}
		return answer;
	}
}
