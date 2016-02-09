package on2016_01.on2016_01_23_Facebook_Hacker_Cup_2016_Round_2.Costly_Labels;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class CostlyLabelsTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            out.printLine(30);
            int branching = 29;
            for (int i = 0; i < 30; i++) {
                out.printLine(1000, 30, random.nextInt(1000001));
                for (int j = 0; j < 1000; j++) {
                    for (int k = 0; k < 30; k++) {
                        out.print(random.nextInt(1000001) + " ");
                    }
                    out.printLine();
                }
                for (int j = 1; j < 1000; ++j) {
                    out.printLine((j + 1) + " " + ((j - 1) / branching + 1));
                }            }
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 100;
        int n = 10;
        int k = 3;
        int mc = 10;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            out.printLine(1);
            int p = random.nextInt(mc);
            out.printLine(n, k, p);
            int[][] c = new int[n][k];
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < k; l++) {
                    c[j][l] = random.nextInt(mc);
                    out.print(c[j][l] + " ");
                }
                out.printLine();
            }
            Graph graph = new BidirectionalGraph(n);
            for (int j = 1; j < n; j++) {
                int i = random.nextInt(j);
                graph.addSimpleEdge(j, i);
                out.printLine(j + 1, i + 1);
            }
            int[] col = new int[n];
            outAnswer.printLine("Case #1:", dumb(n, k, p, c, graph, 0, col));
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }

    private long dumb(int n, int k, int p, int[][] c, Graph graph, int step, int[] col) {
        if (step == n) {
            long answer = 0;
            for (int i = 0; i < n; i++) {
                answer += c[i][col[i]];
                int mask = 0;
                for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                    int next = graph.destination(j);
                    int cc = col[next];
                    if ((mask >> cc & 1) == 1) {
                        answer += p;
                        break;
                    }
                    mask += 1 << cc;
                }
            }
            return answer;
        }
        long answer = Long.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            col[step] = i;
            answer = Math.min(answer, dumb(n, k, p, c, graph, step + 1, col));
        }
        return answer;
    }
}
