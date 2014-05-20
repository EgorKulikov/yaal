package on2014_05.on2014_05_20_Single_Round_Match_621.StringsNightmareAgain;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.collections.set.EHashSet;
import net.egork.misc.ArrayUtils;

import java.util.*;

public class StringsNightmareAgainTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		int size = 100;
		Random random = new Random(239);
		for (int i = 0; i < 10000; i++) {
			int n = random.nextInt(size) + 1;
			int a = random.nextInt(n + 1);
			int b = random.nextInt(1000001);
			int c = random.nextInt(1000001);
			int d = random.nextInt(1000001);
			int ob = b;
			char[] s = ArrayUtils.createArray(n, 'a');
			for (int j = 0; j < a; j++) {
				b = (int) (((long)b * c + d) % n);
				s[b] = 'b';
			}
			String ss = new String(s);
			Set<String> set = new EHashSet<String>();
			for (int j = 0; j < n; j++) {
				for (int k = 1; j + k <= n; k++) {
					for (int l = j + k; l + k <= n; l++) {
						if (ss.substring(j, j + k).equals(ss.substring(l, l + k)))
							set.add(ss.substring(j, j + k));
					}
				}
			}
			tests.add(createTest((long)set.size(), a, ob, c, d, n));
		}
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
