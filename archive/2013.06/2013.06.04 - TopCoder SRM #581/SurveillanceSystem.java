package net.egork;

import java.util.Arrays;

public class SurveillanceSystem {
    public String getContainerInfo(String containers, int[] reports, int L) {
		int[] count = new int[containers.length() - L + 1];
		for (int i = 0; i < count.length; i++) {
			for (int j = 0; j < L; j++) {
				if (containers.charAt(i + j) == 'X')
					count[i]++;
			}
		}
		int[] qty = new int[L + 1];
		for (int i : reports)
			qty[i]++;
		char[] answer = new char[containers.length()];
		Arrays.fill(answer, '-');
		int[] covered = new int[containers.length()];
		for (int i = 0; i <= L; i++) {
			if (qty[i] == 0)
				continue;
			Arrays.fill(covered, 0);
			int total = 0;
			for (int j = 0; j < count.length; j++) {
				if (count[j] == i) {
					for (int k = 0; k < L; k++)
						covered[j + k]++;
					total++;
				}
			}
			for (int j = 0; j < covered.length; j++) {
				if (covered[j] != 0 && answer[j] == '-')
					answer[j] = '?';
				if (covered[j] + qty[i] > total)
					answer[j] = '+';
			}
		}
		return new String(answer);
    }
}
