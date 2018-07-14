package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;

public class TaskBTestCase {
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
        int testCount = 0;
        StringWriter sw = new StringWriter();
        OutputWriter out = new OutputWriter(sw);
        StringWriter swAnswer = new StringWriter();
        OutputWriter outAnswer = new OutputWriter(swAnswer);
        int aFrom = 1;
        int aTo = 30;
        int bFrom = 1;
        int bTo = 30;
        int cFrom = 1;
        int cTo = 30;
        out.printLine((aTo - aFrom + 1) * (bTo - bFrom + 1) * (cTo - cFrom + 1));
        for (int a = aFrom; a <= aTo; a++) {
            for (int b = bFrom; b <= bTo; b++) {
                for (int c = cFrom; c <= cTo; c++) {
                    out.printLine(a, b, c);
                    long answer = 0;
                    for (int i = 1; i <= max(aTo, max(bTo, cTo)); i++) {
                        for (int j = 1; j <= i; j++) {
                            for (int k = 1; k <= j; k++) {
                                if (a % i == 0 && b % j == 0 && c % k == 0 ||
                                    a % i == 0 && b % k == 0 && c % j == 0 ||
                                    a % j == 0 && b % i == 0 && c % k == 0 ||
                                    a % j == 0 && b % k == 0 && c % i == 0 ||
                                    a % k == 0 && b % i == 0 && c % j == 0 ||
                                    a % k == 0 && b % j == 0 && c % i == 0) {
                                    answer++;
                                }
                            }
                        }
                    }
                    outAnswer.printLine(answer);
                }
            }
        }
        tests.add(new Test(sw.toString(), swAnswer.toString()));
        return tests;
    }
}
