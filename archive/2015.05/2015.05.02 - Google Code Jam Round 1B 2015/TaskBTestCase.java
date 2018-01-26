package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

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
        for (int rowCount = 1; rowCount <= 16; rowCount++) {
            for (int columnCount = 1; rowCount * columnCount <= 16; columnCount++) {
                int[] result = new int[rowCount * columnCount + 1];
                Arrays.fill(result, Integer.MAX_VALUE);
                for (int i = 0; i < (1 << (rowCount * columnCount)); i++) {
                    int current = 0;
                    for (int j = 0; j < rowCount; j++) {
                        for (int k = 0; k < columnCount; k++) {
                            int id = j * columnCount + k;
                            if ((i >> id & 1) == 0) {
                                continue;
                            }
                            if (j < rowCount - 1 && (i >> (id + columnCount) & 1) == 1) {
                                current++;
                            }
                            if (k < columnCount - 1 && (i >> (id + 1) & 1) == 1) {
                                current++;
                            }
                        }
                    }
                    result[Integer.bitCount(i)] = Math.min(result[Integer.bitCount(i)], current);
                }
                StringWriter sw = new StringWriter();
                OutputWriter out = new OutputWriter(sw);
                StringWriter swAns = new StringWriter();
                OutputWriter outAns = new OutputWriter(swAns);
                out.printLine(rowCount * columnCount + 1);
                for (int i = 0; i <= rowCount * columnCount; i++) {
                    out.printLine(rowCount, columnCount, i);
                    outAns.printLine("Case #" + (i + 1) + ":", result[i]);
                }
                tests.add(new Test(sw.toString(), swAns.toString()));
            }
        }
        return tests;
    }
}
