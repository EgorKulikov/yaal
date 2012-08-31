package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Restore {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int lat1 = in.readInt();
		int long1 = in.readInt();
		int lat2 = in.readInt();
		int long2 = in.readInt();
		if (lat1 == lat2 && long1 == long2) {
			out.printLine(lat1, lat1);
			return;
		}
		Point first = getPoint(lat1, long1);
		Point second = getPoint(lat2, long2);
		double b = first.y * second.z - first.z * second.y;
		double a = first.x * second.z - first.z * second.x;
		double v1 = a * first.x + b * first.y;
		double v2 = a * second.x + b * second.y;
		if (Math.abs(v1) < GeometryUtils.epsilon || Math.abs(v2) < GeometryUtils.epsilon || (v1 < 0 ^ v2 > 0)) {
			out.printLine(Math.max(lat1, lat2), Math.min(lat1, lat2));
			return;
		}
		double xx = b;
		double yy = -a;
		double zz = first.x * second.y - second.x * first.y;
		double aa = Math.asin(Math.abs(zz) / Math.hypot(Math.hypot(xx, yy), zz));
		double angle = Math.PI / 2 - aa;
		if (lat1 + lat2 <= 0)
			angle = -angle;
		angle /= Math.PI / 180;
		if (lat1 + lat2 >= 0)
			out.printFormat("%.0f %d\n", angle, Math.min(lat1, lat2));
		else
			out.printFormat("%d %.0f\n", Math.max(lat1, lat2), angle);
	}

	private Point getPoint(double latitude, double longitude) {
		latitude /= 180 / Math.PI;
		longitude /= 180 / Math.PI;
		double z = Math.sin(latitude);
		double x = Math.cos(longitude) * Math.cos(latitude);
		double y = Math.sin(longitude) * Math.cos(latitude);
		return new Point(x, y, z);
	}

	class Point {
		double x, y, z;

		Point(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
