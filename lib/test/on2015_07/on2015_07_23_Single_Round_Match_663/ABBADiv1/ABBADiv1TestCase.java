package on2015_07.on2015_07_23_Single_Round_Match_663.ABBADiv1;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ABBADiv1TestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		String s = "";
//		Random r = new Random(42);
		for (int i = 0; i < 30; i++) {
			s += "BA";
		}
		s += "AABB";
		for (int i = 0; i < 30; i++) {
			s += "BA";
		}
		tests.add(createTest("Impossible", "A", s));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
