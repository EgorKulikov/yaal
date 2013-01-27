package net.egork;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CosmicBlocks {
	int[] level;
	int[] count;
	private int[] blockTypes;
	private int minWays;
	private int maxWays;
	Set<Integer> good = new HashSet<Integer>();
	Set<Integer> checked = new HashSet<Integer>();
	int[][] index;

	public int getNumOrders(int[] blockTypes, int minWays, int maxWays) {
		this.blockTypes = blockTypes;
		this.minWays = minWays;
		this.maxWays = maxWays;
		level = new int[blockTypes.length];
		count = new int[blockTypes.length];
		index = new int[blockTypes.length][blockTypes.length];
		int cur = 0;
		for (int i = 0; i < blockTypes.length; i++) {
			for (int j = 0; j < blockTypes.length; j++) {
				if (i != j)
					index[i][j] = 1 << (cur++);
			}
		}
		can = new int[1 << blockTypes.length][1 << blockTypes.length][];
		for (int i = 0; i < (1 << blockTypes.length); i++) {
			for (int j = 0; j < (1 << blockTypes.length); j++) {
				if ((i & j) == 0) {
					can[i][j] = new int[1 << (Integer.bitCount(i) * Integer.bitCount(j))];
				}
			}
		}
		go(0);
		return good.size();
	}

	private void go(int step) {
		if (step == blockTypes.length) {
			for (int i = 1; i < blockTypes.length; i++) {
				if (count[i] > count[i - 1])
					return;
			}
			go(1, 0);
			return;
		}
		for (int i = 0; i < blockTypes.length; i++) {
			level[step] = i;
			count[i] += blockTypes[step];
			go(step + 1);
			count[i] -= blockTypes[step];
		}
	}

	int[][][] can;
	int[] variants = new int[1 << 6];

	private void go(int step, int mask) {
		if (step == blockTypes.length || count[step] == 0) {
			if (checked.contains(mask))
				return;
			variants[0] = 1;
			for (int i = 1; i < (1 << blockTypes.length); i++) {
				variants[i] = 0;
				for (int j = 0; j < blockTypes.length; j++) {
					if ((i >> j & 1) == 1) {
						boolean can = true;
						for (int k = 0; k < blockTypes.length; k++) {
							if (j != k && (mask & index[j][k]) != 0 && (i >> k & 1) == 0) {
								can = false;
								break;
							}
						}
						if (can)
							variants[i] += variants[i - (1 << j)];
					}
				}
			}
			int last = (1 << blockTypes.length) - 1;
			if (variants[last] >= minWays && variants[last] <= maxWays)
				good.add(mask);
			checked.add(mask);
			return;
		}
		int downCount = 0;
		int upCount = 0;
		int downMask = 0;
		int upMask = 0;
		int[] up = new int[6];
		int[] down = new int[6];
		for (int i = 0; i < blockTypes.length; i++) {
			if (level[i] == step) {
				up[upCount++] = i;
				upMask += 1 << i;
			} else if (level[i] == step - 1) {
				down[downCount++] = i;
				downMask += 1 << i;
			}
		}
		int[] downDelta = new int[6];
		int[] upDelta = new int[6];
		for (int i = 0; i < (1 << (downCount * upCount)); i++) {
			if (can[downMask][upMask][i] != 0) {
				if (can[downMask][upMask][i] == 1) {
					int curMask = mask;
					for (int j = 0; j < downCount; j++) {
						for (int k = 0; k < upCount; k++) {
							if ((i >> (j * upCount + k) & 1) != 0)
								curMask += index[down[j]][up[k]];
						}
					}
					go(step + 1, curMask);
				}
				continue;
			}
			Arrays.fill(downDelta, 0);
			Arrays.fill(upDelta, 0);
			for (int j = 0; j < downCount; j++) {
				for (int k = 0; k < upCount; k++) {
					if ((i >> (j * upCount + k) & 1) == 1) {
						downDelta[j]++;
						upDelta[k]++;
					}
				}
			}
			boolean good = true;
			for (int j = 0; j < downCount; j++) {
				if (downDelta[j] > blockTypes[down[j]])
					good = false;
			}
			for (int j = 0; j < upCount; j++) {
				if (upDelta[j] > blockTypes[up[j]])
					good = false;
			}
			if (!good) {
				can[downMask][upMask][i] = -1;
				continue;
			}
			for (int j = 1; j < (1 << upCount); j++) {
				int curDownMask = 0;
				for (int k = 0; k < upCount; k++) {
					if ((j >> k & 1) == 1) {
						for (int l = 0; l < downCount; l++) {
							if ((i >> (l * upCount + k) & 1) == 1)
								curDownMask |= 1 << l;
						}
					}
				}
				int sumUp = 0;
				for (int k = 0; k < upCount; k++) {
					if ((j >> k & 1) == 1)
						sumUp += blockTypes[up[k]] - upDelta[k];
				}
				int sumDown = 0;
				for (int k = 0; k < downCount; k++) {
					if ((curDownMask >> k & 1) == 1)
						sumDown += blockTypes[down[k]] - downDelta[k];
				}
				if (sumUp > sumDown) {
					good = false;
					break;
				}
			}
			if (!good) {
				can[downMask][upMask][i] = -1;
				continue;
			}
			can[downMask][upMask][i] = 1;
			int curMask = mask;
			for (int j = 0; j < downCount; j++) {
				for (int k = 0; k < upCount; k++) {
					if ((i >> (j * upCount + k) & 1) != 0)
						curMask += index[down[j]][up[k]];
				}
			}
			go(step + 1, curMask);
		}
	}


}

