package on2015_12.on2015_12_26_Single_Round_Match_677.DiameterOfRandomTree;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.misc.ArrayUtils;

import java.util.*;

public class DiameterOfRandomTreeTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		int[] a = ArrayUtils.createOrder(50);
		int[] b = a.clone();
		for (int i = 0; i < 50; i++) {
			b[i]++;
		}
		tests.add(createTest(null, a, b));
		return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
