package on2012_10.on2012_10_28_.TaskC;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class CTestCase implements TestProvider {
	public Collection<Test> createTests() {
//		List<Test> tests = new ArrayList<Test>();
//		Random random = new Random(239);
//		for (int i = 0; i < 1000000; i++)
//			tests.add(new Test(Integer.toString(random.nextInt(1000000000) + 1)));
//		return tests;
		long answer = 0;
		for (int i = 0; i < (1 << 21); i++) {
			boolean good = false;
			for (int j = 0; j < 7; j++) {
				int current = (i >> (3 * j)) & 7;
				if (current == 0 && (i >> (3 * j)) != 0) {
					good = false;
					break;
				}
				if (current == 0)
					break;
				if (j > 0) {
					int last = (i >> (3 * (j - 1))) & 7;
					if (last == current)
						good = true;
					if (j > 1) {
						int preLast = (i >> (3 * (j - 2))) & 7;
						if (last >= preLast && last >= current || last <= preLast && last <= current)
							good = true;
					}
				}
			}
			if (good)
				answer += i;
			answer %= 1000000007;
		}
		return Collections.singleton(new Test("7", Long.toString(answer)));
	}
}
