package on2015_08.on2015_08_07_August_Challenge_2015.Chef_and_insomnia;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ChefAndInsomniaTestCase {
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
        int testCount = 1000;
        int n = 10;
        int[] a = new int[n];
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            int k = random.nextInt(10);
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(10) + 1;
            }
            out.printLine(n, k);
            out.printLine(a);
            int answer = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    boolean good = true;
                    for (int x = i; x < j; x++) {
                        for (int y = x + 1; y <= j; y++) {
                            if (a[x] % a[y] == k) {
                                good = false;
                                break;
                            }
                        }
                    }
                    if (good) {
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
