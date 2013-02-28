package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class EndOfTheWorldTestCase {
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
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int max = 100;
			out.printLine(max + 1);
			for (int i = 0; i <= max; i++)
				out.printLine(i);
			NavigableSet<Integer> good = new TreeSet<Integer>();
			for (int i = 2; i <= max; i++) {
				for (int j = i * i; j <= max; j *= i)
					good.add(j);
			}
			int[] bad = new int[max + 1 - good.size()];
			int k = 0;
			for (int i = 0; i <= max; i++) {
				if (!good.contains(i))
					bad[k++] = i;
			}
			for (int i = 0; i <= max; i++) {
				if (i == 0)
					outAnswer.printLine(2);
				else if (good.contains(i))
					outAnswer.printLine(good.headSet(i, true).size());
				else {
					int position = Arrays.binarySearch(bad, i) + 1;
					int x = 2;
					int result = 0;
					while (true) {
						result++;
						if (x == position)
							break;
						if (x > position) {
							int l = x / 2;
							int r = x;
							while (true) {
								int m = (l + r) / 2;
								result++;
								if (m == position)
									break;
								else if (m > position)
									r = m;
								else
									l = m;
							}
							break;
						}
						x *= 2;
					}
					outAnswer.printLine(result);
				}
			}
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
