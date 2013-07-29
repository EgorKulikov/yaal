package on2013_07.on2013_07_01_The_COJ_Progressive_Contest__6.TheAsteroids;



import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheAsteroids {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		Point ship = Point.readPoint(in);
		int answer = -1;
		double distance = Double.POSITIVE_INFINITY;
		for (int i = 0; i < count; i++) {
			Point asteroid = Point.readPoint(in);
			int radius = in.readInt();
			double current = asteroid.distance(ship) - radius;
			if (current < distance) {
				distance = current;
				answer = i + 1;
			}
		}
		out.printLine(answer);
	}
}
