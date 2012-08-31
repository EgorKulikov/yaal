package on2012_01.on2012_0_31.monsterfarm;



import net.egork.misc.MiscUtils;

import java.util.Arrays;

public class MonsterFarm {
	private int[][] graph;
	private long[] count;

	public int numMonsters(String[] transforms) {
		graph = new int[transforms.length][];
		for (int i = 0; i < transforms.length; i++) {
			graph[i] = MiscUtils.getIntArray(transforms[i]);
			MiscUtils.decreaseByOne(graph[i]);
		}
		count = new long[transforms.length];
		Arrays.fill(count, -2);
		return (int) go(0, 0L, 0L);
	}

	private long go(int vertex, long onPath, long singleOnPath) {
		if ((onPath >> vertex & 1) == 1) {
			if ((singleOnPath >> vertex & 1) == 1)
				return 1;
			else
				return -1;
		}
		if (count[vertex] != -2)
			return count[vertex];
		count[vertex] = 0;
		onPath += 1L << vertex;
		singleOnPath = graph[vertex].length == 1 ? singleOnPath + (1L << vertex) : 0;
		for (int i : graph[vertex]) {
			long result = go(i, onPath, singleOnPath);
			if (result == -1)
				return count[vertex] = -1;
			count[vertex] += result;
		}
		return count[vertex] %= ((long)1e9 + 7);
	}

}
