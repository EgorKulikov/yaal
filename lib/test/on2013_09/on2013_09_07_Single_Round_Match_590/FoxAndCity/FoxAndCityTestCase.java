package on2013_09.on2013_09_07_Single_Round_Match_590.FoxAndCity;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class FoxAndCityTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		Random random = new Random(239);
		for (int i = 0; i < 500; i++) {
			String[] graph = new String[40];
			for (int j = 0; j < 40; j++) {
				StringBuilder builder = new StringBuilder(40);
				for (int k = 0; k < j; k++)
					builder.append(graph[k].charAt(j));
				builder.append('N');
				for (int k = j + 1; k < 40; k++) {
					if (random.nextInt(5) == 0)
						builder.append('Y');
					else
						builder.append('N');
				}
				graph[j] = builder.toString();
			}
			int[] want = new int[40];
			for (int j = 1; j < 40; j++) {
				if (random.nextBoolean())
					want[j] = 1;
				else
					want[j] = 5;
			}
			tests.add(createTest(null, graph, want));
		}
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
