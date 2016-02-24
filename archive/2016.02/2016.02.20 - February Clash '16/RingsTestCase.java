package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.StringInputStream;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class RingsTestCase {
    String cases = "test 1: 5 3 7 5\n" +
            "\n" +
            "test 2: 6 6 6 6\n" +
            "\n" +
            "test 3: 7 4 7 5\n" +
            "\n" +
            "test 4: 8 5 10 8\n" +
            "\n" +
            "test 5: 9 3 9 9\n" +
            "\n" +
            "test 6: 20 7 15 10\n" +
            "\n" +
            "test 7: 25 5 30 10\n" +
            "\n" +
            "test 8: 40 12 50 20\n" +
            "\n" +
            "test 9: 50 4 40 10\n" +
            "\n" +
            "test 10: 60 20 70 40\n" +
            "\n" +
            "test 11: 200 30 200 200\n" +
            "\n" +
            "test 12: 250 6 100 60\n" +
            "\n" +
            "test 13: 300 80 400 200\n" +
            "\n" +
            "test 14: 350 17 200 70\n" +
            "\n" +
            "test 15: 400 31 170 40\n" +
            "\n" +
            "test 16: 5000 10 5000 5000\n" +
            "\n" +
            "test 17: 5500 20 6000 2000\n" +
            "\n" +
            "test 18: 6000 30 10000 800\n" +
            "\n" +
            "test 19: 6500 5 3000 200\n" +
            "\n" +
            "test 20: 7000 40 7000 2000\n" +
            "\n" +
            "test 21: 50000 6 70000 50000\n" +
            "\n" +
            "test 22: 55000 4 100000 15000\n" +
            "\n" +
            "test 23: 60000 10 15000 6000\n" +
            "\n" +
            "test 24: 65000 4 40000 20000\n" +
            "\n" +
            "test 25: 70000 11 90000 60000\n" +
            "\n" +
            "test 26: 200000 5 200000 200000\n" +
            "\n" +
            "test 27: 205000 3 300000 60000\n" +
            "\n" +
            "test 28: 210000 4 100000 45000\n" +
            "\n" +
            "test 29: 230000 4 150000 90000\n" +
            "\n" +
            "test 30: 500000 2 100000 10000\n" +
            "\n" +
            "test 31: 50000 6 700 500\n" +
            "\n" +
            "test 32: 55000 4 1000 150\n" +
            "\n" +
            "test 33: 60000 10 1500 600\n" +
            "\n" +
            "test 34: 65000 4 400 200\n" +
            "\n" +
            "test 35: 70000 11 900 600\n" +
            "\n" +
            "test 36: 200000 5 200 200\n" +
            "\n" +
            "test 37: 205000 3 300 60\n" +
            "\n" +
            "test 38: 210000 4 1000 450\n" +
            "\n" +
            "test 39: 230000 4 1500 900\n" +
            "\n" +
            "test 40: 500000 2 1000 100";

    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 0;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 40;
        InputReader in = new InputReader(new StringInputStream(cases));
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            in.readString();
            in.readString();
            int n = in.readInt();
            int m = in.readInt();
            int c = in.readInt();
            int e = in.readInt();
            int[][] rings = new int[n][m];
            boolean[][] was = new boolean[m][c];
            for (int i = 0; i < e; i++) {
                for (int j = 0; j < m; j++) {
                    do {
                        rings[i][j] = random.nextInt(c);
                    } while (was[j][rings[i][j]]);
                    was[j][rings[i][j]] = true;
                }
            }
            for (int i = e; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    rings[i][j] = random.nextInt(c);
                }
            }
            for (int i = 0; i < n; i++) {
                int sh = random.nextInt(m);
                int[] w = rings[i].clone();
                for (int j = 0; j < m; j++) {
                    rings[i][j] = w[(j + sh) % m];
                }
                int prev = random.nextInt(i + 1);
                for (int j = 0; j < m; j++) {
                    int temp = rings[i][j];
                    rings[i][j] = rings[prev][j];
                    rings[prev][j] = temp;
                }
            }
            out.printLine(n, m, c);
            for (int i = 0; i < n; i++) {
                out.printLine(rings[i]);
            }
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
