package net.egork;

import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Steeple {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int totalCount = in.readInt();
		List<Segment> horizontal = new ArrayList<Segment>();
		List<Segment> vertical = new ArrayList<Segment>();
		for (int i = 0; i < totalCount; i++) {
			int x0 = in.readInt();
			int y0 = in.readInt();
			int x1 = in.readInt();
			int y1 = in.readInt();
			Segment segment = new Segment(x0, y0, x1, y1);
			if (segment.isVertical())
				vertical.add(segment);
			else
				horizontal.add(segment);
		}
		Graph graph = new Graph(totalCount + 2);
		for (int i = 0; i < horizontal.size(); i++) {
			graph.add(new FlowEdge(totalCount, i, 1));
			for (int j = 0; j < vertical.size(); j++) {
				if (intersect(horizontal.get(i), vertical.get(j)))
					graph.add(new FlowEdge(i, horizontal.size() + j, 1));
			}
		}
		for (int j = 0; j < vertical.size(); j++)
			graph.add(new FlowEdge(horizontal.size() + j, totalCount + 1, 1));
		long answer = totalCount - GraphAlgorithms.dinic(graph, totalCount, totalCount + 1);
		out.printLine(answer);
	}

	private boolean intersect(Segment horizontal, Segment vertical) {
		return horizontal.x0 <= vertical.x0 && vertical.x0 <= horizontal.x1 &&
			vertical.y0 <= horizontal.y0 && horizontal.y0 <= vertical.y1;
	}
}

class Segment {
	final int x0;
	final int y0;
	final int x1;
	final int y1;

	Segment(int x0, int y0, int x1, int y1) {
		this.x0 = Math.min(x0, x1);
		this.y0 = Math.min(y0, y1);
		this.x1 = Math.max(x0, x1);
		this.y1 = Math.max(y0, y1);
	}

	boolean isVertical() {
		return x0 == x1;
	}
}