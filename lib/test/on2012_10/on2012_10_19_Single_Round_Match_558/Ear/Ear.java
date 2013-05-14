package on2012_10.on2012_10_19_Single_Round_Match_558.Ear;



import net.egork.misc.ArrayUtils;
import net.egork.string.StringUtils;

import java.util.Arrays;

public class Ear {
	public long getCount(String[] redX, String[] blueX, String[] blueY) {
		int[] rx = toArray(redX);
		int[] bx = toArray(blueX);
		int[] by = toArray(blueY);
		Arrays.sort(rx);
		int[] order = ArrayUtils.order(by);
		long answer = 0;
		for (int ii = 0; ii < bx.length; ii++) {
			int i = order[ii];
			for (int jj = 0; jj < ii; jj++) {
				int j = order[jj];
				if (by[i] == by[j])
					continue;
				int a = by[j] - by[i];
				int b = bx[i] - bx[j];
				int c = a * bx[i] + b * by[i];
				double xx = (double)c / a;
				int leftxi;
				int rightxi;
				int leftxj = bx[j] - 1;
				int rightxj = bx[j] + 1;
				if (bx[i] > bx[j]) {
					leftxi = (int) Math.round(Math.floor(xx - 1e-8));
					rightxi = bx[i] + 1;
				} else {
					rightxi = (int) Math.round(Math.ceil(xx + 1e-8));
					leftxi = bx[i] - 1;
				}
				long leftValid = Arrays.binarySearch(rx, leftxi);
				if (leftValid >= 0)
					leftValid++;
				else
					leftValid = -leftValid - 1;
				long leftSupplement = Arrays.binarySearch(rx, leftxj);
				if (leftSupplement >= 0)
					leftSupplement++;
				else
					leftSupplement = -leftSupplement - 1;
				long rightValid = Arrays.binarySearch(rx, rightxi);
				if (rightValid < 0)
					rightValid = -rightValid - 1;
				rightValid = rx.length - rightValid;
				long rightSupplement = Arrays.binarySearch(rx, rightxj);
				if (rightSupplement < 0)
					rightSupplement = -rightSupplement - 1;
				rightSupplement = rx.length - rightSupplement;
				long left = leftValid * (2 * leftSupplement - leftValid - 1) / 2;
				long right = rightValid * (2 * rightSupplement - rightValid - 1) / 2;
				answer += left * right;
			}
		}
		return answer;
	}

	private int[] toArray(String[] strings) {
		String s = StringUtils.unite(strings);
		String[] tokens = s.split(" ");
		int[] result = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++)
			result[i] = Integer.parseInt(tokens[i]);
		return result;
	}
}
