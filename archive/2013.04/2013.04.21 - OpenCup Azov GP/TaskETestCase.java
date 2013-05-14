package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TaskETestCase {
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
        int testCount = 0;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
			System.err.println(testNumber);
			StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int height = 2 + random.nextInt(5);
			int width = 2 + random.nextInt(4);
			int[][] weights = new int[height][width];
			out.printLine(height, width);
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++)
					weights[i][j] = random.nextInt((int) (10));
				out.printLine(weights[i]);
			}
			outAnswer.printLine(calculateRows(0L, 0, height, width, weights));
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }

	private long calculateRows(long mask, int row, int height, int width, int[][] weights) {
		if (row == height)
			return calculateColumns(mask, 0, height, width, weights);
		long result = Long.MAX_VALUE / 2;
		for (int i = 0; i < width; i++) {
			int index = row * width + i;
			if ((mask >> index & 1) == 0)
				result = Math.min(result, calculateRows(mask + (1L << index), row + 1, height, width, weights) + weights[row][i]);
		}
		return result;
	}

	private long calculateColumns(long mask, int column, int height, int width, int[][] weights) {
		if (column == width)
			return 0;
		long result = Long.MAX_VALUE / 2;
		for (int i = 0; i < height; i++) {
			int index = i * width + column;
			if ((mask >> index & 1) == 0)
				result = Math.min(result, calculateColumns(mask + (1L << index), column + 1, height, width, weights) + weights[i][column]);
		}
		return result;
	}
}
