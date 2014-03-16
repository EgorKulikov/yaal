package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;

import java.util.Arrays;

public class FamilyCrest {
    public String canBeInfinite(int[] A, int[] B, int[] C, int[] D) {
		int count = A.length;
		double[] directions = new double[count];
		for (int i = 0; i < count; i++) {
			directions[i] = Math.atan2(D[i] - B[i], C[i] - A[i]);
			if (directions[i] > Math.PI / 2)
				directions[i] -= Math.PI;
			if (directions[i] < Math.PI / 2)
				directions[i] += Math.PI;
		}
		Arrays.sort(directions);
		directions = Arrays.copyOf(directions, count + 1);
		directions[count] = directions[0] + Math.PI;
		GeometryUtils.epsilon = 1e-10;
		for (int i = 0; i < count; i++) {
			if (Math.abs(directions[i] - directions[i + 1]) < 1e-12)
				continue;
			double angle = (directions[i] + directions[i + 1]) / 2;
			Segment[] was = new Segment[count];
			for (int j = 0; j < count; j++)
				was[j] = new Segment(new Point(A[j], B[j]), new Point(C[j], D[j]));
			double dx = Math.cos(angle) * 1e-5;
			double dy = Math.sin(angle) * 1e-5;
			Segment[] will = new Segment[count];
			for (int j = 0; j < count; j++)
				will[j] = new Segment(new Point(A[j] + dx, B[j] + dy), new Point(C[j] + dx, D[j] + dy));
			boolean good = true;
			for (Segment first : was) {
				for (Segment second : will) {
					if (first.intersect(second, false) != null) {
						good = false;
						break;
					}
				}
				if (!good)
					break;
			}
			if (good)
				return "Infinite";
		}
		return "Finite";
    }
}
