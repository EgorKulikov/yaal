package on2014_06.on2014_06_12_Single_Round_Match_624.DrivingPlans;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class DrivingPlansTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		int[] a = new int[2000];
		int[] b = new int[2000];
		int[] c = new int[2000];
		for (int i = 0; i < 500; i++) {
			a[4 * i] = 2 * i + 1;
			b[4 * i] = 2 * i + 3;
			a[4 * i + 1] = 2 * i + 1;
			b[4 * i + 1] = 2 * i + 4;
			a[4 * i + 2] = 2 * i + 2;
			b[4 * i + 2] = 2 * i + 3;
			a[4 * i + 3] = 2 * i + 2;
			b[4 * i + 3] = 2 * i + 4;
		}
		Arrays.fill(c, 1);
		tests.add(createTest(null, 1002, a, b, c));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
