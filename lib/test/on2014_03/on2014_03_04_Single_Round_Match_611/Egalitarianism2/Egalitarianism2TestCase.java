package on2014_03.on2014_03_04_Single_Round_Match_611.Egalitarianism2;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.numbers.IntegerUtils;

import java.util.*;

public class Egalitarianism2TestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		Random random = new Random(239);
		for (int testNumber = 0; testNumber < 10000; testNumber++) {
			int size = 3 + random.nextInt(4);
			int edgeCount = size * (size - 1) / 2;
			int[] x = new int[size];
			int[] y = new int[size];
			for (int i = 0; i < size; i++) {
				x[i] = -10 + random.nextInt(21);
				y[i] = -10 + random.nextInt(21);
			}
			double answer = Double.POSITIVE_INFINITY;
			double[] tree = new double[size - 1];
			for (int i = 0; i < (1 << edgeCount); i++) {
				if (Integer.bitCount(i) != size - 1)
					continue;
				IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(size);
				int l = 0;
				int m = 0;
				boolean good = true;
				for (int j = 0; j < size; j++) {
					for (int k = j + 1; k < size; k++) {
						if ((i >> (l++) & 1) == 0)
							continue;
						if (!setSystem.join(j, k))
							good = false;
						tree[m++] = Math.hypot(x[j] - x[k], y[j] - y[k]);
					}
				}
				if (!good)
					continue;
				double mean = 0;
				for (double d : tree)
					mean += d;
				mean /= size  - 1;
				double dev = 0;
				for (double d : tree)
					dev += (d - mean) * (d - mean);
				dev = Math.sqrt(dev / (size - 1));
				answer = Math.min(answer, dev);
			}
			tests.add(createTest(answer, x, y));
		}
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
