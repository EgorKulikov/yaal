package net.egork;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.*;

public class ShufflegoldTestCase {
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
        int testCount = 100;
		int maxSize = 500;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
			List<Integer> permutation = new ArrayList<Integer>();
			int total = random.nextInt(maxSize) + 1;
			int count = random.nextInt(total) + 1;
			for (int i = 0; i < count; i++)
				permutation.add(i);
			Collections.shuffle(permutation, random);
			int[] current = ArrayUtils.createOrder(total);
			out.printLine(total, count, total);
			for (int i : permutation)
				out.printLine(i + 1);
			for (int i = 1; i <= total; i++)
				out.printLine(i);
			int[] next = new int[total];
			for (int i = 0; i <= total - count; i++) {
				System.arraycopy(current, 0, next, 0, total);
				for (int j = 0; j < count; j++)
					next[i + permutation.get(j)] = current[i + j];
				int[] temp = current;
				current = next;
				next = temp;
			}
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			for (int i = total - 1; i >= 0; i--) {
				outAnswer.printLine(current[i] + 1);
			}
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
