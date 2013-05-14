package on2013_01.on2013_01_25_.SortTest;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.*;

public class SortTestCase {
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
        int testCount = 10;
		int size = 20;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			out.printLine(size);
			int[] array = new int[size];
			for (int i = 0; i < size; i++)
				array[i] = random.nextInt((int) 1e6);
			out.printLine(array);
			Arrays.sort(array);
			ArrayUtils.reverse(array);
			outAnswer.printLine(array);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
