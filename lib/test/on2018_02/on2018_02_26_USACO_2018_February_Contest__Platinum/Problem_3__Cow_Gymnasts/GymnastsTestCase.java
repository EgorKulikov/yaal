package on2018_02.on2018_02_26_USACO_2018_February_Contest__Platinum.Problem_3__Cow_Gymnasts;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.fill;

public class GymnastsTestCase {
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
        int testCount = 16;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            int n = testNumber + 1;
            out.printLine(n);
            int answer = 1;
            int[] array = new int[n];
            int[] result = new int[n];
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < (1 << n) - 1; j++) {
                    fill(result, 0);
                    for (int k = 0; k < n; k++) {
                        array[k] = i + (j >> k & 1);
                        for (int l = k; l < k + array[k]; l++) {
                            result[l % n]++;
                        }
                    }
                    if (Arrays.equals(array, result)) {
                        answer++;
                    }
                }
            }
            outAnswer.printLine(answer);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
