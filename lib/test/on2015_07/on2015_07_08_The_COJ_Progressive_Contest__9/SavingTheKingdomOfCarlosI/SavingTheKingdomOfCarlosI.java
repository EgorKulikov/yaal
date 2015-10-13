package on2015_07.on2015_07_08_The_COJ_Progressive_Contest__9.SavingTheKingdomOfCarlosI;



import net.egork.geometry.Point;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SavingTheKingdomOfCarlosI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        Point[] points = new Point[count];
        for (int i = 0; i < count; i++) {
            points[i] = Point.readPoint(in);
        }
        double[][] distances = new double[count][count];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                distances[i][j] = points[i].distance(points[j]);
            }
        }
        int start = in.readInt() - 1;
        double[][] answer = new double[count][1 << count];
        ArrayUtils.fill(answer, Double.POSITIVE_INFINITY);
        answer[start][1 << start] = 0;
        for (int i = 1; i < (1 << count); i++) {
            for (int j = 0; j < count; j++) {
                if ((i >> j & 1) == 0) {
                    continue;
                }
                int remaining = i - (1 << j);
                for (int k = 0; k < count; k++) {
                    if ((remaining >> k & 1) == 1) {
                        answer[j][i] = Math.min(answer[j][i], answer[k][remaining] + distances[j][k]);
                    }
                }
            }
        }
        double result = Double.POSITIVE_INFINITY;
        for (int i = 0; i < count; i++) {
            result = Math.min(result, answer[i][(1 << count) - 1]);
        }
        out.printFormat("%.2f\n", result);
    }
}
