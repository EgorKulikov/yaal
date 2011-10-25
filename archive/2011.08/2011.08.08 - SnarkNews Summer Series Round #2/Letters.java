import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class Letters implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int godCount = in.readInt();
		String[] samples = IOUtils.readStringArray(in, count);
		int answer = 0;
		for (int i = 0; i < godCount; i++) {
			String god = in.readString();
			Graph graph = new Graph(count + 28);
			for (int j = 0; j < samples.length; j++) {
				graph.add(new FlowEdge(count + 26, j, 1));
				for (int k = 0; k < samples[j].length(); k++)
					graph.add(new FlowEdge(j, count + samples[j].charAt(k) - 'A', 1));
			}
			int[] letters = new int[26];
			for (char letter : god.toCharArray())
				letters[letter - 'A']++;
			for (int j = 0; j < 26; j++) {
				if (letters[j] != 0)
					graph.add(new FlowEdge(count + j, count + 27, letters[j]));
			}
			if (GraphAlgorithms.dinic(graph, count + 26, count + 27) == god.length())
				answer++;
		}
		out.println(answer);
	}
}

