package on2013_06.on2013_06_19_9_Round_Spiral__Round_3__Step_10_.G__The_situation_points_out_the_direct;



import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point first = Point.readPoint(in);
		Point second = Point.readPoint(in);
		double a = in.readDouble();
		double b = in.readDouble();
		double c = in.readDouble();
		Line line = new Line(a, b, c);
		out.printLine(line.value(first) * line.value(second) > 0 ? "YES" : "NO");
    }
}
