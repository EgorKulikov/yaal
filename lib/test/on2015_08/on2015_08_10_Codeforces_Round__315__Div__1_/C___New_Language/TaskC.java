package on2015_08.on2015_08_10_Codeforces_Round__315__Div__1_.C___New_Language;



import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.StronglyConnectedComponents;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] type = in.readString().toCharArray();
		int n = in.readInt();
		int m = in.readInt();
		int[] pos1 = new int[m];
		char[] t1 = new char[m];
		int[] pos2 = new int[m];
		char[] t2 = new char[m];
		for (int i = 0; i < m; i++) {
			pos1[i] = in.readInt();
			t1[i] = in.readCharacter();
			pos2[i] = in.readInt();
			t2[i] = in.readCharacter();
		}
		char[] s = IOUtils.readCharArray(in, n);
		MiscUtils.decreaseByOne(pos1, pos2);
		char[] nextVowel = new char[type.length];
		char[] nextConsonant = new char[type.length];
		char vowel = 0;
		char consonant = 0;
		for (int i = type.length - 1; i >= 0; i--) {
			nextVowel[i] = vowel;
			nextConsonant[i] = consonant;
			if (type[i] == 'V') {
				vowel = (char) ('a' + i);
			} else {
				consonant = (char) ('a' + i);
			}
		}
		if (vowel == 0) {
			for (int i = 0; i < m; i++) {
				if (t1[i] == 'C' && t2[i] == 'V') {
					out.printLine(-1);
					return;
				}
			}
			out.printLine(s);
			return;
		}
		if (consonant == 0) {
			for (int i = 0; i < m; i++) {
				if (t1[i] == 'V' && t2[i] == 'C') {
					out.printLine(-1);
					return;
				}
			}
			out.printLine(s);
			return;
		}
		boolean[][][] relation = new boolean[n + 1][2 * n][2 * n];
		Graph graph = new Graph(2 * n);
		for (int j = 0; j < m; j++) {
			int from = pos1[j];
			int revTo = n + pos1[j];
			if (t1[j] == 'V') {
				from += n;
				revTo -= n;
			}
			int to = pos2[j];
			int revFrom = n + pos2[j];
			if (t2[j] == 'V') {
				to += n;
				revFrom -= n;
			}
			graph.addSimpleEdge(from, to);
			graph.addSimpleEdge(revFrom, revTo);
		}
		Pair<int[], Graph> kosaraju = StronglyConnectedComponents.kosaraju(graph);
		boolean[][] edges = new boolean[kosaraju.second.vertexCount()][kosaraju.second.vertexCount()];
		for (int i = 0; i < edges.length; i++) {
			edges[i][i] = true;
			for (int j = kosaraju.second.firstOutbound(i); j != -1; j = kosaraju.second.nextOutbound(j)) {
				edges[i][kosaraju.second.destination(j)] = true;
			}
		}
		for (int i = 0; i < 2 * n; i++) {
			if (i < n && kosaraju.first[i] == kosaraju.first[i + n]) {
				out.printLine(-1);
				return;
			}
			for (int j = 0; j < 2 * n; j++) {
				if (edges[kosaraju.first[i]][kosaraju.first[j]]) {
					relation[0][i][j] = true;
				}
			}
		}
		for (int i = 0; i < 2 * n; i++) {
			for (int j = 0; j < 2 * n; j++) {
				for (int k = 0; k < 2 * n; k++) {
					relation[0][j][k] |= relation[0][j][i] && relation[0][i][k];
				}
			}
		}
		int start = n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n; j++) {
				for (int k = 0; k < 2 * n; k++) {
					relation[i + 1][j][k] = relation[i][j][k];
				}
			}
			if (type[s[i] - 'a'] == 'C') {
				if (relation[i + 1][i][n + i]) {
					start = i;
					break;
				}
				updateConsonant(n, relation[i + 1], i);
			} else {
				if (relation[i + 1][n + i][i]) {
					start = i;
					break;
				}
				updateVowel(n, relation[i + 1], i);
			}
		}
		for (int i = start; i >= 0; i--) {
			if (i == n) {
				out.printLine(s);
				return;
			}
			int value = Integer.MAX_VALUE;
			if (i == n || nextConsonant[s[i] - 'a'] != 0) {
				boolean compatible = !relation[i][i][n + i];
				if (compatible) {
					value = i == n ? 0 : nextConsonant[s[i] - 'a'];
				}
			}
			if (i != n && nextVowel[s[i] - 'a'] != 0) {
				boolean compatible = !relation[i][n + i][i];
				if (compatible) {
					value = Math.min(value, nextVowel[s[i] - 'a']);
				}
			}
			if (value != Integer.MAX_VALUE) {
				boolean[][] current = relation[i];
				if (i != n) {
					s[i] = (char) (value);
					if (type[value - 'a'] == 'C') {
						updateConsonant(n, current, i);
					} else {
						updateVowel(n, current, i);
					}
				}
				for (int k = i + 1; k < n; k++) {
					value = Integer.MAX_VALUE;
					if (!current[k][n + k]) {
						value = type[0] == 'C' ? 'a' : nextConsonant[0];
					}
					if (!current[n + k][k]) {
						value = Math.min(value, type[0] == 'V' ? 'a' : nextVowel[0]);
					}
					s[k] = (char) (value);
					if (type[value - 'a'] == 'C') {
						updateConsonant(n, current, k);
					} else {
						updateVowel(n, current, k);
					}
				}
				out.printLine(s);
				return;
			}
		}
		out.printLine(-1);
	}

	protected void updateVowel(int n, boolean[][] booleans, int i) {
		if (!booleans[i][n + i]) {
			for (int j = 0; j < 2 * n; j++) {
				for (int k = 0; k < 2 * n; k++) {
					booleans[j][k] |= booleans[j][i] && booleans[n + i][k];
				}
			}
		}
	}

	protected void updateConsonant(int n, boolean[][] booleans, int i) {
		if (!booleans[n + i][i]) {
			for (int j = 0; j < 2 * n; j++) {
				for (int k = 0; k < 2 * n; k++) {
					booleans[j][k] |= booleans[j][n + i] && booleans[i][k];
				}
			}
		}
	}

	protected boolean vowelCompatible(char[] type, int n, int m, int[] pos1, char[] t1, int[] pos2, char[] t2, char[] s, int i) {
		Graph graph = new Graph(2 * n);
		for (int j = 0; j < i; j++) {
			if (type[s[j] - 'a'] == 'V') {
				graph.addSimpleEdge(j, n + j);
			} else {
				graph.addSimpleEdge(n + j, j);
			}
		}
		graph.addSimpleEdge(i, i + n);
		for (int j = 0; j < m; j++) {
			int from = pos1[j];
			int revTo = n + pos1[j];
			if (t1[j] == 'V') {
				from += n;
				revTo -= n;
			}
			int to = pos2[j];
			int revFrom = n + pos2[j];
			if (t2[j] == 'V') {
				to += n;
				revFrom -= n;
			}
			graph.addSimpleEdge(from, to);
			graph.addSimpleEdge(revFrom, revTo);
		}
		return compatible(graph);
	}

	protected boolean consonantCompatible(char[] type, int n, int m, int[] pos1, char[] t1, int[] pos2, char[] t2, char[] s, int i) {
		Graph graph = new Graph(2 * n);
		for (int j = 0; j < i; j++) {
			if (type[s[j] - 'a'] == 'V') {
				graph.addSimpleEdge(j, n + j);
			} else {
				graph.addSimpleEdge(n + j, j);
			}
		}
		if (i != n) {
			graph.addSimpleEdge(n + i, i);
		}
		for (int j = 0; j < m; j++) {
			int from = pos1[j];
			int revTo = n + pos1[j];
			if (t1[j] == 'V') {
				from += n;
				revTo -= n;
			}
			int to = pos2[j];
			int revFrom = n + pos2[j];
			if (t2[j] == 'V') {
				to += n;
				revFrom -= n;
			}
			graph.addSimpleEdge(from, to);
			graph.addSimpleEdge(revFrom, revTo);
		}
		return compatible(graph);
	}

	private boolean compatible(Graph graph) {
		int[] color = StronglyConnectedComponents.kosaraju(graph).first;
		int n = graph.vertexCount() >> 1;
		for (int i = 0; i < n; i++) {
			if (color[i] == color[i + n]) {
				return false;
			}
		}
		return true;
	}
}
