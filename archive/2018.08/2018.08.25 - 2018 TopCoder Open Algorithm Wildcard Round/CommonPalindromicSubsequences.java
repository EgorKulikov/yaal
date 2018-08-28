package net.egork;

import java.util.Arrays;

import static java.lang.System.arraycopy;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.fill;

public class CommonPalindromicSubsequences {
    int[][] nextA;
    int[][] nextB;
    int[][] prevA;
    int[][] prevB;

    long[][][][] answer;

    public long count(String A, String B) {
        answer = new long[A.length()][A.length()][B.length()][B.length()];
        fill(answer, -1);
        int[] pos = createArray(26, A.length());
        nextA = new int[A.length()][26];
        for (int i = A.length() - 1; i >= 0; i--) {
            pos[A.charAt(i) - 'a'] = i;
            arraycopy(pos, 0, nextA[i], 0, 26);
        }
        Arrays.fill(pos, B.length());
        nextB = new int[B.length()][26];
        for (int i = B.length() - 1; i >= 0; i--) {
            pos[B.charAt(i) - 'a'] = i;
            arraycopy(pos, 0, nextB[i], 0, 26);
        }
        Arrays.fill(pos, -1);
        prevA = new int[A.length()][26];
        for (int i = 0; i < A.length(); i++) {
            pos[A.charAt(i) - 'a'] = i;
            arraycopy(pos, 0, prevA[i], 0, 26);
        }
        Arrays.fill(pos, -1);
        prevB = new int[B.length()][26];
        for (int i = 0; i < B.length(); i++) {
            pos[B.charAt(i) - 'a'] = i;
            arraycopy(pos, 0, prevB[i], 0, 26);
        }
        return go(0, A.length() - 1, 0, B.length() - 1) - 1;
    }

    private long go(int stA, int enA, int stB, int enB) {
        if (stA > enA || stB > enB) {
            return 1;
        }
        if (answer[stA][enA][stB][enB] != -1) {
            return answer[stA][enA][stB][enB];
        }
        answer[stA][enA][stB][enB] = 1;
        for (int i = 0; i < 26; i++) {
            if (nextA[stA][i] <= enA && nextB[stB][i] <= enB) {
                answer[stA][enA][stB][enB]++;
            }
            if (nextA[stA][i] < prevA[enA][i] && nextB[stB][i] < prevB[enB][i]) {
                answer[stA][enA][stB][enB] += go(nextA[stA][i] + 1, prevA[enA][i] - 1, nextB[stB][i] + 1,
                        prevB[enB][i] - 1);
            }
        }
        return answer[stA][enA][stB][enB];
    }
}
