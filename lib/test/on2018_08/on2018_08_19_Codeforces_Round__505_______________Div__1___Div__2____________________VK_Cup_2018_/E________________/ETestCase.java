package on2018_08.on2018_08_19_Codeforces_Round__505_______________Div__1___Div__2____________________VK_Cup_2018_.E________________;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ETestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            out.printLine(50, 50);
            for (int i = 0; i < 50; i++) {
                out.printLine(i + 1, i < 25 ? 50 : 1);
            }
            for (int i = 0; i < 50; i++) {
                out.printLine(50 - i, i < 25 ? 1 : 50);
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
