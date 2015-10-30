package on2015_10.on2015_10_14_Single_Round_Match_671.BearDarts;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class BearDartsTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		Random random = new Random(239);
		int[] test = new int[1000];
		for (int i = 0; i < test.length; i++) {
			test[i] = random.nextInt(1000000000) + 1;
		}
		tests.add(createTest(null, (Object)test));
		return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
