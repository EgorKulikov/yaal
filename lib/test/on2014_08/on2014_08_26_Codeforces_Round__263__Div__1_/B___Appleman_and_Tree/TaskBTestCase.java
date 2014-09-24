package on2014_08.on2014_08_26_Codeforces_Round__263__Div__1_.B___Appleman_and_Tree;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskBTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(436735745);
        int testCount = 100;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
			int n = 100000;
			out.printLine(n);
			int[] parent = new int[n - 1];
			for (int i = 0; i < n - 1; i++) {
				parent[i] = random.nextInt(i + 1);
			}
			out.printLine(parent);
			int[] color = new int[n];
			for (int i = 0; i < n; i++) {
				color[i] = random.nextInt(2);
			}
			out.printLine(color);
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
