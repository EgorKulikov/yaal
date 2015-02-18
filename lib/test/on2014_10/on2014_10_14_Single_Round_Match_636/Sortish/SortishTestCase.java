package on2014_10.on2014_10_14_Single_Round_Match_636.Sortish;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;

import java.util.*;

public class SortishTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		int[] seq = new int[2000];
		for (int i = 0; i < seq.length; i++) {
			seq[i] = i + 1;
		}
		Random random = new Random(239);
		for (int i = 1; i < seq.length; i++) {
			int at = random.nextInt(i + 1);
			int temp = seq[i];
			seq[i] = seq[at];
			seq[at] = temp;
		}
		IntSet was = new IntHashSet();
		for (int i = 0; i < 14; i++) {
			int id;
			do {
				id = random.nextInt(2000);
			} while (was.contains(id));
			was.add(id);
			seq[id] = 0;
		}
		tests.add(createTest(null, 1000000, seq));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
