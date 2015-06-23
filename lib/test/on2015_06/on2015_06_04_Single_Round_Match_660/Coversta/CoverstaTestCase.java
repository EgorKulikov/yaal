package on2015_06.on2015_06_04_Single_Round_Match_660.Coversta;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class CoverstaTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		String[] a = new String[100];
		Random random = new Random(239);
		for (int i = 0; i < 100; i++) {
			StringBuilder row = new StringBuilder(100);
			for (int j = 0; j < 100; j++) {
				row.append((char)('0' + random.nextInt(10)));
			}
			a[i] = row.toString();
		}
		int[] x = new int[10];
		int[] y = new int[10];
		for (int i = 0; i < 10; i++) {
			x[i] = i / 3;
			y[i] = i % 3;
		}
		tests.add(createTest(null, a, x, y));
		return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
