package on2016_02.on2016_02_20_Booking_com_Hack_a_Holiday__City_Break_Edition.Weekend_away;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class WeekendAway {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        Graph graph = new BidirectionalGraph(n);
        for (int i = 0; i < m; i++) {
            int a = in.readInt() - 1;
            int b = in.readInt() - 1;
            int d = in.readInt();
            graph.addWeightedEdge(a, b, d);
        }
        long answer = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long min = Long.MAX_VALUE;
            int at = -1;
            long second = Long.MAX_VALUE;
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                long distance = graph.weight(j);
                if (graph.destination(j) != at && distance < min) {
                    second = min;
                    min = distance;
                    at = graph.destination(j);
                } else {
                    second = Math.min(second, distance);
                }
            }
            if (second != Long.MAX_VALUE) {
                answer = Math.min(answer, min + second);
            }
        }
        out.printLine(answer);
    }
}
