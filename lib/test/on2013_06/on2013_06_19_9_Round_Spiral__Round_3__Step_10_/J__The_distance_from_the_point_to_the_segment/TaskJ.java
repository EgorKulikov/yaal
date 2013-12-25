package on2013_06.on2013_06_19_9_Round_Spiral__Round_3__Step_10_.J__The_distance_from_the_point_to_the_segment;



import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point point = Point.readPoint(in);
		Point from = Point.readPoint(in);
		Point to = Point.readPoint(in);
		Segment segment = new Segment(from, to);
		out.printFormat("%.5f\n", segment.distance(point));
    }
}
