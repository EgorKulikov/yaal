import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int result;
		@SuppressWarnings({"unchecked"}) final
		Set<Integer>[] incoming = new Set[rowCount * columnCount];
		for (int i = 0; i < rowCount * columnCount; i++)
			incoming[i] = new HashSet<Integer>();
		int[] firstDelta = new int[rowCount * columnCount];
		int[] secondDelta = new int[rowCount * columnCount];
		for (int j = 0; j < rowCount; j++) {
			for (int k = 0; k < columnCount; k++) {
				int index = j * columnCount + k;
				int firstHorizontalDelta = rowCount * columnCount - 1;
				int secondHorizontalDelta = 1;
				int firstVerticalDelta = rowCount * columnCount - columnCount;
				int secondVerticalDelta = columnCount;
				if (k == 0)
					firstHorizontalDelta = columnCount - 1;
				else if (k == columnCount - 1)
					secondHorizontalDelta = rowCount * columnCount - columnCount + 1;
				switch (map[j][k]) {
					case '-':
						firstDelta[index] = firstHorizontalDelta;
						secondDelta[index] = secondHorizontalDelta;
						break;
					case '/':
						firstDelta[index] = secondHorizontalDelta + firstVerticalDelta;
						secondDelta[index] = firstHorizontalDelta + secondVerticalDelta;
						break;
					case '|':
						firstDelta[index] = firstVerticalDelta;
						secondDelta[index] = secondVerticalDelta;
						break;
					default:
						firstDelta[index] = firstHorizontalDelta + firstVerticalDelta;
						secondDelta[index] = secondHorizontalDelta + secondVerticalDelta;
						break;
				}
				int first = (index + firstDelta[index]) % (rowCount * columnCount);
				incoming[first].add(index);
				int second = (index + secondDelta[index]) % (rowCount * columnCount);
				incoming[second].add(index);
			}
		}
		NavigableSet<Integer> queue = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int value = incoming[o1].size() - incoming[o2].size();
				if (value != 0)
					return value;
				return o1 - o2;
			}
		});
		for (int i = 0; i < rowCount * columnCount; i++)
			queue.add(i);
		out.print("Case #" + testNumber + ": ");
		while (!queue.isEmpty() && incoming[queue.first()].size() <= 1) {
			if (incoming[queue.first()].isEmpty()) {
				out.println(0);
				return;
			}
			int index = queue.pollFirst();
			int source = incoming[index].iterator().next();
			int other;
			if ((source + firstDelta[source]) % (rowCount * columnCount) == index)
				other = (source + secondDelta[source]) % (rowCount * columnCount);
			else
				other = (source + firstDelta[source]) % (rowCount * columnCount);
			incoming[index].remove(source);
			queue.remove(other);
			incoming[other].remove(source);
			queue.add(other);
		}
		int[][] edges = new int[rowCount * columnCount][2];
		boolean[] visited = new boolean[rowCount * columnCount];
		Arrays.fill(visited, true);
		for (int index : queue) {
			visited[index] = false;
			int curIndex = 0;
			for (int source : incoming[index]) {
				int other;
				if ((source + firstDelta[source]) % (rowCount * columnCount) == index)
					other = (source + secondDelta[source]) % (rowCount * columnCount);
				else
					other = (source + firstDelta[source]) % (rowCount * columnCount);
				edges[index][curIndex++] = other;
			}
		}
		result = 1;
		for (int i = 0; i < rowCount * columnCount; i++) {
			if (visited[i])
				continue;
			result *= 2;
			result %= 1000003;
			int current = edges[i][0];
			int last = i;
			while (current != i) {
				visited[current] = true;
				int next = edges[current][0] + edges[current][1] - last;
				last = current;
				current = next;
			}
		}
		out.println(result);
//		out.println("Case #" + testNumber + ": " + (result % 1000003));
	}
}

