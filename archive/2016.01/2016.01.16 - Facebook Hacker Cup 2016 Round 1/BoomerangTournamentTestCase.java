package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.collections.intcollection.Range;
import net.egork.generated.collections.list.IntList;
import net.egork.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class BoomerangTournamentTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 0;
        for (int n = 1; n <= 4; n *= 2) {
            int[] min = new int[n];
            int[] max = new int[n];
            wins = new int[n];
            queue = new int[2 * n - 1];
            int[][] w = new int[n][n];
            for (int j = 0; j < (1 << (n * (n - 1) / 2)); j++) {
                StringWriter sw = new StringWriter();
                OutputWriter out = new OutputWriter(sw);
                StringWriter swAnswer = new StringWriter();
                OutputWriter outAnswer = new OutputWriter(swAnswer);
                int at = 0;
                for (int i = 0; i < n; i++) {
                    for (int k = 0; k < n; k++) {
                        if (i > k) {
                            w[i][k] = 1 - w[k][i];
                        } else if (i < k) {
                            w[i][k] = j >> (at++) & 1;
                        }
                    }
                }
                out.printLine(1);
                out.printLine(n);
                for (int i = 0; i < n; i++) {
                    out.printLine(w[i]);
                }
                outAnswer.printLine("Case #1:");
                stupid(n, w, min, max);
                for (int i = 0; i < n; i++) {
                    outAnswer.printLine(min[i], max[i]);
                }
                tests.add(new Test(sw.toString(), swAnswer.toString()));
            }
        }
        return tests;
    }

    int[] wins;
    int[] queue;

    private void stupid(int n, int[][] w, int[] min, int[] max) {
        IntList order = Range.range(n);
        Arrays.fill(min, n + 1);
        Arrays.fill(max, 0);
        do {
            Arrays.fill(wins, 0);
            int start = 0;
            int end = n - 1;
            for (int i = 0; i < n; i++) {
                queue[i] = order.get(i);
            }
            while (start != end) {
                int first = queue[start++];
                int second = queue[start++];
                int winner;
                if (w[first][second] == 1) {
                    winner = first;
                } else {
                    winner = second;
                }
                queue[++end] = winner;
                wins[winner]++;
            }
            for (int i = 0; i < n; i++) {
                int place = 1;
                for (int j = 0; j < n; j++) {
                    if (i != j && wins[j] > wins[i]) {
                        place++;
                    }
                }
                min[i] = Math.min(min[i], place);
                max[i] = Math.max(max[i], place);
            }
        } while (order.nextPermutation());
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1000;
        int n = 8;
        int[] min = new int[n];
        int[] max = new int[n];
        wins = new int[n];
        queue = new int[2 * n - 1];
        int[][] w = new int[n][n];
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            long j = random.nextLong() % (1L << 28);
            int at = 0;
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    if (i > k) {
                        w[i][k] = 1 - w[k][i];
                    } else if (i < k) {
                        w[i][k] = (int) (j >> (at++) & 1);
                    }
                }
            }
            out.printLine(1);
            out.printLine(n);
            for (int i = 0; i < n; i++) {
                out.printLine(w[i]);
            }
            outAnswer.printLine("Case #1:");
            stupid(n, w, min, max);
            for (int i = 0; i < n; i++) {
                outAnswer.printLine(min[i], max[i]);
            }
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
