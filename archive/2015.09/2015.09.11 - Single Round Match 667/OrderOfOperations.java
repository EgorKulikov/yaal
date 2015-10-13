package net.egork;

import java.util.Arrays;

public class OrderOfOperations {
    int[] instructions;
    int[] result;

    public int minTime(String[] s) {
        instructions = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            int current = 0;
            for (int j = 0; j < s[i].length(); j++) {
                current *= 2;
                current += s[i].charAt(j) - '0';
            }
            instructions[i] = current;
        }
        result = new int[1 << s[0].length()];
        Arrays.fill(result, -1);
        return go(0);
    }

    private int go(int current) {
        if (result[current] != -1) {
            return result[current];
        }
        int answer = Integer.MAX_VALUE;
        for (int i : instructions) {
            if ((i | current) == current) {
                continue;
            }
            int delta = Integer.bitCount(i & (~current));
            answer = Math.min(answer, delta * delta + go(i | current));
        }
        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        return result[current] = answer;
    }
}
