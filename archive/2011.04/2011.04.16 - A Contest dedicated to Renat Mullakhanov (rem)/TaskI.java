package April2011.UVaAContestDedicatedToRenatMullakhanov;

import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskI implements Solver {
	private int[] divisors;

	public TaskI() {
		divisors = IntegerUtils.generateDivisorTable(500001);
	}

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int numberCount = in.readInt();
		int[] numbers = in.readIntArray(numberCount);
		Map<Integer, Integer> index = new HashMap<Integer, Integer>();
		for (int i = 0; i < numberCount; i++)
			index.put(numbers[i], i);
		int[] primality = new int[numberCount];
		Graph graph = new Graph(numberCount + 2);
		for (int i = 0; i < numberCount; i++) {
			int number = numbers[i];
			while (number != 1) {
				int divisor = divisors[number];
				primality[i]++;
				number /= divisor;
				while (number % divisor == 0) {
					number /= divisor;
					primality[i]++;
				}
			}
		}
		for (int i = 0; i < numberCount; i++) {
			int number = numbers[i];
			while (number != 1) {
				int divisor = divisors[number];
				number /= divisor;
				if (index.containsKey(numbers[i] / divisor)) {
					if (primality[i] % 2 == 0)
						graph.add(new FlowEdge(i, index.get(numbers[i] / divisor), 1));
					else
						graph.add(new FlowEdge(index.get(numbers[i] / divisor), i, 1));
				}
				while (number % divisor == 0)
					number /= divisor;
			}
			if (primality[i] % 2 == 0)
				graph.add(new FlowEdge(numberCount, i, 1));
			else
				graph.add(new FlowEdge(i, numberCount + 1, 1));
		}
		long result = numberCount - GraphAlgorithms.dinic(graph, numberCount, numberCount + 1);
		out.println("Case " + testNumber + ": " + result);
	}
}

