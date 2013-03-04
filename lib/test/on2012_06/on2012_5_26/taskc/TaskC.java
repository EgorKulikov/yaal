package on2012_06.on2012_5_26.taskc;



import net.egork.geometry.Circle;
import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int xp = in.readInt();
        int yp = in.readInt();
        int vp = in.readInt();
        int x = in.readInt();
        int y = in.readInt();
        int v = in.readInt();
        int r = in.readInt();
        double left = 0;
        double R = Math.hypot(xp, yp);
        double right = Math.abs(Math.hypot(x, y) - R) / v + 2 * Math.PI * R / vp;
        double alpha = Math.atan2(yp, xp);
        Circle danger = new Circle(new Point(0, 0), r);
        Point ship = new Point(x, y);
        Point[] shipOrbit = danger.findTouchingPoints(ship);
        if (shipOrbit.length != 2)
            throw new RuntimeException();
        for (int i = 0; i < 1000; i++) {
            double time = (left + right) / 2;
            double currentAlpha = alpha + vp / R * time;
            double cx = Math.cos(currentAlpha) * R;
            double cy = Math.sin(currentAlpha) * R;
            Point planet = new Point(cx, cy);
            double distance;
            if (planet.distance(ship) < GeometryUtils.epsilon || new Segment(planet, ship).intersect(danger).length == 0)
                distance = planet.distance(ship);
            else {
                distance = Double.POSITIVE_INFINITY;
                Point[] planetOrbit = danger.findTouchingPoints(planet);
                if (planetOrbit.length != 2)
                    throw new RuntimeException();
                for (Point first : shipOrbit) {
                    for (Point second : planetOrbit) {
                        double angle = Math.atan2(first.y, first.x) - Math.atan2(second.y, second.x);
                        while (angle > 2 * Math.PI)
                            angle -= 2 * Math.PI;
                        while (angle < 0)
                            angle += 2 * Math.PI;
                        if (angle > Math.PI)
                            angle = 2 * Math.PI - angle;
                        distance = Math.min(distance, first.distance(ship) + second.distance(planet) + angle * r);
                    }
                }
            }
            if (distance < v * time)
                right = time;
            else
                left = time;
        }
        out.printFormat("%.9f\n", (left + right) / 2);
	}
}
