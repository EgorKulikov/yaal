package on2014_08.on2014_08_31_SnarkNews_Summer_Series_2014__Round_5.B___Crossroads;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] graph = in.readLine(false).toCharArray();
		if (graph.length <= 6) {
			out.printLine("YES");
			return;
		}
		if (graph.length == 10) {
			if (ArrayUtils.count(graph, '1') == 10) {
				out.printLine("NO");
			} else {
				out.printLine("YES");
			}
			return;
		}
		boolean[][] gr = new boolean[6][6];
		int id = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < i; j++) {
				gr[i][j] = gr[j][i] = graph[id++] == '1';
			}
		}
		for (int i = 0; i < 64; i++) {
			if (Integer.bitCount(i) == 3) {
				boolean bad = true;
				for (int j = 0; j < 6 && bad; j++) {
					if ((i >> j & 1) == 0) {
						continue;
					}
					for (int k = 0; k < 6; k++) {
						if ((i >> k & 1) == 0 && !gr[j][k]) {
							bad = false;
							break;
						}
					}
				}
				if (bad) {
					out.printLine("NO");
					return;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			if (check(gr, i)) {
				out.printLine("NO");
				return;
			}
			for (int j = 0; j < 6; j++) {
				if (gr[i][j]) {
					for (int k = j + 1; k < 6; k++) {
						if (gr[i][k] && !gr[j][k]) {
							gr[j][k] = gr[k][j] = true;
							if (check(gr, i)) {
								out.printLine("NO");
								return;
							}
							gr[j][k] = gr[k][j] = false;
						}
					}
				}
			}
		}
		out.printLine("YES");
    }

	private boolean check(boolean[][] gr, int excluded) {
		for (int i = 0; i < 6; i++) {
			if (i == excluded) {
				continue;
			}
			for (int j = i + 1; j < 6; j++) {
				if (j != excluded && !gr[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
