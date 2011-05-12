import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.WeightedFlowEdge;

import java.util.Arrays;

public class SlimeXGrandSlimeAuto {
	public int travel(int[] cars, int[] districts, String[] roads, int inverseWalkSpeed, int inverseDriveSpeed) {
		int districtCount = roads.length;
		int[][] distances = new int[districtCount][districtCount];
		for (int i = 0; i < districtCount; i++) {
			for (int j = 0; j < districtCount; j++) {
				if (i == j)
					continue;
				char value = roads[i].charAt(j);
				distances[i][j] = value == '.' ? Integer.MAX_VALUE : Character.isDigit(value) ? value - '0' + 1 :
					Character.isLowerCase(value) ? value - 'a' + 11 : value - 'A' + 37;
			}
		}
		for (int i = 0; i < districtCount; i++) {
			for (int j = 0; j < districtCount; j++) {
				for (int k = 0; k < districtCount; k++) {
					if (distances[j][i] != Integer.MAX_VALUE && distances[i][k] != Integer.MAX_VALUE)
						distances[j][k] = Math.min(distances[j][k], distances[j][i] + distances[i][k]);
				}
			}
		}
		Graph graph = new Graph(districts.length + cars.length + 2);
		int result = 0;
		int source = districts.length + cars.length;
		for (int i = 0; i < districts.length; i++) {
			int last = i == 0 ? 0 : districts[i - 1];
			graph.add(new WeightedFlowEdge(source, i, 0, 1));
			graph.add(new WeightedFlowEdge(i, source + 1, distances[last][districts[i]] * inverseWalkSpeed, 1));
			for (int j = 0; j < cars.length; j++) {
				graph.add(new WeightedFlowEdge(i, districts.length + j, distances[last][cars[j]] * inverseWalkSpeed + distances[cars[j]][districts[i]] * inverseDriveSpeed, 1));
			}
		}
		for (int i = 0; i < cars.length; i++)
			graph.add(new WeightedFlowEdge(districts.length + i, source + 1, 0, 1));
		return (int) minCostFlow(graph, source, source + 1, true)[1];
	}

	public static final long INFINITY = Long.MAX_VALUE;

	public static class DistanceResult {
		public final long[] distance;
		public final Edge[] last;

		public DistanceResult(int vertexCount) {
			distance = new long[vertexCount];
			Arrays.fill(distance, INFINITY);
			last = new Edge[vertexCount];
		}
	}

	public static DistanceResult levite(Graph graph, int source, boolean ignoreEmptyCapacity) {
		int vertexCount = graph.getSize();
		DistanceResult result = new DistanceResult(vertexCount);
		boolean[] processed = new boolean[vertexCount];
		boolean[] queued = new boolean[vertexCount];
		Deque queue = new Deque(vertexCount);
		queue.add(source);
		queued[source] = true;
		result.distance[source] = 0;
		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			queued[vertex] = false;
			processed[vertex] = true;
			for (Edge edge : graph.getIncident(vertex)) {
				if (ignoreEmptyCapacity && edge.getCapacity() == 0)
					continue;
				int destination = edge.getDestination();
				long newDistance = edge.getWeight() + result.distance[vertex];
				if (result.distance[destination] > newDistance) {
					result.distance[destination] = newDistance;
					result.last[destination] = edge;
					if (processed[destination]) {
						processed[destination] = false;
						queued[destination] = true;
						queue.addFirst(destination);
					} else if (!queued[destination]) {
						queued[destination] = true;
						queue.add(destination);
					}
				}
			}
		}
		return result;
	}

	private static class Deque {
		private final int[] array;
		private int start = 0;
		private int finish = 0;

		private Deque(int capacity) {
			array = new int[capacity + 1];
		}

		public boolean isEmpty() {
			return start == finish;
		}

		public void add(int value) {
			array[finish++] = value;
			if (finish == array.length)
				finish = 0;
		}

		public int poll() {
			int value = array[start++];
			if (start == array.length)
				start = 0;
			return value;
		}

		public void addFirst(int value) {
			start--;
			if (start == -1)
				start = array.length - 1;
			array[start] = value;
		}
	}

	public static long[] minCostFlow(Graph graph, int source, int destination, boolean maxFlowRequired) {
		long totalFlow = 0;
		long totalCost = 0;
		while (true) {
			DistanceResult result = levite(graph, source, true);
			if (result.distance[destination] >= (maxFlowRequired ? INFINITY : 0))
				return new long[]{totalFlow, totalCost};
			long currentFlow = pushPath(result, source, destination);
			totalFlow += currentFlow;
			totalCost += result.distance[destination] * currentFlow;
		}
	}

	private static long pushPath(DistanceResult result, int source, int destination) {
		int currentVertex = destination;
		long currentFlow = INFINITY;
		while (currentVertex != source) {
			Edge edge = result.last[currentVertex];
			currentFlow = Math.min(currentFlow, edge.getCapacity());
			currentVertex = edge.getSource();
		}
		currentVertex = destination;
		while (currentVertex != source) {
			Edge edge = result.last[currentVertex];
			edge.pushFlow(currentFlow);
			currentVertex = edge.getSource();
		}
		return currentFlow;
	}

// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			SlimeXGrandSlimeAutoHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SlimeXGrandSlimeAutoHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class SlimeXGrandSlimeAutoHarness {
	public static void run_test(int casenum) {
		if (casenum != -1) {
			if (runTestCase(casenum) == -1)
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for (int i=0;; ++i) {
			int x = runTestCase(i);
			if (x == -1) {
				if (i >= 100) break;
				continue;
			}
			correct += x;
			++total;
		}
		
		if (total == 0) {
			System.err.println("No test cases run.");
		} else if (correct < total) {
			System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
		} else {
			System.err.println("All " + total + " tests passed!");
		}
	}
	
	static boolean compareOutput(int expected, int result) { return expected == result; }
	static String formatResult(int res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, int expected, int received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.println("PASSED");
			return 1;
		} else {
			System.err.println("FAILED");
			System.err.println("    Expected: " + formatResult(expected)); 
			System.err.println("    Received: " + formatResult(received)); 
			return 0;
		}
	}

	static int runTestCase(int casenum) {
		switch(casenum) {
		case 0: {
			int[] cars                = {1};
			int[] districts           = {2,3,0};
			String[] roads            = { "..0.", "...1", "0..2", ".12."};
			int inverseWalkSpeed      = 5;
			int inverseDriveSpeed     = 1;
			int expected__            = 36;

			return verifyCase(casenum, expected__, new SlimeXGrandSlimeAuto().travel(cars, districts, roads, inverseWalkSpeed, inverseDriveSpeed));
		}
		case 1: {
			int[] cars                = {1};
			int[] districts           = {2, 0};
			String[] roads            = { ".A.", "A.B", ".B."};
			int inverseWalkSpeed      = 2;
			int inverseDriveSpeed     = 1;
			int expected__            = 262;

			return verifyCase(casenum, expected__, new SlimeXGrandSlimeAuto().travel(cars, districts, roads, inverseWalkSpeed, inverseDriveSpeed));
		}
		case 2: {
			int[] cars                = {2,2};
			int[] districts           = {1,2,1};
			String[] roads            = { ".a4", "a..", "4.."};
			int inverseWalkSpeed      = 3;
			int inverseDriveSpeed     = 1;
			int expected__            = 95;

			return verifyCase(casenum, expected__, new SlimeXGrandSlimeAuto().travel(cars, districts, roads, inverseWalkSpeed, inverseDriveSpeed));
		}
		case 3: {
			int[] cars                = {1};
			int[] districts           = {2, 0};
			String[] roads            = { ".B.", "B.A", ".A."};
			int inverseWalkSpeed      = 2;
			int inverseDriveSpeed     = 1;
			int expected__            = 262;

			return verifyCase(casenum, expected__, new SlimeXGrandSlimeAuto().travel(cars, districts, roads, inverseWalkSpeed, inverseDriveSpeed));
		}
		case 4: {
			int[] cars                = {2,5,1,2};
			int[] districts           = {1,5,1,2,4};
			String[] roads            = { ".12.4.", "1....7", "2..3..", "..3..5", "4.....", ".7.5.."} ;
			int inverseWalkSpeed      = 53;
			int inverseDriveSpeed     = 37;
			int expected__            = 1259;

			return verifyCase(casenum, expected__, new SlimeXGrandSlimeAuto().travel(cars, districts, roads, inverseWalkSpeed, inverseDriveSpeed));
		}

		// custom cases

/*      case 5: {
			int[] cars                = ;
			int[] districts           = ;
			String[] roads            = ;
			int inverseWalkSpeed      = ;
			int inverseDriveSpeed     = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new SlimeXGrandSlimeAuto().travel(cars, districts, roads, inverseWalkSpeed, inverseDriveSpeed));
		}*/
/*      case 6: {
			int[] cars                = ;
			int[] districts           = ;
			String[] roads            = ;
			int inverseWalkSpeed      = ;
			int inverseDriveSpeed     = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new SlimeXGrandSlimeAuto().travel(cars, districts, roads, inverseWalkSpeed, inverseDriveSpeed));
		}*/
/*      case 7: {
			int[] cars                = ;
			int[] districts           = ;
			String[] roads            = ;
			int inverseWalkSpeed      = ;
			int inverseDriveSpeed     = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new SlimeXGrandSlimeAuto().travel(cars, districts, roads, inverseWalkSpeed, inverseDriveSpeed));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


