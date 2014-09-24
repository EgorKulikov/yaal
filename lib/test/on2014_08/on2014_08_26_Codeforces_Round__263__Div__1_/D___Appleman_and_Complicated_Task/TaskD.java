package on2014_08.on2014_08_26_Codeforces_Round__263__Div__1_.D___Appleman_and_Complicated_Task;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	int size;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		size = in.readInt();
		int count = in.readInt();
		int[] row = new int[count];
		int[] column = new int[count];
		int[] type = new int[count];
		for (int i = 0; i < count; i++) {
			row[i] = in.readInt() - 1;
			column[i] = in.readInt() - 1;
			type[i] = in.readCharacter() == 'o' ? 1 : 0;
		}
		int[] first = new int[count];
		int[] second = new int[count];
		int[] same = new int[count];
		for (int i = 0; i < count; i++) {
			if (row[i] + column[i] > size - 1) {
				row[i] = size - 1 - row[i];
				column[i] = size - 1 - column[i];
			}
			if (row[i] > column[i]) {
				int temp = row[i];
				row[i] = column[i];
				column[i] = temp;
			}
			first[i] = (column[i] - row[i]) >> 1;
			second[i] = (column[i] + row[i] + 2) >> 1;
			same[i] = (row[i] + column[i]) & 1;
		}
		int answer = 0;
		int even = solve(first, second, same, type, 0);
		if (even == -1) {
			out.printLine(0);
			return;
		}
		answer += even;
		int odd = solve(first, second, same, type, 1);
		if (odd == -1) {
			out.printLine(0);
			return;
		}
		answer += odd;
		out.printLine(IntegerUtils.power(2, answer, (long) (1e9 + 7)));
	}

	private int solve(int[] first, int[] second, int[] same, int[] type, int value) {
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem((size + 3) >> 1);
		for (int i = 0; i < first.length; i++) {
			if (same[i] == value && type[i] == 0) {
				setSystem.join(first[i], second[i]);
			}
		}
		Graph graph = new BidirectionalGraph((size + 3) >> 1);
		for (int i = 0; i < first.length; i++) {
			if (same[i] == value && type[i] == 1) {
				graph.addSimpleEdge(setSystem.get(first[i]), setSystem.get(second[i]));
			}
		}
		int result = -1;
		int[] color = new int[(size + 3) >> 1];
		for (int i = 0; i < ((size + 3) >> 1); i++) {
			if (2 * i + value >= size + 2 || setSystem.get(i) != i || color[i] != 0) {
				continue;
			}
			if (dfs(i, 1, graph, color)) {
				return -1;
			}
			result++;
		}
		return result;
	}

	private boolean dfs(int id, int newColor, Graph graph, int[] color) {
		if (color[id] != 0) {
			return color[id] != newColor;
		}
		color[id] = newColor;
		for (int i = graph.firstOutbound(id); i != -1; i = graph.nextOutbound(i)) {
			if (dfs(graph.destination(i), -newColor, graph, color)) {
				return true;
			}
		}
		return false;
	}
}
