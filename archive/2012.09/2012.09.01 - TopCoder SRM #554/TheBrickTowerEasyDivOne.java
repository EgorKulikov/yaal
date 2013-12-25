package net.egork;

public class TheBrickTowerEasyDivOne {
	public int find(int redCount, int redHeight, int blueCount, int blueHeight) {
		if (redHeight == blueHeight)
			return Math.min(2 * Math.min(redCount, blueCount) + 1, redCount + blueCount);
		int bothCount = Math.min(redCount, blueCount);
		int answer = 3 * bothCount;
		if (Math.max(redCount, blueCount) > bothCount)
			answer++;
		return answer;
	}
}
