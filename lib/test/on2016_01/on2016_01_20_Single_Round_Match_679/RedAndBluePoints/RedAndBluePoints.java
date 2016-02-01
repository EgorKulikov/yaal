package on2016_01.on2016_01_20_Single_Round_Match_679.RedAndBluePoints;



import net.egork.collections.intcollection.Range;
import net.egork.geometry.*;
import net.egork.misc.ArrayUtils;

public class RedAndBluePoints {
	public int find(int[] blueX, int[] blueY, int[] redX, int[] redY) {
		Point[] good = new Point[blueX.length];
		for (int i = 0; i < good.length; i++) {
			good[i] = new Point(blueX[i], blueY[i]);
		}
		Point[] bad = new Point[redX.length];
		for (int i = 0; i < bad.length; i++) {
			bad[i] = new Point(redX[i], redY[i]);
		}
		int[][][] delta = new int[blueX.length][blueX.length][blueX.length];
		for (int i = 0; i < good.length; i++) {
			for (int j = 0; j < good.length; j++) {
				if (i == j) {
					continue;
				}
				for (int k = 0; k < good.length; k++) {
					if (i == k || j == k) {
						continue;
					}
//					Polygon polygon = new Polygon(good[i], good[j], good[k]);
					Line[] lines = {good[i].line(good[j]), good[j].line(good[k]), good[k].line(good[i])};
					Segment accountedFor = new Segment(good[i], good[j]);
					for (Point aBad : bad) {
						if (contains(lines, aBad)) {
							delta[i][j][k] = -1;
							break;
						}
					}
					if (delta[i][j][k] == -1) {
						continue;
					}
					delta[i][j][k] = 1;
					for (Point aGood : good) {
						if (contains(lines, aGood) && !accountedFor.contains(aGood, true)) {
							delta[i][j][k]++;
						}
					}
				}
			}
		}
//		System.err.println("Pre--------------");
		int result = Math.min(2, good.length);
		int[][] answer = new int[good.length][good.length];
		double[] angle = new double[good.length];
		int[] order = Range.range(good.length).toArray();
		for (int i = 0; i < good.length; i++) {
			ArrayUtils.fill(answer, 0);
			for (int j = 0; j < good.length; j++) {
				if (i == j) {
					angle[j] = Double.NEGATIVE_INFINITY;
				} else {
					angle[j] = -Math.atan2(good[j].y - good[i].y, good[j].x - good[i].x);
				}
			}
			ArrayUtils.sort(order, (a, b) -> Double.compare(angle[a], angle[b]));
			for (int at = 0; at < order.length; at++) {
				int j = order[at];
				if (good[j].y < good[i].y || i == j) {
					continue;
				}
				if (good[j].y > good[i].y) {
					Segment segment = new Segment(good[i], good[j]);
					for (int k = 0; k < good.length; k++) {
						if (segment.contains(good[k], true)) {
							answer[i][j]++;
						}
					}
					for (int k = 0; k < bad.length; k++) {
						if (segment.contains(bad[k], true)) {
							answer[i][j] = 0;
							break;
						}
					}
				}
				for (int k : order) {
					if (j == k) {
						break;
					}
					if (answer[k][j] != 0) {
						result = Math.max(result, answer[k][j]);
						for (int l = at + 1; l < good.length; l++) {
							int current = order[l];
							if (delta[i][j][current] != -1 && Polygon.over(good[k], good[j], good[current])) {
								answer[j][current] = Math.max(answer[j][current], answer[k][j] + delta[i][j][current]);
							}
						}
					}
				}
			}
		}
		return result;
	}

	private boolean contains(Line[] lines, Point point) {
		double[] d = new double[3];
		for (int i = 0; i < 3; i++) {
			d[i] = lines[i].value(point);
			if (Math.abs(d[i]) < GeometryUtils.epsilon) {
				return false;
			}
		}
		for (int i = 1; i < 3; i++) {
			if (Math.signum(d[i]) != Math.signum(d[0])) {
				return false;
			}
		}
		return true;
	}
}
