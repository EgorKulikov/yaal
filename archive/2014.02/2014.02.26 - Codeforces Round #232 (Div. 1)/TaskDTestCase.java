package net.egork;

import com.sun.org.apache.bcel.internal.generic.LUSHR;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.sequence.ListUtils;
import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskDTestCase {
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
        int testCount = 20;
		int size = 8;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			List<Integer> permutation = new ArrayList<Integer>();
			for (int i = 1; i <= size; i++)
				permutation.add(i);
			Collections.shuffle(permutation, random);
			out.printLine(size);
			out.printLine(permutation.toArray());
			List<Integer> current = new ArrayList<Integer>();
			for (int i = 1; i <= size; i++)
				current.add(i);
			long answer = 0;
			while (true) {
				answer += ListUtils.countUnorderedPairs(current);
				if (current.equals(permutation))
					break;
				ListUtils.nextPermutation(current);
			}
			outAnswer.printLine(answer);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
