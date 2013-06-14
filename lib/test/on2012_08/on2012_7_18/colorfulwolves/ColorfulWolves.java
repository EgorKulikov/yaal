package on2012_08.on2012_7_18.colorfulwolves;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;

import java.util.Arrays;

public class ColorfulWolves {
	public int getmin(String[] colormap) {
		int count = colormap.length;
		boolean[][] canChange = new boolean[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				canChange[i][j] = colormap[i].charAt(j) == 'Y';
		}
		int[][] graph = new int[count][];
		for (int i = 0; i < count; i++) {
			graph[i] = new int[CollectionUtils.count(Array.wrap(canChange[i]), true)];
			int index = 0;
			for (int j = 0; j < count; j++) {
				if (canChange[i][j])
					graph[i][index++] = j;
			}
		}
		int[] result = new int[count];
		Arrays.fill(result, 0, count - 1, Integer.MAX_VALUE / 2);
		while (true) {
			boolean updated = false;
			for (int i = 0; i < count; i++) {
				for (int j = 0; j < graph[i].length; j++) {
					int current = result[graph[i][j]] + j;
					if (current < result[i]) {
						result[i] = current;
						updated = true;
					}
				}
			}
			if (!updated)
				break;
		}
		if (result[0] == Integer.MAX_VALUE / 2)
			return -1;
		return result[0];
	}


}

