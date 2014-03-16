package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TaskD1TestCase {
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
		int n = 2;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			out.printLine(n, n);
			int[] xv = new int[n];
			int[] yv = new int[n];
			int[] lv = new int[n];
			int[] xh = new int[n];
			int[] yh = new int[n];
			int[] lh = new int[n];
			for (int i = 0; i < n; i++) {
				xv[i] = random.nextInt(100);
				yv[i] = random.nextInt(100);
				xh[i] = random.nextInt(100);
				yh[i] = random.nextInt(100);
				lv[i] = random.nextInt(100) + 1;
				lh[i] = random.nextInt(100) + 1;
			}
			int answer = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					answer = Math.max(answer, Math.min(Math.min(xv[i] - xh[j], xh[j] + lh[j] - xv[i]), Math.min(yh[j] - yv[i], yv[i] + lv[i] - yh[j])));
			}
			for (int i = 0; i < n; i++)
				out.printLine(xv[i], yv[i], lv[i]);
			for (int i = 0; i < n; i++)
				out.printLine(xh[i], yh[i], lh[i]);
			outAnswer.printLine(answer);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
