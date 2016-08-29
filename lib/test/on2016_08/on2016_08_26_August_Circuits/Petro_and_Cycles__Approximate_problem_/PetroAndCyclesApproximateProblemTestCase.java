package on2016_08.on2016_08_26_August_Circuits.Petro_and_Cycles__Approximate_problem_;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PetroAndCyclesApproximateProblemTestCase {
    Random random = new Random(239);

    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        int testCount = 50;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            int n = randSqrt(2, 100000);
            int m = randSqrt(2 * n, 10 * n);
            out.printLine(n, m);
            for (int i = 0; i < m; i++) {
                int a, b;
                do {
                    a = random.nextInt(n);
                    b = random.nextInt(n);
                } while (a == b);
                out.printLine(a + 1, b + 1);
            }
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    int randSqrt(int lo, int hi) {
        return lo + (int)(sqr(random.nextDouble() * Math.sqrt(hi - lo + 1)));
    }

    private double sqr(double v) {
        return v * v;
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 0;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
