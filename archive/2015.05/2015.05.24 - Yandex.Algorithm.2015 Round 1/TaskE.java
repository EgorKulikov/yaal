package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long[] total = new long[70];
        long[] qty = new long[70];
        int[] skip = new int[70];
        total[0] = 1;
        qty[0] = 1;
        int count = in.readInt();
        int root = 0;
        for (int i = 0; i < count; i++) {
            char type = in.readCharacter();
            if (type == '+') {
                int by = in.readInt();
                if (by == 1) {
                    skip[root]++;
                    total[root]++;
                } else {
                    root++;
                    qty[root] = by;
                    total[root] = qty[root] * total[root - 1] + 1;
                }
            } else {
                long first = in.readLong() - 1;
                long second = in.readLong() - 1;
                int answer = 0;
                boolean different = false;
                int level = root;
                while (first > 0 || second > 0) {
                    long firstSubTree = -1, secondSubTree = -1;
                    if (first > skip[level]) {
                        first -= skip[level] + 1;
                        firstSubTree = first / total[level - 1];
                        first %= total[level - 1];
                    }
                    if (second > skip[level]) {
                        second -= skip[level] + 1;
                        secondSubTree = second / total[level - 1];
                        second %= total[level - 1];
                    }
                    if (different) {
                        if (firstSubTree == -1) {
                            answer += first;
                        } else {
                            answer += skip[level];
                        }
                        if (secondSubTree == -1) {
                            answer += second;
                        } else {
                            answer += skip[level];
                        }
                    } else {
                        if (firstSubTree == -1) {
                            if (secondSubTree == -1) {
                                answer += Math.abs(first - second);
                            } else {
                                answer += skip[level] - first;
                            }
                        } else if (secondSubTree == -1) {
                            answer += skip[level] - second;
                        }
                    }
                    if (firstSubTree == -1) {
                        first = 0;
                    }
                    if (secondSubTree == -1) {
                        second = 0;
                    }
                    if (firstSubTree != secondSubTree) {
                        different = true;
                    }
                    if (different) {
                        if (firstSubTree != -1) {
                            answer++;
                        }
                        if (secondSubTree != -1) {
                            answer++;
                        }
                    }
                    level--;
                }
                out.printLine(answer);
            }
        }
    }
}
