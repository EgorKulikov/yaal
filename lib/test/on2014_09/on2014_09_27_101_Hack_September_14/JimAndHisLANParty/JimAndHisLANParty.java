package on2014_09.on2014_09_27_101_Hack_September_14.JimAndHisLANParty;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intervaltree.LCA;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JimAndHisLANParty {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int gameCount = in.readInt();
		int edgeCount = in.readInt();
		int[] gameID = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(gameID);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count + 1);
		Graph graph = new Graph(count + 1);
		int[] addedToParent = new int[count + 1];
		int[][] parent = new int[20][count + 1];
		for (int i = 0; i < edgeCount; i++) {
			int first = setSystem.get(from[i]);
			int second = setSystem.get(to[i]);
			if (setSystem.join(first, second)) {
				int cParent = setSystem.get(first);
				int other = first + second - cParent;
				graph.addSimpleEdge(cParent, other);
				addedToParent[other] = i + 1;
				parent[0][other] = cParent;
			}
		}
		for (int i = 1; i <= count; i++) {
			if (setSystem.get(i) != setSystem.get(0)) {
				addedToParent[setSystem.get(i)] = i + edgeCount;
				graph.addSimpleEdge(0, setSystem.get(i));
				setSystem.join(i, 0);
			}
		}
		for (int i = 1; i < 20; i++) {
			for (int j = 0; j <= count; j++) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
			}
		}
		LCA lca = new LCA(graph);
		IntList[] players = new IntList[gameCount];
		for (int i = 0; i < gameCount; i++) {
			players[i] = new IntArrayList();
		}
		for (int i = 0; i < count; i++) {
			players[gameID[i]].add(i + 1);
		}

		for (int i = 0; i < gameCount; i++) {
			int cLca = players[i].get(0);
			for (int j : players[i].toArray()) {
				cLca = lca.getLCA(cLca, j);
			}
			if (cLca == 0) {
				out.printLine(-1);
				continue;
			}
			int result = 0;
			for (int j : players[i].toArray()) {
				if (j == cLca) {
					continue;
				}
				int up = lca.getLevel(j) - lca.getLevel(cLca) - 1;
				for (int k = 19; k >= 0; k--) {
					if (up >= (1 << k)) {
						j = parent[k][j];
						up -= 1 << k;
					}
				}
				result = Math.max(result, addedToParent[j]);
			}
			out.printLine(result);
		}
	}
}
