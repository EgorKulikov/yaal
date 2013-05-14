package net.egork;

public class KingdomAndTrees {
	public int minLevel(int[] heights) {
		int left = 0;
		int right = 1000000000;
		while (left < right) {
			int middle = (left + right) / 2;
			int curMinHeight = 0;
			boolean good = true;
			for (int i : heights) {
				if (i + middle <= curMinHeight) {
					good = false;
					break;
				}
				curMinHeight = Math.max(curMinHeight + 1, i - middle);
			}
			if (good)
				right = middle;
			else
				left = middle + 1;
		}
		return left;
	}


}

