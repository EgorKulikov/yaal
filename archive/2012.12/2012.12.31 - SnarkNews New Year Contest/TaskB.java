package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		double radius = in.readDouble();
		double mainDensity = in.readDouble();
		double lowDensity = in.readDouble();
		double upDensity = in.readDouble();
		double cylinderRadius = in.readDouble();
		double waterDensity = in.readDouble();
		double height = in.readDouble();
		if (lowDensity < upDensity) {
			double temp = lowDensity;
			lowDensity = upDensity;
			upDensity = temp;
		}
		double v0 = radius * radius * radius / 48 * Math.PI;
		double m = 62 * v0 * mainDensity + v0 * lowDensity + v0 * upDensity;
		double totalDensity = (62 * mainDensity + upDensity + lowDensity) / 64;
		double left = 0;
		double right = 2 * radius;
		for (int i = 0; i < 200; i++) {
			double middle = (left + right) / 2;
			double v = Math.PI * middle * middle * (radius - middle / 3);
			double water = Math.PI * cylinderRadius * cylinderRadius * height + v;
			if (water < Math.PI * cylinderRadius * cylinderRadius * middle) {
				right = middle;
				continue;
			}
			double downMass = v * mainDensity;
			double hLow = middle - radius / 4;
			hLow = Math.max(hLow,  0);
			hLow = Math.min(hLow, radius / 2);
			double vLow = Math.PI * hLow * hLow * (radius / 4 - hLow / 3);
			double hUp = middle - 5 * radius / 4;
			hUp = Math.max(hUp,  0);
			hUp = Math.min(hUp, radius / 2);
			double vUp = Math.PI * hUp * hUp * (radius / 4 - hUp / 3);
			downMass -= (vUp + vLow) * mainDensity;
			downMass += vUp * upDensity + vLow * lowDensity;
//			if (totalDensity / waterDensity > m / (m - downMass))
//				left = middle;
//			else
//				right = middle;
			if (m > waterDensity * v)
				left = middle;
			else
				right = middle;
		}
		double answer = (left + right) / 2;
		double v = Math.PI * answer * answer * (radius - answer / 3);
		double remainingWater = Math.PI * cylinderRadius * cylinderRadius * height + v - Math.PI * cylinderRadius * cylinderRadius * answer;
		answer += remainingWater / (Math.PI * cylinderRadius * cylinderRadius);
		out.printLine(answer);
    }
}
