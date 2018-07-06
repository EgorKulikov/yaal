package net.egork;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.string.StringUtils.reverse;

public class PalindromeSubsequence {
    public int[] optimalPartition(String s) {
        if (s.equals(reverse(s))) {
            return createArray(s.length(), 1);
        }
        int[] answer = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            answer[i] = s.charAt(i) - 'a' + 1;
        }
        return answer;
    }
}
