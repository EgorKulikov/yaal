package on2014_05.on2014_05_20_Single_Round_Match_621.TreesAnalysis;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;

import java.util.*;

public class TreesAnalysisTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		tests.add(createTest(null, ArrayUtils.createArray(3999, 3999), ArrayUtils.createArray(3999, 3999)));
		int size = 10;
		Random random = new Random(239);
		int[] maskFirst = new int[size - 1];
		int[] maskSecond = new int[size - 1];
		for (int i = 0; i < 1000; i++) {
			int[] first = new int[size - 1];
			int[] second = new int[size - 1];
			for (int j = 0; j < size - 1; j++) {
				first[j] = random.nextInt(size - j - 1) + j + 1;
				second[j] = random.nextInt(size - j - 1) + j + 1;
			}
			Graph left = Graph.createGraph(size, first, ArrayUtils.createOrder(size - 1));
			Graph right = Graph.createGraph(size, second, ArrayUtils.createOrder(size - 1));
			long answer = 0;
			for (int j = 0; j < size - 1; j++) {
				maskFirst[j] = getMask(j, first[j], left);
				maskSecond[j] = getMask(j, second[j], right);
			}
			for (int j = 0; j < size - 1; j++) {
				for (int k = 0; k < size - 1; k++) {
					int both = Integer.bitCount(maskFirst[j] & maskSecond[k]);
					int onlyLeft = Integer.bitCount(maskFirst[j]) - both;
					int onlyRight = Integer.bitCount(maskSecond[k]) - both;
					int none = size - both - onlyLeft - onlyRight;
					int max = Math.max(Math.max(onlyLeft, onlyRight), Math.max(both, none));
					answer += max * max;
				}
			}
			tests.add(createTest(answer, first, second));
		}
        return tests;
    }

	private int getMask(int current, int last, Graph graph) {
		int result = 1 << current;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				result += getMask(next, current, graph);
		}
		return result;
	}

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
