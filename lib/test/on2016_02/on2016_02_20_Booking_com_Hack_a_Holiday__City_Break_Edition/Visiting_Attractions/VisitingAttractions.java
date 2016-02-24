package on2016_02.on2016_02_20_Booking_com_Hack_a_Holiday__City_Break_Edition.Visiting_Attractions;



import net.egork.geometry.Point;
import net.egork.misc.MiscUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class VisitingAttractions {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Point hotel = Point.readPoint(in);
        int n = in.readInt();
        Point[] attractions = new Point[n + 1];
        for (int i = 0; i < n; i++) {
            attractions[i] = Point.readPoint(in);
        }
        attractions[n] = hotel;
        int s = in.readInt();
        Point[] stations = new Point[s];
        for (int i = 0; i < s; i++) {
            stations[i] = Point.readPoint(in);
        }
        int l = in.readInt();
        int[][] lines = new int[l][];
        for (int i = 0; i < l; i++) {
            String[] tokens = in.readLine().split(" ");
            lines[i] = new int[tokens.length];
            for (int j = 0; j < lines[i].length; j++) {
                lines[i][j] = Integer.parseInt(tokens[j]) + n;
            }
        }
        double[][] distances = new double[n + s + 1][n + s + 1];
        Point[] all = copyOf(attractions, n + s + 1);
        System.arraycopy(stations, 0, all, n + 1, s);
        for (int i = 0; i < all.length; i++) {
            for (int j = 0; j < all.length; j++) {
                distances[i][j] = all[i].distance(all[j]) / 5;
            }
        }
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < lines[i].length; j++) {
                for (int k = 0; k < lines[i].length; k++) {
                    distances[lines[i][j]][lines[i][k]] = Math.min(distances[lines[i][j]][lines[i][k]],
                            all[lines[i][j]].distance(all[lines[i][k]]) / 25);
                }
            }
        }
        for (int i = 0; i < all.length; i++) {
            for (int j = 0; j < all.length; j++) {
                for (int k = 0; k < all.length; k++) {
                    distances[j][k] = Math.min(distances[j][k], distances[j][i] + distances[i][k]);
                }
            }
        }
        double[][] answer = new double[n + 1][1 << (n + 1)];
        for (int i = 1; i < (1 << (n + 1)); i++) {
            for (int j = 0; j <= n; j++) {
                if ((i >> j & 1) == 0) {
                    continue;
                }
                if (i == (1 << j)) {
                    answer[j][i] = 1;
                } else {
                    answer[j][i] = Double.POSITIVE_INFINITY;
                    for (int k = 0; k <= n; k++) {
                        if (k != j && ((i >> k & 1) == 1)) {
                            answer[j][i] = Math.min(answer[j][i], answer[k][i - (1 << j)] + distances[j][k] + 1);
                        }
                    }
                }
            }
        }
        int maxVisited = 0;
        for (int i = 0; i < (1 << n); i++) {
            if (answer[n][i + (1 << n)] <= 14) {
                maxVisited = Math.max(maxVisited, Integer.bitCount(i));
            }
        }
        out.printLine(maxVisited);
        int[] order = createArray(maxVisited, n + 1);
        int[] current = new int[maxVisited];
        double eps = 1e-7;
        for (int i = 0; i < (1 << n); i++) {
            if (answer[n][i + (1 << n)] <= 14 + eps && maxVisited == Integer.bitCount(i)) {
                int mask = i;
                double remaining = 13;
                int at = n;
                for (int j = 0; j < maxVisited; j++) {
                    for (int k = 0; k < n; k++) {
                        if ((mask >> k & 1) == 1 && answer[k][mask] <= remaining - distances[at][k] + eps) {
                            current[j] = k + 1;
                            remaining -= distances[at][k] + 1;
                            at = k;
                            mask -= 1 << k;
                            break;
                        }
                    }
                }
                for (int j = 0; j < maxVisited; j++) {
                    if (current[j] > order[j]) {
                        break;
                    }
                    if (current[j] < order[j]) {
                        System.arraycopy(current, 0, order, 0, maxVisited);
                        break;
                    }
                }
            }
        }
        out.printLine(order);
    }
}
