package net.egork;

public class AstronomicalRecords {
    public int minimalPlanets(int[] A, int[] B) {
		int answer = A.length + B.length;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				int[][] result = new int[A.length + 1][B.length + 1];
				result[i][j] = 0;
				for (int l = i; l <= A.length; l++) {
					for (int m = j; m <= B.length; m++) {
						if (l < A.length)
							result[l + 1][m] = Math.max(result[l + 1][m], result[l][m]);
						if (m < B.length)
							result[l][m + 1] = Math.max(result[l][m + 1], result[l][m]);
						if (l < A.length && m < B.length && (long)A[i] * B[m] == (long)A[l] * B[j])
							result[l + 1][m + 1] = Math.max(result[l + 1][m + 1], result[l][m] + 1);
					}
				}
				answer = Math.min(answer, A.length + B.length - result[A.length][B.length]);
			}
		}
		return answer;
    }
}
