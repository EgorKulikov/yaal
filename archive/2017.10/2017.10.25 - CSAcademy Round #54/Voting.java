package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.sort;
import static net.egork.io.InputReader.readLongArray;

public class Voting {
    int n;
    long[] score;
    long[] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        int x = in.readInt();
        score = in.readLongArray(n);
        sort(score);
        score = copyOf(score, n + 1);
        answer = new long[n];
        int result = possible(x);
        if (result < 0) {
            int left = 0;
            int right = x - 1;
            while (left < right) {
                int middle = (left + right + 1) >> 1;
                if (possible(middle) >= 0) {
                    left = middle;
                } else {
                    right = middle - 1;
                }
            }
            if (possible(left) != 0) {
                throw new RuntimeException();
            }
        } else if (result > 0) {
            int left = x + 1;
            int right = n;
            while (left < right) {
                int middle = (left + right) >> 1;
                if (possible(middle) <= 0) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            }
            if (possible(left) != 0) {
                throw new RuntimeException();
            }
        }
        out.printLine(answer);
    }

    private int possible(int x) {
        int up = n - x;
        long curScore = 0;
        NavigableSet<Integer> availableLows = new TreeSet<>((a, b) -> (score[a] != score[b] ? Long.compare(score[a],
                score[b]) : a - b));
        for (int i = 0; i < n - x; i++) {
            availableLows.add(i);
        }
        for (int i = 0; i < n; i++) {
            if (up < n && curScore * n < i * score[up]) {
                curScore++;
                answer[i] = score[up++];
            } else {
                if (availableLows.isEmpty()) {
                    return -1;
                }
                long limit = i == 0 ? Long.MAX_VALUE : curScore * n / i;
                score[n] = limit;
                Integer option = availableLows.floor(n);
                if (option == null) {
                    return 1;
                }
                availableLows.remove(option);
                answer[i] = score[option];
            }
        }
        return 0;
    }
}
