package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.misc.MiscUtils;
import net.egork.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PlacingKnightsTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 0;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            out.printLine(100);
            for (int m = 1; m <= 10; m++) {
                for (int l = 1; l <= 10; l++) {
                    int rowCount = m;
                    int columnCount = l;
                    Graph graph = new Graph(rowCount * columnCount + 2);
                    for (int i = 0; i < rowCount; i++) {
                        for (int j = i & 1; j < columnCount; j += 2) {
                            graph.addFlowEdge(rowCount * columnCount, i * columnCount + j, 1);
                            for (int k = 0; k < 8; k++) {
                                int ni = i + MiscUtils.DX_KNIGHT[k];
                                int nj = j + MiscUtils.DY_KNIGHT[k];
                                if (MiscUtils.isValidCell(ni, nj, rowCount, columnCount)) {
                                    graph.addFlowEdge(i * columnCount + j, ni * columnCount + nj, 1);
                                }
                            }
                        }
                        for (int j = 1 - (i & 1); j < columnCount; j += 2) {
                            graph.addFlowEdge(i * columnCount + j, rowCount * columnCount + 1, 1);
                        }
                    }
                    out.printLine(m, l);
                    outAnswer.printLine("Case", (m * 10 + l - 10) + ":", rowCount * columnCount - MaxFlow.dinic(graph, rowCount * columnCount, rowCount * columnCount + 1));
                }
            }
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
