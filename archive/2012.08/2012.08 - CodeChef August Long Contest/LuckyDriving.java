package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LuckyDriving {
    static final long MOD = 1000000007;
    private long[] count;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[] digits = in.readString().toCharArray();
        count = new long[10];
        for (char c : digits)
            count[c - '0']++;
        long answer = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    for (int l = k; l < 10; l++)
                        answer += get(i, j, k, l);
                    answer += get(i, j, k);
                }
                answer += get(i, j);
            }
            answer += get(i);
        }
        answer %= MOD;
        out.printLine(answer);
	}

    long get(int a, int b, int c, int d) {
        if ((a + b + c + d) % 9 != 0 || d == 0)
            return 0;
        long answer = 1;
        if (a == b) {
            if (b == c) {
                if (c == d)
                    answer *= count[a] * (count[a] - 1) * (count[a] - 2) * (count[a] - 3) / 24;
                else
                    answer *= count[a] * (count[a] - 1) * (count[a] - 2) / 6;
            } else
                answer *= count[a] * (count[a] - 1) / 2;
        } else
            answer *= count[a];
        if (a != b) {
            if (b == c) {
                if (c == d)
                    answer *= count[b] * (count[b] - 1) * (count[b] - 2) / 6;
                else
                    answer *= count[b] * (count[b] - 1) / 2;
            } else
                answer *= count[b];
        }
        if (b != c) {
            if (c == d)
                answer *= count[c] * (count[c] - 1) / 2;
            else
                answer *= count[c];
        }
        if (c != d)
            answer *= count[d];
        return answer % MOD;
    }

    long get(int a, int b, int c) {
        if ((a + b + c) % 9 != 0 || c == 0)
            return 0;
        long answer = 1;
        if (a == b) {
            if (b == c)
                answer *= count[a] * (count[a] - 1) * (count[a] - 2) / 6;
            else
                answer *= count[a] * (count[a] - 1) / 2;
        } else
            answer *= count[a];
        if (a != b) {
            if (b == c)
                answer *= count[b] * (count[b] - 1) / 2;
            else
                answer *= count[b];
        }
        if (b != c)
            answer *= count[c];
        return answer % MOD;
    }

    long get(int a, int b) {
        if ((a + b) % 9 != 0 || b == 0)
            return 0;
        long answer = 1;
        if (a == b)
            answer *= count[a] * (count[a] - 1) / 2;
        else
            answer *= count[a];
        if (a != b)
            answer *= count[b];
        return answer % MOD;
    }

    long get(int a) {
        if (a % 9 != 0 || a == 0)
            return 0;
        long answer = 1;
        answer *= count[a];
        return answer % MOD;
    }
}
