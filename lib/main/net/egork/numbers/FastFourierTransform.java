package net.egork.numbers;

<<<<<<< local
class FastFourierTransform {
=======
/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public class FastFourierTransform {
>>>>>>> other
	public void fft(double[] a, double[] b, boolean invert) {
		int count = a.length;
		for (int i = 1, j = 0; i < count; i++) {
			int bit = count >> 1;
			for (; j >= bit; bit >>= 1)
				j -= bit;
			j += bit;
			if (i < j) {
				double temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				temp = b[i];
				b[i] = b[j];
				b[j] = temp;
			}
		}
		for (int len = 2; len <= count; len <<= 1) {
			int halfLen = len >> 1;
			double angle = 2 * Math.PI / len;
			if (invert)
				angle = -angle;
			double wLenA = Math.cos(angle);
			double wLenB = Math.sin(angle);
			for (int i = 0; i < count; i += len) {
				double wA = 1;
				double wB = 0;
<<<<<<< local
=======
				int f = i;
				int s = i + halfLen;
>>>>>>> other
				for (int j = 0; j < halfLen; j++) {
<<<<<<< local
					double uA = a[i + j];
					double uB = b[i + j];
					double vA = a[i + j + halfLen] * wA - b[i + j + halfLen] * wB;
					double vB = a[i + j + halfLen] * wB + b[i + j + halfLen] * wA;
					a[i + j] = uA + vA;
					b[i + j] = uB + vB;
					a[i + j + halfLen] = uA - vA;
					b[i + j + halfLen] = uB - vB;
=======
					double vA = a[s] * wA - b[s] * wB;
					double vB = a[s] * wB + b[s] * wA;
					a[s] = a[f] - vA;
					b[s] = b[f] - vB;
					a[f] += vA;
					b[f] += vB;
>>>>>>> other
					double nextWA = wA * wLenA - wB * wLenB;
					wB = wA * wLenB + wB * wLenA;
					wA = nextWA;
<<<<<<< local
=======
					f++;
					s++;
>>>>>>> other
				}
			}
		}
		if (invert) {
			for (int i = 0; i < count; i++) {
				a[i] /= count;
				b[i] /= count;
			}
		}
	}

	public long[] multiply(long[] a, long[] b) {
		int resultSize = Integer.highestOneBit(Math.max(a.length, b.length) - 1) << 2;
		resultSize = Math.max(resultSize, 1);
		double[] aReal = new double[resultSize];
		double[] aImaginary = new double[resultSize];
		double[] bReal = new double[resultSize];
		double[] bImaginary = new double[resultSize];
		for (int i = 0; i < a.length; i++)
			aReal[i] = a[i];
		for (int i = 0; i < b.length; i++)
			bReal[i] = b[i];
		fft(aReal, aImaginary, false);
		fft(bReal, bImaginary, false);
		for (int i = 0; i < resultSize; i++) {
			double real = aReal[i] * bReal[i] - aImaginary[i] * bImaginary[i];
			aImaginary[i] = aImaginary[i] * bReal[i] + bImaginary[i] * aReal[i];
			aReal[i] = real;
		}
		fft(aReal, aImaginary, true);
		long[] result = new long[resultSize];
		for (int i = 0; i < resultSize; i++)
			result[i] = Math.round(aReal[i]);
		return result;
	}
}
