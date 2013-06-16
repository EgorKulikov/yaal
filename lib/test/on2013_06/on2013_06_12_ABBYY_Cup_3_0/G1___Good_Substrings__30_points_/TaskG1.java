package on2013_06.on2013_06_12_ABBYY_Cup_3_0.G1___Good_Substrings__30_points_;



import net.egork.misc.ArrayUtils;
import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG1 {
	int count;
	int[] min, max;
	int[][] ways;
	private SuffixAutomaton automaton;
	int length;
	StringBuilder all;
	int[] help;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		length = s.length();
		count = in.readInt();
		String[] t = new String[count];
		min = new int[count];
		max = new int[count];
		for (int i = 0; i < count; i++) {
			t[i] = in.readString();
			min[i] = in.readInt();
			max[i] = in.readInt();
		}
		all = new StringBuilder();
		all.append(s);
		all.append((char)count);
		for (int i = 0; i < count; i++) {
			all.append(t[i]);
			all.append((char)i);
		}
		automaton = new SuffixAutomaton(all);
		int x = 0;
		help = new int[automaton.size];
		for (int i = 0; i < all.length(); i++) {
			x = automaton.to[automaton.findEdge(x, all.charAt(i))];
			int xx = x;
			while (xx != -1 && help[xx] == 0) {
				help[xx] = i + 1;
				xx = automaton.link[xx];
			}
		}
		ways = new int[count + 1][automaton.size];
		ArrayUtils.fill(ways, -1);
		long answer = go(0);
		out.printLine(answer);
    }

	private long go(int vertex) {
		if (ways[0][vertex] != -1)
			return 0;
		ArrayUtils.fillColumn(ways, vertex, 0);
		int id = automaton.first[vertex];
		long result = 0;
		while (id != -1) {
			result += go(automaton.to[id]);
			if (automaton.label[id] <= count)
				ways[automaton.label[id]][vertex]++;
			else {
				for (int i = 0; i <= count; i++)
					ways[i][vertex] += ways[i][automaton.to[id]];
			}
			id = automaton.next[id];
		}
		if (vertex != 0) {
			boolean good = true;
			for (int i = 0; i < count; i++) {
				if (ways[i][vertex] < min[i] || ways[i][vertex] > max[i]) {
					good = false;
					break;
				}
			}
			long multiplier = Math.max(0, Math.min(automaton.length[vertex], length) - automaton.length[automaton.link[vertex]]);
			if (good && ways[count][vertex] != 0)
				result += multiplier;
		}
		return result;
	}
}
