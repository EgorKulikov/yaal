package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;

import java.util.Arrays;

public class BlurredDartboard {
	public int minThrows(int[] points, int P) {
		Arrays.sort(points);
		int[] notSeen = new int[CollectionUtils.count(Array.wrap(points), 0)];
		int index = notSeen.length;
		int otherIndex = 0;
		for (int i = 1; i <= points.length; i++) {
			if (index == points.length || i != points[index])
				notSeen[otherIndex++] = i;
			else
				index++;
		}
		int[] maxPerHits = new int[Math.max(notSeen.length + 1, 2)];
		for (int i = 1; i <= notSeen.length; i++)
			maxPerHits[i] = maxPerHits[i - 1] + notSeen[i - 1];
		int maxVisible = points[points.length - 1];
		for (int i = 1; i < maxPerHits.length; i++)
			maxPerHits[i] = Math.max(maxPerHits[i], maxVisible * i);
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i < maxPerHits.length; i++) {
			int current;
			if (maxPerHits[i] >= P)
				current = i;
			else
				current = i + ((P - maxPerHits[i] + maxPerHits[maxPerHits.length - 1] - 1) / maxPerHits[maxPerHits.length - 1]) * (maxPerHits.length - 1);
			answer = Math.min(answer, current);
		}
		return answer;
	}

}

