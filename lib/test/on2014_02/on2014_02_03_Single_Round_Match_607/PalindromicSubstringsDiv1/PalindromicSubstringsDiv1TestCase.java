package on2014_02.on2014_02_03_Single_Round_Match_607.PalindromicSubstringsDiv1;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class PalindromicSubstringsDiv1TestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		String[] s = new String[50];
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 50; i++)
			builder.append('?');
		Arrays.fill(s, builder.toString());
		tests.add(createTest(null, s, s));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
