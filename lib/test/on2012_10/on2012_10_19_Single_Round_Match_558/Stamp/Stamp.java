package on2012_10.on2012_10_19_Single_Round_Match_558.Stamp;



import net.egork.misc.ArrayUtils;

public class Stamp {
	public int getMinimumCost(String desiredColor, int stampCost, int pushCost) {
		int count = desiredColor.length();
		char[] color = desiredColor.toCharArray();
		int answer = Integer.MAX_VALUE;
		char[] types = "RGB".toCharArray();
		for (int i = 1; i <= count; i++) {
			int[][] result = new int[3][count + 1];
			ArrayUtils.fill(result, Integer.MAX_VALUE / 2);
			result[0][i] = pushCost;
			result[1][i] = pushCost;
			result[2][i] = pushCost;
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < 3; k++) {
					if (color[j] == types[k]) {
						for (int l = 0; l < 3; l++) {
							if (k != l)
								result[l][i] = Integer.MAX_VALUE / 2;
						}
					}
				}
			}
			for (int j = i + 1; j <= count; j++) {
				for (int k = 0; k < 3; k++) {
					boolean good = true;
					for (int l = j - i; l < j; l++) {
						if (color[l] != '*' && color[l] != types[k]) {
							good = false;
							break;
						}
					}
					if (!good)
						continue;
					for (int l = 0; l < 3; l++) {
						if (k != l)
							result[k][j] = Math.min(result[k][j], result[l][j - i] + pushCost);
					}
					for (int l = j - i; l < j; l++)
						result[k][j] = Math.min(result[k][j], result[k][l] + pushCost);
				}
			}
			answer = Math.min(answer, stampCost * i + Math.min(result[0][count], Math.min(result[1][count], result[2][count])));
		}
		return answer;
	}
}
