package on2014_08.on2014_08_01_Codeforces_Round__259__Div__1_.E___Little_Pony_and_Lord_Tirek;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskETestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
			out.printLine(100000);
			for (int i = 0; i < 100000; i++) {
				int base = random.nextInt(100001);
				int max = random.nextInt(100001);
				int rate = random.nextInt(31);
				if (base > max) {
					int temp = base;
					base = max;
					max = temp;
				}
				out.printLine(base, max, rate);
			}
			out.printLine(100000);
			int[] time = new int[100000];
			for (int i = 0; i < 100000; i++) {
				time[i] = random.nextInt(1000000001);
			}
			Arrays.sort(time);
			for (int i = 0; i < 100000; i++) {
				int left = random.nextInt(100000) + 1;
				int right = random.nextInt(100000) + 1;
				if (left > right) {
					int temp = left;
					left = right;
					right = temp;
				}
				out.printLine(time[i], left, right);
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
