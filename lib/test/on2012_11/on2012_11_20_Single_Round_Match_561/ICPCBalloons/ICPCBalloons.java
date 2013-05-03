package on2012_11.on2012_11_20_Single_Round_Match_561.ICPCBalloons;



import net.egork.misc.ArrayUtils;
import net.egork.collections.comparators.IntComparator;

public class ICPCBalloons {
	public int minRepaintings(int[] balloonCount, String balloonSize, int[] maxAccepted) {
		int taskCount = maxAccepted.length;
		ArrayUtils.sort(maxAccepted, IntComparator.REVERSE);
		int mediumCount = 0;
		int largeCount = 0;
		for (char c : balloonSize.toCharArray()) {
			if (c == 'L')
				largeCount++;
			else
				mediumCount++;
		}
		int totalLarge = 0;
		int totalMedium = 0;
		int[] large = new int[largeCount];
		int[] medium = new int[mediumCount];
		for (int i = 0; i < balloonCount.length; i++) {
			if (balloonSize.charAt(i) == 'L') {
				large[--largeCount] = balloonCount[i];
				totalLarge += balloonCount[i];
			} else {
				medium[--mediumCount] = balloonCount[i];
				totalMedium += balloonCount[i];
			}
		}
		ArrayUtils.sort(large, IntComparator.REVERSE);
		ArrayUtils.sort(medium, IntComparator.REVERSE);
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < (1 << taskCount); i++) {
			int curLarge = 0;
			int curMedium = 0;
			for (int j = 0; j < taskCount; j++) {
				if ((i >> j & 1) == 0)
					curMedium += maxAccepted[j];
				else
					curLarge += maxAccepted[j];
			}
			if (curLarge > totalLarge || curMedium > totalMedium)
				continue;
			int current = 0;
			int k = 0;
			int l = 0;
			for (int j = 0; j < taskCount; j++) {
				if ((i >> j & 1) == 0) {
					if (k != medium.length)
						current += Math.max(0, maxAccepted[j] - medium[k++]);
					else
						current += maxAccepted[j];
				} else {
					if (l != large.length)
						current += Math.max(0, maxAccepted[j] - large[l++]);
					else
						current += maxAccepted[j];
				}
			}
			answer = Math.min(answer, current);
		}
		if (answer == Integer.MAX_VALUE)
			return -1;
		return answer;
	}
}
