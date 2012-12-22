package net.egork;

public class CrossingTheRiver {
	public String isItEvenPossible(int waterWidth, int landWidth, int[] blockHeight, int depth) {
		int[] count = new int[102];
		for (int i : blockHeight)
			count[i + 1]++;
		count[1] = 100;
		int[] sum = new int[102];
		for (int i = 1; i <= 101; i++) {
			sum[i] = sum[i - 1] + count[i];
		}
		for (int firstWaterHeight = depth + 1; firstWaterHeight <= 101 && firstWaterHeight <= depth + 2; firstWaterHeight++) {
			for (int lastWaterHeight = firstWaterHeight; lastWaterHeight <= 101; lastWaterHeight++) {
				if (count[lastWaterHeight] == 0)
					break;
				for (int firstLandHeight = lastWaterHeight - depth; firstLandHeight <= 101 && firstLandHeight <= lastWaterHeight - depth + 1; firstLandHeight++) {
					for (int lastLandHeight = firstLandHeight; lastLandHeight <= 101; lastLandHeight++) {
						int need = 1;
						if (lastLandHeight >= firstWaterHeight && lastLandHeight <= lastWaterHeight)
							need++;
						if (count[lastLandHeight] < need)
							break;
						int heightIntersection = Math.max(0, Math.min(lastWaterHeight, lastLandHeight) - Math.max(firstWaterHeight, firstLandHeight) + 1);
						int waterCount = sum[lastWaterHeight] - sum[firstWaterHeight - 1];
						int landCount = sum[lastLandHeight] - sum[firstLandHeight - 1];
						int sharedCount = 0;
						if (Math.min(lastWaterHeight, lastLandHeight) >= Math.max(firstWaterHeight, firstLandHeight)) {
							sharedCount = sum[Math.min(lastWaterHeight, lastLandHeight)] - sum[Math.max(firstWaterHeight, firstLandHeight) - 1];
						}
						int waterNeed = waterWidth - heightIntersection;
						int landNeed = landWidth - heightIntersection;
						waterCount -= 2 * heightIntersection;
						landCount -= 2 * heightIntersection;
						sharedCount -= 2 * heightIntersection;
						if (waterNeed <= waterCount && landNeed <= landCount && waterNeed + landNeed <= waterCount + landCount - sharedCount)
							return "POSSIBLE";
					}
				}
			}
		}
		return "IMPOSSIBLE";
	}


}

