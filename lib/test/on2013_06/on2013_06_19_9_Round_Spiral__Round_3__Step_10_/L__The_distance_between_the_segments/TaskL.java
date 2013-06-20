package on2013_06.on2013_06_19_9_Round_Spiral__Round_3__Step_10_.L__The_distance_between_the_segments;



import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskL {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point from = Point.readPoint(in);
		Point to = Point.readPoint(in);
		Segment first = new Segment(from, to);
		from = Point.readPoint(in);
		to = Point.readPoint(in);
		Segment second = new Segment(from, to);
		out.printLine(first.distance(second));
    }
}
