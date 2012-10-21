package net.egork;

public class StrangeDictionary2 {
	int[][] move;
	int[] countStable;

	public double[] getProbabilities(String[] words) {
		int count = words.length;
		int length = words[0].length();
		int maskCount = 1 << count;
		move = new int[maskCount][length];
		countStable = new int[maskCount];
		for (int i = 0; i < maskCount; i++) {
			for (int j = 0; j < length; j++) {
				int curMask = 0;
				int curMin = 'z' + 1;
				for (int k = 0; k < count; k++) {
					if ((i >> k & 1) == 1) {
						if (words[k].charAt(j) < curMin) {
							curMin = words[k].charAt(j);
							curMask = 1 << k;
						} else if (words[k].charAt(j) == curMin)
							curMask += 1 << k;
					}
				}
				move[i][j] = curMask;
				if (move[i][j] == i)
					countStable[i]++;
			}
		}
		double[][] answer = new double[length + 1][maskCount];
		double[] result = new double[count];
		answer[0][maskCount - 1] = 1;
		for (int i = 0; i <= length; i++) {
			for (int j = 0; j < maskCount; j++) {
				if (answer[i][j] == 0)
					continue;
				if (Integer.bitCount(j) == 1) {
					result[Integer.bitCount(j - 1)] += answer[i][j];
					continue;
				}
				for (int k = 0; k < length; k++) {
					if (move[j][k] != j)
						answer[i + 1][move[j][k]] += answer[i][j] / (length - i);
				}
				answer[i + 1][j] = (countStable[j] - i) * answer[i][j] / (length - i);
			}
		}
		return result;
	}


}

