package on2015_03.on2015_03_17_Single_Round_Match_653.Singing;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.numbers.IntegerUtils;

import java.util.*;

public class SingingTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		for (int N = 1; N <= 6; N++) {
			int M = 4;
			int[] pitch = new int[N];
			for (int i = 0; i < (1 << (2 * N)); i++) {
				for (int j = 0; j < N; j++) {
					pitch[j] = 1 + (i >> (2 * j) & 3);
				}
				for (int low = 1; low <= M; low++) {
					for (int high = low; high <= M; high++) {
						int answer = Integer.MAX_VALUE;
						for (int k = 0; k < (1 << M); k++) {
							boolean bad = false;
							for (int l = 0; l < low - 1; l++) {
								if ((k >> l & 1) == 1) {
									bad = true;
									break;
								}
							}
							if (bad) {
								continue;
							}
							for (int l = high; l < M; l++) {
								if ((k >> l & 1) == 0) {
									bad = true;
									break;
								}
							}
							if (bad) {
								continue;
							}
							int score = 0;
							for (int l = 1; l < N; l++) {
								if ((k >> (pitch[l] - 1) & 1) != (k >> (pitch[l - 1] - 1) & 1)) {
									score++;
								}
							}
							answer = Math.min(answer, score);
						}
						tests.add(createTest(answer, M, low, high, pitch.clone()));
					}
				}
			}
		}
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
