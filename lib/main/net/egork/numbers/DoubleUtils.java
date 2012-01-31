package net.egork.numbers;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class DoubleUtils {
	public static double sumGeometricProgression(double p, double q) {
		return p / (1 - q);
	}
}
