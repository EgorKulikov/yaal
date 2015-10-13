package on2015_07.on2015_07_23_Single_Round_Match_663.ChangingChange;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.numbers.IntegerUtils;

import java.util.*;

public class ChangingChangeTestCase {
	private static final long MOD = (long) (1e9 + 7);

    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		long[][] c = IntegerUtils.generateBinomialCoefficients(4000, MOD);
		int[] ways = new int[2000];
		for (int i = 0; i < 2000; i++) {
			ways[i] = (int) c[3999][i];
		}
		int[] vRemoved = new int[2000];
		int[] nRemoved = new int[2000];
		Arrays.fill(vRemoved, 1);
		Arrays.fill(nRemoved, 2000);
		tests.add(createTest(null, ways, vRemoved, nRemoved));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
