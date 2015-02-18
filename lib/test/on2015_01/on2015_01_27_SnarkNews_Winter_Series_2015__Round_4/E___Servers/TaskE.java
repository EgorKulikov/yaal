package on2015_01.on2015_01_27_SnarkNews_Winter_Series_2015__Round_4.E___Servers;



import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.StronglyConnectedComponents;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		long target = in.readLong();
		int[] source = new int[edgeCount];
		int[] destination = new int[edgeCount];
		IOUtils.readIntArrays(in, source, destination);
		MiscUtils.decreaseByOne(source, destination);
		Graph graph = Graph.createGraph(count, source, destination);
		long[] value = IOUtils.readLongArray(in, count);
		Pair<int[], Graph> pair = StronglyConnectedComponents.kosaraju(graph);
		long[] result = new long[pair.second.vertexCount()];
		for (int i = 0; i < count; i++) {
			result[pair.first[i]] = IntegerUtils.gcd(result[pair.first[i]], value[i]);
		}
		for (int i = 0; i < pair.second.vertexCount(); i++) {
			for (int j = pair.second.firstInbound(i); j != -1; j = pair.second.nextInbound(j)) {
				result[i] = IntegerUtils.gcd(result[i], result[pair.second.source(j)]);
			}
		}
		for (int i = 0; i < count; i++) {
			value[i] = result[pair.first[i]];
		}
		out.printLine(ArrayUtils.count(value, target));
	}
}
