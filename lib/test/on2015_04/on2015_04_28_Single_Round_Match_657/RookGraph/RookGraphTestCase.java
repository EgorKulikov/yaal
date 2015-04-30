package on2015_04.on2015_04_28_Single_Round_Match_657.RookGraph;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class RookGraphTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		String[] input = new String[16];
		for (int i = 0; i < 16; i++) {
			input[i] = "";
			for (int j = 0; j < 16; j++) {
				input[i] += (i == j) ? "1" : "0";
			}
		}
		tests.add(createTest(null, 16, input));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
