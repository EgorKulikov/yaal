package on2016_03.on2016_03_13_GP_of_Tatarstan.A___Three_Seamarks;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.geometry.Angle;
import net.egork.geometry.Point;
import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskATestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            out.printLine(50000);
            for (int i = 0; i < 50000; i++) {
                Point m1 = /*new Point(0, 0);*/new Point(random.nextInt(20001) - 10000, random.nextInt(20001) - 10000);
                Point m2 = /*new Point(2, 0);*/new Point(random.nextInt(20001) - 10000, random.nextInt(20001) - 10000);
                Point m3 = new Point(random.nextInt(20001) - 10000, random.nextInt(20001) - 10000);
                Point p = new Point(random.nextInt(20001) - 10000, random.nextInt(20001) - 10000);
                out.printLine(m1.x, m1.y, m2.x, m2.y, m3.x, m3.y, new Angle(p, m1, m2).value() * 180 / Math.PI,
                        new Angle(p, m2, m3).value() * 180 / Math.PI);
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
