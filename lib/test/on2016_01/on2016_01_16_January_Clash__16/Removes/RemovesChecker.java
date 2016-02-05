package on2016_01.on2016_01_16_January_Clash__16.Removes;



import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.Verdict;
import net.egork.generated.collections.list.IntArray;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.Graph;
import net.egork.graph.StronglyConnectedComponents;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;


public class RemovesChecker implements Checker {
	static double totalScore = 0;
	static int testCount = 0;

    public RemovesChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        InputReader in = new InputReader(new StringBufferInputStream(input));
        InputReader expected;
        if (expectedOutput == null)
            expected = null;
        else
            expected = new InputReader(new StringBufferInputStream(expectedOutput));
        InputReader actual = new InputReader(new StringBufferInputStream(actualOutput));
		try {
			return check(in, expected, actual);
		} catch (InputMismatchException e) {
			return new Verdict(Verdict.VerdictType.PE, e.getMessage());
		}
    }

    public Verdict check(InputReader in, InputReader expected, InputReader actual) {
		in.readInt();
		int n = in.readInt();
		int b = in.readInt();
		int[][] c = IOUtils.readIntTable(in, n, n);
		int r = actual.readInt();
		Set<IntIntPair> was = new HashSet<>();
		for (int i = 0; i < r; i++) {
			int x = actual.readInt();
			int y = actual.readInt();
			IntIntPair key = new IntIntPair(x, y);
			if (was.contains(key)) {
				return new Verdict(Verdict.VerdictType.WA, "Duplicate edge " + i + " " + x + " " + y);
			}
			was.add(key);
			if (x == y) {
				return new Verdict(Verdict.VerdictType.WA, "Same ends " + i + " " + x);
			}
			b -= c[x][y];
			if (b < 0) {
				return new Verdict(Verdict.VerdictType.WA, "Insufficient funds " + i + " " + b);
			}
		}
		Graph graph = new Graph(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j && !was.contains(new IntIntPair(i, j))) {
					graph.addSimpleEdge(i, j);
				}
			}
		}
		int[] colors = StronglyConnectedComponents.kosaraju(graph).first;
		int[] qty = new IntArray(colors).qty();
		double curScore = -1;
		for (int i : qty) {
			curScore += Math.sqrt((double)i / n);
		}
		totalScore += curScore;
		testCount++;
		return new Verdict(Verdict.VerdictType.OK, String.format("Test score = %.3f, total score = %.3f", curScore, totalScore / testCount * 100));
    }
}
