package on2014_10.on2014_10_19_Bayan_Elimination_2014.Water_Barrels;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class WaterBarrels {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine("Case #" + testNumber + ":");
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] height = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, height);
		int[] currentHeight = new int[count];
		boolean[] connected = new boolean[count];
		MiscUtils.decreaseByOne(from, to);
		connected[0] = true;
		int qConnected = 1;
		long time = 0;
		long[] answer = new long[count];
		while (qConnected < count) {
			int minHeight = Integer.MAX_VALUE;
			for (int i = 0; i < count; i++) {
				if (connected[i] && currentHeight[i] < minHeight) {
					minHeight = currentHeight[i];
				}
			}
			int qtyAtMin = 0;
			int nextJoinHeight = Integer.MAX_VALUE;
			for (int i = 0; i < count; i++) {
				if (connected[i]) {
					if (currentHeight[i] == minHeight) {
						qtyAtMin++;
					} else {
						nextJoinHeight = Math.min(nextJoinHeight, currentHeight[i]);
					}
				}
			}
			int nextEdgeHeight = Integer.MAX_VALUE;
			int edgeId = -1;
			for (int i = 0; i < edgeCount; i++) {
				if ((connected[from[i]] ^ connected[to[i]]) && height[i] < nextEdgeHeight) {
					edgeId = i;
					nextEdgeHeight = height[i];
				}
			}
			int nextHeight = Math.min(nextEdgeHeight, nextJoinHeight);
			time += (long)(nextHeight - minHeight) * qtyAtMin;
			for (int i = 0; i < count; i++) {
				if (connected[i] && currentHeight[i] == minHeight) {
					currentHeight[i] = nextHeight;
				}
			}
			if (nextEdgeHeight == nextHeight) {
				if (!connected[from[edgeId]]) {
					answer[from[edgeId]] = time;
				} else {
					answer[to[edgeId]] = time;
				}
				connected[from[edgeId]] = connected[to[edgeId]] = true;
				qConnected++;
			}
		}
		out.printLine(answer);
    }
}
