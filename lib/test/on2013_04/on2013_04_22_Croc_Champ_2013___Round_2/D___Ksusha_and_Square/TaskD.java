package on2013_04.on2013_04_22_Croc_Champ_2013___Round_2.D___Ksusha_and_Square;



import net.egork.geometry.GeometryUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		GeometryUtils.epsilon = 5e-6;
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		double answer = calculate(x, y, count) + calculate(y, x, count);
		out.printLine(answer);
    }

	private double calculate(int[] x, int[] y, int count) {
		x = Arrays.copyOf(x, count + 1);
		y = Arrays.copyOf(y, count + 1);
		x[count] = x[0];
		y[count] = y[0];
		int minX = Integer.MAX_VALUE;
		int at = -1;
		int maxX = Integer.MIN_VALUE;
		for (int i = 0; i < count; i++) {
			if (x[i] < minX) {
				minX = x[i];
				at = i;
			}
			maxX = Math.max(maxX, x[i]);
		}
		long[] qty = new long[maxX - minX + 1];
		int side = at;
		int otherSide = at - 1;
		if (otherSide < 0)
			otherSide += count;
		for (int i = minX; i < maxX; i++) {
			while (x[side] <= i && x[side + 1] <= i) {
				side++;
				if (side == count)
					side = 0;
			}
			while (x[otherSide] <= i && x[otherSide + 1] <= i) {
				otherSide--;
				if (otherSide == -1)
					otherSide = count - 1;
			}
			double y0 = getY(x, y, side, i);
			double y1 = getY(x, y, otherSide, i);
			if (y0 < y1)
				qty[i - minX] = delta(y0, y1);
			else
				qty[i - minX] = delta(y1, y0);
		}
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;
		for (int i = 0; i < count; i++) {
			if (x[i] == maxX) {
				maxY = Math.max(maxY, y[i]);
				minY = Math.min(minY, y[i]);
			}
		}
		qty[maxX - minX] = maxY - minY + 1;
		double sum = 0;
		long total = 0;
		double result = 0;
		for (long i = 0; i < qty.length; i++) {
			long q = qty[((int) i)];
			sum += i * q;
			result += i * i * q;
			total += q;
		}
		result *= total;
		for (int i = 0; i < qty.length; i++)
			result -= sum * i * qty[i];
		result /= total;
		result /= total - 1;
//		result *= 2;
		return result;
	}

	private int delta(double y0, double y1) {
		return (int) (Math.round(Math.floor(y1 + GeometryUtils.epsilon)) - Math.round(Math.ceil(y0 - GeometryUtils.epsilon)) + 1);
	}

	private double getY(int[] x, int[] y, int side, int i) {
		return ((double)y[side] * Math.abs(x[side + 1] - i) + (double)y[side + 1] * Math.abs(x[side] - i)) / Math.abs(x[side] - x[side + 1]);
	}
}
