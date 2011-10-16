import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class StrPack implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int l = in.readInt();
		if (n == 0 && l == 0) {
			Exit.exit(in, out);
			return;
		}
		final int[] f = new int[n];
		for (int i = 0; i < n; ++i) {
			f[i] = in.readInt();
		}
		Integer[] perm = new Integer[n];
		for (int i = 0; i < n; ++i)
			perm[i] = i;
		Arrays.sort(perm, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return f[o1] - f[o2];
			}
		});
		int[] fSorted = new int[n];
		for (int i = 0; i < n; ++i)
			fSorted[i] = f[perm[i]];
		double left = 0.0;
		double right = 1e12;
		double[] freq = new double[n];
		sums = new double[freq.length - 1];
		sumDepth = new int[freq.length - 1];
		sumParent = new int[freq.length - 1];
		freqParent = new int[freq.length];
		while (right - left > 5e-5) {
			double middle = (left + right) / 2;
			buildFreq(n, fSorted, freq, middle);
			if (fits(freq, l))
				right = middle;
			else
				left = middle;
		}
		buildFreq(n, fSorted, freq, right);
		if (!fits(freq, l)) throw new RuntimeException();
		int[] len = new int[freq.length];
		if (freq.length == 1) {
			len[0] = 1;
		} else {
			int[] sumLen = new int[freq.length - 1];
			for (int i = freq.length - 3; i >= 0; --i) {
				sumLen[i] = 1 + sumLen[sumParent[i]];
			}
			for (int i = freq.length - 1; i >= 0; --i) {
				len[perm[i]] = 1 + sumLen[freqParent[i]];
			}
		}
		long res = 0;
		for (int i = 0; i < freq.length; ++i)
			res += len[i] * (long) f[i];
		out.println(res);
		for (int i = 0; i < freq.length; ++i) {
			if (i > 0)
				out.print(" ");
			out.print(len[i]);
		}
		out.println();
	}

	double[] sums;
	int[] sumDepth;
	int[] sumParent;
	int[] freqParent;


	private boolean fits(double[] freq, int maxLen) {
		int nSums = 0;
		int pFreq = 0;
		int pSums = 0;
		while (freq.length - pFreq + nSums - pSums > 1) {
			double cur = 0;
			int curDepth = 1;
			for (int i = 0; i < 2; ++i) {
				if (pFreq == freq.length) {
					cur += sums[pSums];
					curDepth = Math.max(curDepth, sumDepth[pSums] + 1);
					sumParent[pSums] = nSums;
					++pSums;
				} else if (pSums == nSums) {
					cur += freq[pFreq];
					freqParent[pFreq] = nSums;
					++pFreq;
				} else if (freq[pFreq] < sums[pSums]) {
					cur += freq[pFreq];
					freqParent[pFreq] = nSums;
					++pFreq;
				} else {
					cur += sums[pSums];
					curDepth = Math.max(curDepth, sumDepth[pSums] + 1);
					sumParent[pSums] = nSums;
					++pSums;
				}
			}
			sums[nSums] = cur;
			sumDepth[nSums] = curDepth;
			++nSums;
			if (curDepth > maxLen)
				return false;
		}
		return true;
	}

	private void buildFreq(int n, int[] fSorted, double[] freq, double middle) {
		for (int i = 0; i < n; ++i) {
			/*double cur = fSorted[i];
			if (cur < middle)
				freq[i] = middle;
			else
				freq[i] = cur;*/
			freq[i] = fSorted[i] + middle;
		}
	}
}
