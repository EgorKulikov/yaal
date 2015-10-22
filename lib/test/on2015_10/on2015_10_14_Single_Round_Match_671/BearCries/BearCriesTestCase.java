package on2015_10.on2015_10_14_Single_Round_Match_671.BearCries;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.misc.ArrayUtils;

import java.util.*;

public class BearCriesTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		Random random = new Random(239);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 200; i++) {
			if (random.nextBoolean()) {
				builder.append(';');
			} else {
				builder.append('_');
			}
		}
		tests.add(createTest(null, builder.toString()));
		int length = 10;
		for (int i = 1; i <= length; i++) {
			for (int j = 0; j < (1 << i); j++) {
				StringBuilder b = new StringBuilder();
				for (int k = 0; k < i; k++) {
					if ((j >> k & 1) == 1) {
						b.append(';');
					} else {
						b.append('_');
					}
				}
				tests.add(createTest(count(b.toString()), b.toString()));
			}
		}
		return tests;
    }

	private static final long MOD = (long) (1e9 + 7);
 char[] message;
 long[][][] ways;

 public int count(String message) {
     this.message = message.toCharArray();
     ways = new long[message.length() + 1][message.length() + 1][message.length() + 1];
     ArrayUtils.fill(ways, -1);
return (int)go(0, 0, 0);
 }

 long go(int position, int good, int bad) {
     if (good < 0 || bad < 0) {
         return 0;
     }
     if (ways[position][good][bad] != -1) {
         return ways[position][good][bad];
     }
     if (position == message.length) {
         return ways[position][good][bad] = good == 0 && bad == 0 ? 1 : 0;
     }
     if (message[position] == '_') {
         return ways[position][good][bad] = (bad * go(position + 1, good, bad) + bad * go(position + 1, good + 1, bad - 1)) % MOD;
     }
     return ways[position][good][bad] = (go(position + 1, good, bad + 1) + good * go(position + 1, good - 1, bad)) % MOD;
 }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
