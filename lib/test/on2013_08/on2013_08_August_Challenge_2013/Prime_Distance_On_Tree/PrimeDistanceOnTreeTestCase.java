package on2013_08.on2013_08_August_Challenge_2013.Prime_Distance_On_Tree;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PrimeDistanceOnTreeTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
		{
			StringWriter sw = new StringWriter();
			OutputWriter out = new OutputWriter(sw);
			out.printLine(50000);
			for (int i = 1; i < 50000; i++)
				out.printLine(i + 1, i);
			tests.add(new Test(sw.toString()));
		}
        return tests;
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			out.printLine(50000);
			for (int i = 2; i <= 50000; i++)
				out.printLine(1, i);
			long total = (long)49999 * 49998 / 2;
			long all = (long)50000 * 49999 / 2;
			outAnswer.printLine((double)total / all);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
		{
			StringWriter sw = new StringWriter();
			OutputWriter out = new OutputWriter(sw);
			StringWriter swAnswer = new StringWriter();
			OutputWriter outAnswer = new OutputWriter(swAnswer);
			out.printLine(50000);
			for (int i = 1; i < 50000; i++)
				out.printLine(50000, i);
			long total = (long)49999 * 49998 / 2;
			long all = (long)50000 * 49999 / 2;
			outAnswer.printLine((double)total / all);
			tests.add(new Test(sw.toString(), swAnswer.toString()));
		}
		{
			StringWriter sw = new StringWriter();
			OutputWriter out = new OutputWriter(sw);
			StringWriter swAnswer = new StringWriter();
			OutputWriter outAnswer = new OutputWriter(swAnswer);
			int n = 100;
			out.printLine(n);
			Graph graph = new BidirectionalGraph(n, n - 1);
			for (int i = 1; i < n; i++) {
				int to = random.nextInt(i);
				out.printLine(i + 1, to + 1);
				graph.addWeightedEdge(i, to, 1);
			}
			int good = 0;
			for (int i = 0; i < n; i++) {
				long[] distance = ShortestDistance.dijkstraAlgorithm(graph, i).first;
				for (int j = 0; j < n; j++) {
					if (IntegerUtils.isPrime(distance[j]))
						good++;
				}
			}
			outAnswer.printLine((double)good / n / (n - 1));
			tests.add(new Test(sw.toString(), swAnswer.toString()));
		}
        return tests;
    }
}
