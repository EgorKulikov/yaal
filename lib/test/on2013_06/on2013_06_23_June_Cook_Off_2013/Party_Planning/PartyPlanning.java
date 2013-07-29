package on2013_06.on2013_06_23_June_Cook_Off_2013.Party_Planning;



import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PartyPlanning {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] enemy = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(enemy);
		int[] who = ArrayUtils.createOrder(count);
		Graph graph = Graph.createGraph(count, enemy, who);
		int[] order = new int[count];
		long answer = 1;
		long[] taken = new long[count];
		long[] notTaken = new long[count];
		boolean[] processed = new boolean[count];
		boolean[] added = new boolean[count];
		for (int i = 0; i < count; i++) {
			if (processed[i])
				continue;
			int j = i;
			do {
				processed[j] = true;
				j = enemy[j];
			} while (!processed[j]);
			order[0] = j;
			int size = 1;
			added[j] = true;
			for (int k = 0; k < size; k++) {
				int l = order[k];
				for (Edge edge : graph.outbound(l)) {
					int next = edge.getDestination();
					if (!added[next]) {
						order[size++] = next;
						added[next] = true;
					}
				}
			}
			long result = 0;
			for (int k = -1; k <= enemy[j]; k += enemy[j] + 1) {
//				boolean done = false;
				for (int l = size - 1; l >= 0; l--) {
					int m = order[l];
					taken[m] = notTaken[m] = 1;
					for (Edge edge : graph.outbound(m)) {
						int next = edge.getDestination();
						if (next == j)
							continue;
//						if (next == enemy[j] && m == j && !done) {
//							done = true;
//							continue;
//						}
						taken[m] = taken[m] * notTaken[next] % MOD;
						notTaken[m] = notTaken[m] * (taken[next] + notTaken[next]) % MOD;
					}
					if (m == k)
						notTaken[m] = 0;
					else if (m == enemy[j])
						taken[m] = 0;
					processed[m] = true;
				}
				if (k == -1)
					result += taken[j] + notTaken[j];
				else
					result += notTaken[j];
			}
			answer = answer * result % MOD;
		}
		out.printLine(answer);
    }
}
