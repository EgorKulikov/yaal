package net.egork;

public class MagicMolecule {
	long[] graph;
	int[] power;
	int count;

    public int maxMagicPower(int[] magicPower, String[] magicBond) {
		count = magicPower.length;
		graph = new long[count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (magicBond[i].charAt(j) == 'Y')
					graph[i] += 1L << j;
			}
			graph[i] += 1L << i;
		}
		power = magicPower;
		int result = go(0, (1L << count) - 1, count / 3);
		if (result < 0)
			result = -1;
		return result;
    }

	private int go(int step, long mask, int remaining) {
		if (remaining < 0)
			return Integer.MIN_VALUE;
		if (step == count)
			return 0;
		if ((mask >> step & 1) == 0)
			return go(step + 1, mask, remaining);
		mask -= 1L << step;
		long delta = mask - (mask & graph[step]);
		if (delta == 0)
			return go(step + 1, mask, remaining) + power[step];
		if (remaining == 0)
			return Integer.MIN_VALUE;
		int deltaSize = Long.bitCount(delta);
		if (deltaSize == 1) {
			int other = Long.bitCount(delta - 1);
			if ((graph[other] & mask) == mask)
				return Math.max(power[step], power[other]) + go(step + 1, mask - (1L << other), remaining - 1);
			return Math.max(power[step] + go(step + 1, mask - (1L << other), remaining - 1), go(step + 1, mask & graph[other], remaining - 1 - Long.bitCount(mask - (mask & graph[other]))));
		}
		return Math.max(go(step + 1, mask, remaining - 1), go(step + 1, mask & graph[step], remaining - deltaSize) + power[step]);
	}
}
