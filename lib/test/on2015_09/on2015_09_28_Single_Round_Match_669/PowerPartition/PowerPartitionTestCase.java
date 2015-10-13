package on2015_09.on2015_09_28_Single_Round_Match_669.PowerPartition;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.misc.ArrayUtils;

import java.util.*;

public class PowerPartitionTestCase {
	private static final long MOD = (long) (1e9 + 7);
	long[][] answer = new long[1001][1001];
	int m;

    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		for (int i = 2; i <= 1000; i++) {
			tests.add(createTest(null, i, 1000000000000000000L));
		}
		int mm = 10;
		int xx = 1000;
		for (int i = 2; i <= mm; i++) {
			m = i;
			ArrayUtils.fill(answer, -1);
			for (int j = 1; j <= xx; j++) {
				int x = 1;
				while (x * i <= j) {
					x *= i;
				}
				tests.add(createTest((int)calculate(j, x), i, (long)j));
			}
		}
        return tests;
    }

	private long calculate(int total, int x) {
		if (answer[total][x] != -1) {
			return answer[total][x];
		}
		if (x == 1) {
			return answer[total][x] = 1;
		}
		answer[total][x] = calculate(total, x / m);
		if (total >= x) {
			answer[total][x] += calculate(total - x, x);
		}
		return answer[total][x] %= MOD;
	}

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
