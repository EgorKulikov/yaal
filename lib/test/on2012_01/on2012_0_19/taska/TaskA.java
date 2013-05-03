package on2012_01.on2012_0_19.taska;



import net.egork.misc.ArrayUtils;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] toSchool = IOUtils.readIntArray(in, count + 1);
        int[] from = new int[count];
        int[] to = new int[count];
        int[] distance = new int[count];
        IOUtils.readIntArrays(in, from, to, distance);
        int[][] graph = GraphUtils.buildGraph(count + 1, from, to);
        int[] minDistance = new int[count + 1];
        go(0, -1, 0, graph, from, to, distance, minDistance);
        int total = (int) (2 * ArrayUtils.sumArray(distance));
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= count; i++)
            answer = Math.min(answer, total - minDistance[i] + toSchool[i]);
        out.printLine(answer);
	}

    private void go(int vertex, int last, int curDistance, int[][] graph, int[] from, int[] to, int[] distance, int[] minDistance) {
        minDistance[vertex] = curDistance;
        for (int i : graph[vertex]) {
            int other = GraphUtils.otherVertex(vertex, from[i], to[i]);
            if (other != last)
                go(other, vertex, curDistance + distance[i], graph, from, to, distance, minDistance);
        }
    }
}
