package net.egork;

import net.egork.string.StringUtils;

public class PalindromicSubstringsDiv1 {
    public double expectedPalindromes(String[] S1, String[] S2) {
		String s = StringUtils.unite(S1) + StringUtils.unite(S2);
		double answer = 0;
		for (int i = 0; i < s.length(); i++) {
			double current = 1;
			answer++;
			for (int j = 1; j <= i && i + j < s.length(); j++) {
				if (s.charAt(i - j) != '?' && s.charAt(i + j) != '?') {
					if (s.charAt(i - j) != s.charAt(i + j))
						break;
				} else
					current /= 26;
				answer += current;
			}
		}
		for (int i = 1; i < s.length(); i++) {
			double current = 1;
			for (int j = 0; j < i && i + j < s.length(); j++) {
				if (s.charAt(i - j - 1) != '?' && s.charAt(i + j) != '?') {
					if (s.charAt(i - j - 1) != s.charAt(i + j))
						break;
				} else
					current /= 26;
				answer += current;
			}
		}
		return answer;
    }
}
