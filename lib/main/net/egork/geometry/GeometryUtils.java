package net.egork.geometry;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class GeometryUtils {
	public static double fastHypot(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}

	public static double missileTrajectoryLength(double v, double angle, double g) {
		return (v * v * Math.sin(2 * angle)) / g;
	}
}
