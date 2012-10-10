package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.WeightedFlowEdge;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int participantCount = in.readInt();
		int taskCount = in.readInt();
		String[] participants = IOUtils.readStringArray(in, participantCount);
		String[] tasks = IOUtils.readStringArray(in, taskCount);
		int count = in.readInt();
		String[] participantMask = new String[count];
		String[] taskMask = new String[count];
		int[] points = new int[count];
		for (int i = 0; i < count; i++) {
			String mask = in.readString();
			String[] tokens = mask.split("-");
			participantMask[i] = tokens[0].replace('?', '.');
			taskMask[i] = tokens[1].replace('?', '.');
			points[i] = Integer.parseInt(tokens[2]);
		}
		boolean[][] participantMatches = new boolean[count][participantCount];
		boolean[][] taskMatches = new boolean[count][taskCount];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < participantCount; j++)
				participantMatches[i][j] = participants[j].matches(participantMask[i]);
			for (int j = 0; j < taskCount; j++)
				taskMatches[i][j] = tasks[j].matches(taskMask[i]);
		}
		int goodCount = in.readInt();
		boolean[] isGood = new boolean[participantCount];
		for (int i = 0; i < goodCount; i++)
			isGood[in.readInt() - 1] = true;
		int source = count + participantCount * taskCount;
		int sink = source + 1;
		Graph graph = new Graph(source + 2);
		for (int i = 0; i < count; i++) {
			graph.add(new WeightedFlowEdge(source, i, 0, 1));
			for (int j = 0; j < participantCount; j++) {
				for (int k = 0; k < taskCount; k++) {
					if (participantMatches[i][j] && taskMatches[i][k])
						graph.add(new WeightedFlowEdge(i, count + j * taskCount + k, isGood[j] ? -points[i] : 0, 1));
				}
			}
		}
		for (int i = 0; i < participantCount; i++) {
			for (int j = 0; j < taskCount; j++)
				graph.add(new WeightedFlowEdge(count + i * taskCount + j, sink, 0, 1));
		}
		out.printLine(-GraphAlgorithms.minCostMaxFlow(graph, source, sink).first);
	}
}
