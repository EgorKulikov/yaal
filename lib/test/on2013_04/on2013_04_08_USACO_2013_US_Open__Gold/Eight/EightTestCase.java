package on2013_04.on2013_04_08_USACO_2013_US_Open__Gold.Eight;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class EightTestCase {
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
		int n = 20;
		double threshold = .1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			char[][] map = new char[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					map[i][j] = random.nextDouble() < threshold ? '*' : '.';
			}
			out.printLine(n);
			for (char[] row : map)
				out.printLine(row);
			int answer = 0;
			for (int r0 = 0; r0 < n; r0++) {
				for (int r1 = r0 + 2; r1 < n; r1++) {
					for (int r2 = r1 + 2; r2 < n; r2++) {
						for (int d0 = 0; d0 < n; d0++) {
							for (int d1 = d0 + 2; d1 < n; d1++) {
								for (int u0 = d0; u0 <= d1; u0++) {
									for (int u1 = u0 + 2; u1 <= d1; u1++) {
										boolean valid = true;
										for (int i = u0; i <= u1 && valid; i++) {
											if (map[r0][i] == '*')
												valid = false;
										}
										for (int i = d0; i <= d1 && valid; i++) {
											if (map[r1][i] == '*')
												valid = false;
										}
										for (int i = d0; i <= d1 && valid; i++) {
											if (map[r2][i] == '*')
												valid = false;
										}
										for (int i = r0; i <= r1 && valid; i++) {
											if (map[i][u0] == '*')
												valid = false;
										}
										for (int i = r0; i <= r1 && valid; i++) {
											if (map[i][u1] == '*')
												valid = false;
										}
										for (int i = r1; i <= r2 && valid; i++) {
											if (map[i][d0] == '*')
												valid = false;
										}
										for (int i = r1; i <= r2 && valid; i++) {
											if (map[i][d1] == '*')
												valid = false;
										}
										if (valid)
											answer = Math.max(answer, (r1 - r0 - 1) * (r2 - r1 - 1) * (u1 - u0 - 1) * (d1 - d0 - 1));
									}
								}
							}
						}
					}
				}
			}
			if (answer == 0)
				answer = -1;
			outAnswer.printLine(answer);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
