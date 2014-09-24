package net.egork;

import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class HappyLetterDiv1 {
    public String getHappyLetters(String letters) {
		int[] qty = new int[26];
		for (char c : letters.toCharArray()) {
			qty[c - 'a']++;
		}
		int[] other = new int[25];
		StringBuilder answer = new StringBuilder(26);
		for (int i = 0; i < 26; i++) {
			int j = 0;
			for (int k = 0; k < 26; k++) {
				if (i != k) {
					other[j++] = qty[k];
				}
			}
			Arrays.sort(other);
			long sum = ArrayUtils.sumArray(other);
			int remaining = other[24];
			for (int k = 0; k < 24; k++) {
				remaining -= other[k];
			}
			remaining = (int) Math.max(remaining, sum & 1);
			if (remaining < qty[i]) {
				answer.append((char)('a' + i));
			}
		}
		return answer.toString();
    }
}
