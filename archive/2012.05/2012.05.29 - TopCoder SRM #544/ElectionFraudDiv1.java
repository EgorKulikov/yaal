package net.egork;

public class ElectionFraudDiv1 {
	public int MinimumVoters(int[] percentages) {
		for (int i = 1; i <= 10000; i++) {
			int must = 0;
			int may = 0;
			boolean good = true;
			for (int j : percentages) {
				int curMust = Math.max(((2 * j - 1) * i + 199) / 200, 0);
				int curMay = Math.min(((2 * j + 1) * i - 1) / 200, i);
				if (curMust > curMay)
					good = false;
				must += curMust;
				may += curMay;
			}
			if (good && must <= i && i <= may)
				return i;
		}
		return -1;
	}


}

