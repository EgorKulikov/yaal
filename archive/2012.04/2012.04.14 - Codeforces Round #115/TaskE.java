package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	int n = 12;

	private int radiusFire;
	private int radiusElectric;
	private int radiusSlow;
	private int damageFire;
	private int damageElectric;
	int[] slowAt;
	private int countFire;
	private int countElectric;
	double[][][] result;
	double[] slowPoints;
	int[] slowOrder = new int[2 * n];
	double[] scoreFire = new double[n];
	double[] scoreElectric = new double[n];
	int[] countSlow = new int[2 * n + 1];
	private double[][] intersectFire;
	private double[][] intersectElectric;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		countFire = in.readInt();
		countElectric = in.readInt();
		int countSlow = in.readInt();
		radiusFire = in.readInt();
		radiusElectric = in.readInt();
		radiusSlow = in.readInt();
		if (radiusSlow == 1)
			countSlow = 0;
		damageFire = in.readInt();
		damageElectric = in.readInt();
		slowAt = new int[n];
		result = new double[n + 1][countFire + 1][countElectric + 1];
		slowPoints = new double[2 * n];
		for (int i = 0; i < n; i++) {
			slowPoints[2 * i] = i - Math.sqrt(radiusSlow * radiusSlow - 1);
			slowPoints[2 * i + 1] = i + Math.sqrt(radiusSlow * radiusSlow - 1);
		}
		Integer[] reverseSlowOrder = ListUtils.order(Array.wrap(slowPoints));
		for (int i = 0; i < 2 * n; i++)
			slowOrder[reverseSlowOrder[i]] = i;
		Arrays.sort(slowPoints);
		intersectFire = new double[n][2 * n + 1];
		intersectElectric = new double[n][2 * n + 1];
		for (int i = 0; i < n; i++) {
			double start = i - Math.sqrt(radiusFire * radiusFire - 1);
			double end = i + Math.sqrt(radiusFire * radiusFire - 1);
			for (int j = 0; j < 2 * n + 1; j++) {
				double curStart = j == 0 ? Double.NEGATIVE_INFINITY : slowPoints[j - 1];
				double curFinish = j == 2 * n ? Double.POSITIVE_INFINITY : slowPoints[j];
				intersectFire[i][j] = Math.max(0, Math.min(end, curFinish) - Math.max(start, curStart));
			}
			start = i - Math.sqrt(radiusElectric * radiusElectric - 1);
			end = i + Math.sqrt(radiusElectric * radiusElectric - 1);
			for (int j = 0; j < 2 * n + 1; j++) {
				double curStart = j == 0 ? Double.NEGATIVE_INFINITY : slowPoints[j - 1];
				double curFinish = j == 2 * n ? Double.POSITIVE_INFINITY : slowPoints[j];
				intersectElectric[i][j] = Math.max(0, Math.min(end, curFinish) - Math.max(start, curStart));
			}
		}
		out.printLine(go(0, countSlow));
	}

	private double go(int step, int remaining) {
		if (step == slowAt.length) {
			if (remaining == 0)
				return count();
			return 0;
		}
		double result = 0;
		for (int i = 0; i <= 2 && i <= remaining; i++) {
			slowAt[step] = i;
			result = Math.max(result, go(step + 1, remaining - i));
		}
		return result;
	}

	private double count() {
		Arrays.fill(countSlow, 1);
		for (int i = 0; i < n; i++) {
			if (slowAt[i] != 0) {
				for (int j = slowOrder[2 * i]; j < slowOrder[2 * i + 1]; j++)
					countSlow[j + 1] += slowAt[i];
			}
		}
		for (int i = 0; i < n; i++) {
			scoreFire[i] = 0;
			scoreElectric[i] = 0;
			for (int j = 0; j < 2 * n + 1; j++) {
				scoreFire[i] += intersectFire[i][j] * countSlow[j] * damageFire;
				scoreElectric[i] += intersectElectric[i][j] * countSlow[j] * damageElectric;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= countFire; j++) {
				for (int k = 0; k <= countElectric; k++) {
					result[i + 1][j][k] = result[i][j][k];
					if (slowAt[i] == 2)
						continue;
					if (j != countFire) {
						result[i + 1][j][k] = Math.max(result[i + 1][j][k], result[i][j + 1][k] + scoreFire[i]);
						if (slowAt[i] == 0) {
							if (j + 2 <= countFire)
								result[i + 1][j][k] = Math.max(result[i + 1][j][k], result[i][j + 2][k] + scoreFire[i] * 2);
							if (k < countElectric)
								result[i + 1][j][k] = Math.max(result[i + 1][j][k], result[i][j + 1][k + 1] + scoreFire[i] + scoreElectric[i]);
						}
					}
					if (k != countElectric) {
						result[i + 1][j][k] = Math.max(result[i + 1][j][k], result[i][j][k + 1] + scoreElectric[i]);
						if (slowAt[i] == 0 && k + 2 <= countElectric)
							result[i + 1][j][k] = Math.max(result[i + 1][j][k], result[i][j][k + 2] + 2 * scoreElectric[i]);
					}
				}
			}
		}
		return result[n][0][0];
	}
}
