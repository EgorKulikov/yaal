package on2015_09.on2015_09_13_XVI_OpenCup__Grand_Prix_of_Ukraine.L___Light_Sources;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TaskLTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            out.printLine(300, 40, 6);
            int b = 23332;
            for (int i = 0; i < 300; i++) {
                out.printLine(random.nextInt(2 * b + 1) - b, random.nextInt(2 * b + 1) - b);
            }
            for (int i = 0; i < 300; i++) {
                out.printLine(random.nextInt(6) + 1);
            }
            for (int i = 0; i < 40; i++) {
                out.printLine(random.nextInt(2 * b + 1) - b, random.nextInt(2 * b + 1) - b);
            }
            tests.add(new Test(sw.toString()));
        }
        return tests;
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
