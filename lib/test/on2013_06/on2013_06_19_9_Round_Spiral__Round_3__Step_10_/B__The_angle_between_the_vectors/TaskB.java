package on2013_06.on2013_06_19_9_Round_Spiral__Round_3__Step_10_.B__The_angle_between_the_vectors;



import net.egork.geometry.Point;
import net.egork.geometry.Vector;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Vector first = new Vector(Point.readPoint(in));
		Vector second = new Vector(Point.readPoint(in));
		out.printFormat("%.5f\n", Math.abs(first.angleTo(second)));
    }
}
