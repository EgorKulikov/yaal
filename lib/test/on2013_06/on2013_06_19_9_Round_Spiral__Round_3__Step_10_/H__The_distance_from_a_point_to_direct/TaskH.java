package on2013_06.on2013_06_19_9_Round_Spiral__Round_3__Step_10_.H__The_distance_from_a_point_to_direct;



import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point point = Point.readPoint(in);
		Point from = Point.readPoint(in);
		Point to = Point.readPoint(in);
		Line line = from.line(to);
		out.printFormat("%.6f\n", line.distance(point));
    }
}
