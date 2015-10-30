package on2015_06.on2015_06_27_CodeChef_Snackdown_2015___Final_Round.WrongMaximalIndependentSet;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class WrongMaximalIndependentSet {
    double[][][] willTake;
    Graph graph;
    long[][] c;

    double[][][] joinArrays;
    boolean[][] done;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] from = new int[count - 1];
        int[] to = new int[count - 1];
        IOUtils.readIntArrays(in, from, to);
        MiscUtils.decreaseByOne(from, to);
        graph = BidirectionalGraph.createGraph(count, from, to);
        willTake = new double[count][count + 1][];
        c = IntegerUtils.generateBinomialCoefficients(count + 1);
        double answer = 0;
        joinArrays = new double[count][count][count];
        done = new boolean[count][count];
        for (int i = 0; i < count; i++) {
            double[] result = go(i, count);
            for (double d : result) {
                answer += d;
            }
        }
        out.printLine(answer);
    }

    private double[] go(int vertex, int last) {
        if (willTake[vertex][last] != null) {
            return willTake[vertex][last];
        }
        double[] result = new double[1];
        result[0] = 1;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            if (graph.destination(i) != last) {
                double[] go = go(graph.destination(i), vertex).clone();
                for (int j = 1; j < go.length; j++) {
                    go[j] += go[j - 1];
                }
                result = join(result, go);
            }
        }
        return willTake[vertex][last] = result;
    }

    double[] left;
    double[] right;

    private double[] join(double[] left, double[] right) {
        ArrayUtils.fill(done, false);
        this.left = left;
        this.right = right;
        doJoin(0, 0);
        return Arrays.copyOf(joinArrays[0][0], left.length + right.length);
    }

    private void doJoin(int lId, int rId) {
        if (done[lId][rId]) {
            return;
        }
        done[lId][rId] = true;
        if (lId == left.length) {
            Arrays.fill(joinArrays[lId][rId], 0, right.length - rId, 0);
        } else {
            if (rId == right.length) {
                doJoin(lId + 1, rId);
                joinArrays[lId][rId][0] = left[lId] * (1 - right[rId - 1]);
                System.arraycopy(joinArrays[lId + 1][rId], 0, joinArrays[lId][rId], 1, left.length + right.length - lId - rId - 1);
            } else {
                doJoin(lId + 1, rId);
                doJoin(lId, rId + 1);
                double probLeft = (double)(left.length - lId) / (left.length + right.length - lId - rId);
                double probRight = 1 - probLeft;
                joinArrays[lId][rId][0] = left[lId] * (1 - (rId == 0 ? 0 : right[rId - 1])) * probLeft;
                for (int i = 1; i < left.length + right.length - lId - rId; i++) {
                    joinArrays[lId][rId][i] = probLeft * joinArrays[lId + 1][rId][i - 1] +
                        probRight * joinArrays[lId][rId + 1][i - 1];
                }
            }
        }
    }
}
