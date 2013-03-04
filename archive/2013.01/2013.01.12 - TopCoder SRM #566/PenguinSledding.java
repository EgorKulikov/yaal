package net.egork;

import java.util.Arrays;

public class PenguinSledding {
	public long countDesigns(int numCheckpoints, int[] checkpoint1, int[] checkpoint2) {
		long answer = 1 - numCheckpoints - checkpoint1.length;
		for (int i = 1; i <= numCheckpoints; i++) {
			int degree = 0;
			for (int j = 0; j < checkpoint1.length; j++) {
				if (checkpoint1[j] == i || checkpoint2[j] == i)
					degree++;
			}
			answer += 1L << degree;
		}
		int[] array = new int[6];
		for (int i = 0; i < checkpoint1.length; i++) {
			for (int j = i + 1; j < checkpoint1.length; j++) {
				for (int k = j + 1; k < checkpoint1.length; k++) {
					array[0] = checkpoint1[i];
					array[1] = checkpoint2[i];
					array[2] = checkpoint1[j];
					array[3] = checkpoint2[j];
					array[4] = checkpoint1[k];
					array[5] = checkpoint2[k];
					Arrays.sort(array);
					if (array[0] == array[1] && array[2] == array[3] && array[4] == array[5])
						answer++;
				}
			}
		}
		return answer;
	}
}
