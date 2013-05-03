package on2012_12.on2012_12_22_Codeforces_Round__157.D___Little_Elephant_and_Broken_Sorting;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.*;

public class DTestCase implements TestProvider {
	public Collection<Test> createTests() {
		int testCount = 10;
		int size = 3;
		int permutations = 3;
		List<Test> tests = new ArrayList<Test>();
		Random random = new Random(239);
		for (int i = 0; i < testCount; i++) {
			StringWriter sw = new StringWriter();
			OutputWriter out = new OutputWriter(sw);
			out.printLine(size, permutations);
			int[] permutation = ArrayUtils.createOrder(size);
			for (int j = 1; j <= size; j++)
				permutation[j - 1] = j;
			for (int j = 1; j < size; j++) {
				int index = random.nextInt(j + 1);
				int temp = permutation[index];
				permutation[index] = permutation[j];
				permutation[j] = temp;
			}
			out.printLine(permutation);
			int[] from = new int[permutations];
			int[] to = new int[permutations];
			for (int j = 0; j < permutations; j++) {
				do {
					from[j] = random.nextInt(size) + 1;
					to[j] = random.nextInt(size) + 1;
				} while (from[j] == to[j]);
				out.printLine(from[j], to[j]);
			}
			int[] current = new int[size];
			double answer = 0;
			for (int j = 0; j < (1 << permutations); j++) {
				System.arraycopy(permutation, 0, current, 0, size);
				for (int k = 0; k < permutations; k++) {
					if ((j >> k & 1) == 1) {
						int temp = current[from[k] - 1];
						current[from[k] - 1] = current[to[k] - 1];
						current[to[k] - 1] = temp;
					}
				}
				for (int k = 0; k < size; k++) {
					for (int l = k + 1; l < size; l++) {
						if (current[k] > current[l])
							answer++;
					}
				}
			}
			answer /= 1 << permutations;
			tests.add(new Test(sw.toString(), Double.toString(answer)));
		}
		return tests;
	}
}
