package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskB {
    Set<String> notAllowed = new HashSet<>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[] forbidden = in.readStringArray(n);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 0; k <= forbidden[i].length() - j; k++) {
                    notAllowed.add(forbidden[i].substring(k, k + j));
                }
            }
        }
        char[] answer = new char[4];
        for (int i = 1; i <= 4; i++) {
            if (can(0, i, answer)) {
                out.printLine(new String(answer, 0, i));
                return;
            }
        }
    }

    private boolean can(int current, int length, char[] answer) {
        if (current == length) {
            return !notAllowed.contains(new String(answer, 0, length));
        }
        for (char c = 'a'; c <= 'z'; c++) {
            answer[current] = c;
            if (can(current + 1, length, answer)) {
                return true;
            }
        }
        return false;
    }
}
