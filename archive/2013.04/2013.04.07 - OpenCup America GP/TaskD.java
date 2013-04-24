package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int countJobs = in.readInt();
		int countStudents = in.readInt();
		if (countJobs == 0 && countStudents == 0)
			throw new UnknownError();
		Graph<String> graph = new Graph<String>();
		String source = "source";
		String sink = "sink";
		String[] jobs = new String[countJobs];
		for (int i = 0; i < countJobs; i++) {
			int capacity = in.readInt();
			jobs[i] = "job " + i;
			graph.addFlowEdge(jobs[i], sink, capacity);
		}
		for (int i = 0; i < countStudents; i++) {
			int year = in.readInt();
			String student = "student " + i;
			graph.addFlowEdge(source, student, 1);
			for (int j = 0; j < 4; j++)
				graph.addFlowWeightedEdge(student, jobs[in.readInt()], -4 * year + j, 1);
		}
		out.printLine(-new MinCostFlow<String>(graph, source, sink, true).minCostMaxFlow().first);
    }
}
