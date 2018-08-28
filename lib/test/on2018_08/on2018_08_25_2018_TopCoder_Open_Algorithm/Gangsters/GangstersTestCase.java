package on2018_08.on2018_08_25_2018_TopCoder_Open_Algorithm.Gangsters;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

import static java.lang.Integer.bitCount;
import static net.egork.misc.ArrayUtils.createOrder;
import static net.egork.misc.ArrayUtils.nextPermutation;

public class GangstersTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
        for (int n = 3; n <= 10; n++) {
            int[] answer = new int[n + 1];
            int[] order = createOrder(n);
            do {
                int alive = (1 << n) - 1;
                for (int i : order) {
                    if ((alive >> i & 1) == 1) {
                        int next = (i + 1) % n;
                        alive -= 1 << next;
                    }
                }
                answer[bitCount(alive)]++;
            } while (nextPermutation(order));
            for (int j = 1; j <= n; j++) {
                tests.add(createTest(answer[j], n, j));
            }
        }
        return tests;
    }

    private NewTopCoderTest createTest(Object answer, Object...arguments) {
        return new NewTopCoderTest(arguments, answer);
    }
}
