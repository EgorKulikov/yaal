package on2013_06.on2013_06_19_9_Round_Spiral__Round_3__Step_10_.O__The_intersection_of_two_lines;



import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskO {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a0 = in.readInt();
		int b0 = in.readInt();
		int c0 = in.readInt();
		Line first = new Line(a0, b0, c0);
		int a1 = in.readInt();
		int b1 = in.readInt();
		int c1 = in.readInt();
		Line second = new Line(a1, b1, c1);
		Point p = first.intersect(second);
		out.printFormat("%.2f %.2f\n", p.x, p.y);
    }
}
