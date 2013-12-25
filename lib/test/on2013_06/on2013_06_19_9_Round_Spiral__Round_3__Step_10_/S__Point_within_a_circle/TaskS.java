package on2013_06.on2013_06_19_9_Round_Spiral__Round_3__Step_10_.S__Point_within_a_circle;



import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskS {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point center = Point.readPoint(in);
		int radius = in.readInt();
		Point point = Point.readPoint(in);
		out.printLine(center.distance(point) < radius + GeometryUtils.epsilon ? "YES" : "NO");
    }
}
