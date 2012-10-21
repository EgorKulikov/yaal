package on2012_01.on2012_0_14.alliance;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Alliance {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(vertexCount);
		for (int i = 0; i < edgeCount; i++)
			setSystem.join(from[i], to[i]);
		int[] vertices = new int[vertexCount];
		for (int i = 0; i < vertexCount; i++)
			vertices[setSystem.get(i)]++;
		int[] edges = new int[vertexCount];
		for (int i : from)
			edges[setSystem.get(i)]++;
		long answer = 1;
		for (int i = 0; i < vertexCount; i++) {
			if (vertices[i] == 0)
				continue;
			if (vertices[i] == edges[i] + 1)
				answer *= vertices[i];
			else if (vertices[i] == edges[i])
				answer *= 2;
			else {
				out.printLine(0);
				return;
			}
			answer %= 1000000007;
		}
		out.printLine(answer);
	}
}
