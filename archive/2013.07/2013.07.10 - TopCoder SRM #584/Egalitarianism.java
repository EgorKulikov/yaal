package net.egork;

public class Egalitarianism {
    public int maxDifference(String[] isFriend, int d) {
		int count = isFriend.length;
		int[][] distance = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (isFriend[i].charAt(j) == 'Y')
					distance[i][j] = 1;
				else
					distance[i][j] = Integer.MAX_VALUE / 2;
			}
			distance[i][i] = 0;
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++)
					distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
			}
		}
		int answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (distance[i][j] == Integer.MAX_VALUE / 2)
					return -1;
				answer = Math.max(answer, distance[i][j]);
			}
		}
		return answer * d;
    }
}
