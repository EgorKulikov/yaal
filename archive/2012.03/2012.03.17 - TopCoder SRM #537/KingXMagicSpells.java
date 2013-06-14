package net.egork;

import java.util.Arrays;

public class KingXMagicSpells {
	public double expectedNumber(int[] ducks, int[] spellOne, int[] spellTwo, int K) {
		int count = ducks.length;
		int[] reverse = new int[count];
		for (int i = 0; i < count; i++)
			reverse[spellTwo[i]] = i;
		double answer = 0;
		int curDuck = 0;
		long[][][][] bitCount = new long[2][K + 2][K + 2][30];
		Arrays.fill(bitCount[0][0][0], 1);
		for (int i = 0; i <= K; i++) {
			for (int j = 1; j <= K + 1; j++) {
				for (int l = 0; l < 30; l++) {
					for (int m = 0; m < 2; m++)
						bitCount[m][i + 1][j][l] = bitCount[m][i][j - 1][l] + bitCount[m ^ (spellOne[curDuck] >> l & 1)][i + 1][j - 1][l];
				}
			}
			for (int j = 0; j < 30; j++)
				answer += (double)bitCount[1 - (ducks[curDuck] >> j & 1)][i + 1][K + 1][j] * (1 << j);
			curDuck = reverse[curDuck];
		}
		answer /= 1L << K;
		return answer;
	}


}

