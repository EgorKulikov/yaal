package on2017_04.on2017_04_18_Single_Round_Match_712.AverageVarianceSubtree;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;

import static net.egork.misc.ArrayUtils.sumArray;

public class AverageVarianceSubtree {
    static class Result {
        double[] qty = new double[51];
        double[] sum = new double[51];
        double[] sumSq = new double[51];
        double[] sqSum = new double[51];
    }

    double totalQty = 0;
    double totalAnswer = 0;
    Graph graph;
    double[] weight;

    public double average(int[] p, int[] weight) {
        graph = new BidirectionalGraph(p.length + 1, p.length);
        for (int i = 0; i < p.length; i++) {
            graph.addSimpleEdge(i + 1, p[i]);
        }
        double mean = sumArray(weight) / (double)weight.length;
        this.weight = new double[weight.length];
        for (int i = 0; i < weight.length; i++) {
            this.weight[i] = weight[i] - mean;
        }
        solve(0, -1);
        return totalAnswer / totalQty;
    }

    private Result solve(int vertex, int last) {
        Result result = new Result();
        result.qty[1] = 1;
        result.sum[1] = weight[vertex];
        result.sqSum[1] = result.sumSq[1] = (double)weight[vertex] * weight[vertex];
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next != last) {
                Result call = solve(next, vertex);
                call.qty[0] = 1;
                result = unite(result, call);
            }
        }
        for (int i = 1; i <= 50; i++) {
            if (result.qty[i] != 0) {
                totalQty += result.qty[i];
                totalAnswer += result.sumSq[i] / i - result.sqSum[i] / i / i;
            }
        }
        return result;
    }

    private Result unite(Result a, Result b) {
        Result result = new Result();
        for (int i = 0; i <= 50; i++) {
            if (a.qty[i] == 0) {
                continue;
            }
            for (int j = 0; j <= 50; j++) {
                if (b.qty[j] == 0) {
                    continue;
                }
                result.qty[i + j] += a.qty[i] * b.qty[j];
                result.sum[i + j] += a.sum[i] * b.qty[j] + a.qty[i] * b.sum[j];
                result.sumSq[i + j] += a.sumSq[i] * b.qty[j] + a.qty[i] * b.sumSq[j];
                result.sqSum[i + j] += a.sqSum[i] * b.qty[j] + a.qty[i] * b.sqSum[j] + 2 * a.sum[i] * b.sum[j];
            }
        }
        return result;
    }
}
