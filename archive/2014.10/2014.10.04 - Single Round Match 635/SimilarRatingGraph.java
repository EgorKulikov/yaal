package net.egork;

import net.egork.misc.ArrayUtils;

public class SimilarRatingGraph {
    public double maxLength(int[] _date, int[] _rating) {
		long[] date = ArrayUtils.asLong(_date);
		long[] rating = ArrayUtils.asLong(_rating);
		double answer = 0;
		for (int l = 1; l < date.length - 1; l++) {
			for (int i = 0; i + l < date.length - 1; i++) {
				int j = i + l;
				if ((date[i + 1] - date[i]) * (rating[j + 1] - rating[j]) != (date[j + 1] - date[j]) * (rating[i + 1] - rating[i])) {
					continue;
				}
				long a = date[i + 1] - date[i];
				long b = date[j + 1] - date[j];
				double length = Math.max(Math.hypot(date[i + 1] - date[i], rating[i + 1] - rating[i]), Math.hypot(date[j + 1] - date[j], rating[j + 1] - rating[j]));
				for (int k = 1; j + k < date.length; k++) {
					if (j + k == date.length - 1 || a * (date[j + k + 1] - date[j + k]) != b * (date[i + k + 1] - date[i + k]) ||
						a * (rating[j + k + 1] - rating[j + k]) != b * (rating[i + k + 1] - rating[i + k]))
					{
						i = i + k - 1;
						answer = Math.max(answer, length);
						break;
					}
					length += Math.max(Math.hypot(date[i + k + 1] - date[i + k], rating[i + k + 1] - rating[i + k]), Math.hypot(date[j + k + 1] - date[j + k], rating[j + k + 1] - rating[j + k]));
				}
			}
		}
		return answer;
    }
}
