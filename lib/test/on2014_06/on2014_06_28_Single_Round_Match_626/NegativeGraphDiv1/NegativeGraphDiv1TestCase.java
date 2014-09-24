package on2014_06.on2014_06_28_Single_Round_Match_626.NegativeGraphDiv1;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class NegativeGraphDiv1TestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		int N = 4 + 18 + 19;
		int[] from = new int[N + 1];
		int[] to = new int[N + 1];
		int[] weight = new int[N + 1];
		int at = 0;
		from[0] = 1;
		to[0] = 2;
		weight[0] = 1;
		from[1] = 2;
		to[1] = 3;
		weight[1] = 1;
		from[2] = 3;
		to[2] = N;
		weight[2] = 1;
		at = 3;
		for (int i = 5; i <= 21; i++) {
			from[at] = i - 1;
			to[at] = i;
			weight[at++] = 1000;
		}
		from[at] = 2;
		to[at] = 4;
		weight[at++] = 1000;
		from[at] = 21;
		to[at] = 2;
		weight[at++] = 1000;
		for (int i = 23; i <= N - 1; i++) {
			from[at] = i - 1;
			to[at] = i;
			weight[at++] = 1000;
		}
		from[at] = 3;
		to[at] = 22;
		weight[at++] = 1000;
		from[at] = N - 1;
		to[at] = 3;
		weight[at] = 1000;
		tests.add(createTest(3 - (19 * 3 + 20 * 3) * 1000L, N, from, to, weight, 19 * 3 + 20 * 3));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
