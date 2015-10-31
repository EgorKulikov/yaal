package on2015_09.on2015_09_05_Booking_com_Hackathon.Nearby_Attractions;



import net.egork.geometry.Point;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class NearbyAttractions {
	private static final double PI = 3.14159265359;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long[] id = new long[n];
		Point[] attractions = new Point[n];
		for (int i = 0; i < n; i++) {
			id[i] = in.readInt();
			attractions[i] = Point.readPoint(in);
		}
		int m = in.readInt();
		int[] order = ArrayUtils.createOrder(n);
		double[] distance = new double[n];
		for (int i = 0; i < m; i++) {
			Point position = Point.readPoint(in);
			String type = in.readString();
			double speed;
			if ("metro".equals(type)) {
				speed = 20;
			} else if ("foot".equals(type)) {
				speed = 5;
			} else {
				speed = 15;
			}
			double max = in.readDouble() / 60;
			for (int j = 0; j < n; j++) {
				distance[j] = distance(position, attractions[j]);
			}
			ArrayUtils.sort(order, (a, b) -> distance[a] == distance[b] ? Long.compare(id[a], id[b]) : Double.compare(distance[a], distance[b]));
			int at = 0;
			while (at < n) {
				if (distance[order[at]] > max * speed) {
					break;
				}
				at++;
			}
			for (int j = 0; j < at; j++) {
				if (j != 0) {
					out.print(' ');
				}
				out.print(id[order[j]]);
			}
			out.printLine();
		}
	}

	double distance(Point point1, Point point2) {
		double EARTH_RADIUS = 6371;
		double point1_lat_in_radians = degree2radians(point1.x);
		double point2_lat_in_radians = degree2radians(point2.x);
		double point1_long_in_radians = degree2radians(point1.y);
		double point2_long_in_radians = degree2radians(point2.y);

		double result = Math.acos(Math.sin(point1_lat_in_radians) * Math.sin(point2_lat_in_radians) +
			Math.cos(point1_lat_in_radians) * Math.cos(point2_lat_in_radians) *
				Math.cos(point2_long_in_radians - point1_long_in_radians)) * EARTH_RADIUS;
		return Math.round(result * 100) / 100d;
	}

	private double degree2radians(double degrees) {
		return degrees / 180 * PI;
	}
}
