package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ArigeomBeats {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] frequencies = IOUtils.readIntArray(in, count);
		boolean[] processed = new boolean[count];
		for (int i = 0; i < count && i < 19; i++) {
			for (int j = i + 1; j < count && j < 19; j++) {
				int delta = frequencies[j] - frequencies[i];
				int next = frequencies[j] + delta;
				int good = 2;
				for (int k = j + 1; k < count; k++) {
					if (frequencies[k] > next)
						break;
					if (frequencies[k] == next) {
						good++;
						next += delta;
					}
				}
				if (count - good > 17)
					continue;
				Arrays.fill(processed, false);
				processed[i] = true;
				processed[j] = true;
				next = frequencies[j] + delta;
				for (int k = j + 1; k < count; k++) {
					if (frequencies[k] > next)
						break;
					if (frequencies[k] == next) {
						processed[k] = true;
						next += delta;
					}
				}
				if (good == count) {
					out.printLine(frequencies);
					out.printLine(frequencies[0], frequencies[1]);
					return;
				}
				Rational ratio = null;
				int last = -1;
				int firstIndex = -1;
				int lastIndex = -1;
				for (int k = 0; k < count; k++) {
					if (!processed[k]) {
						lastIndex = k;
						if (firstIndex == -1)
							firstIndex = k;
						if (last == -1)
							last = frequencies[k];
						else {
							Rational current = new Rational(frequencies[k], last);
							last = frequencies[k];
							if (ratio == null)
								ratio = current;
							else {
								while (!current.equals(Rational.ONE)) {
									if (current.compareTo(ratio) > 0) {
										Rational temp = ratio;
										ratio = current;
										current = temp;
									}
									if (ratio.numerator % current.numerator != 0 || ratio.denominator % current.denominator != 0) {
										ratio = null;
										break;
									}
									ratio = ratio.divide(current);
								}
								if (ratio == null)
									break;
							}
						}
					}
				}
				if (ratio == null && lastIndex != firstIndex)
					continue;
				if (firstIndex == lastIndex) {
					boolean first = true;
					for (int k = 0; k < count; k++) {
						if (k == firstIndex)
							continue;
						if (first)
							first = false;
						else
							out.print(' ');
						out.print(frequencies[k]);
					}
					out.printLine();
					if (firstIndex != 0)
						out.printLine(frequencies[0], frequencies[firstIndex]);
					else
						out.printLine(frequencies[0], frequencies[1]);
					return;
				}
				long current = frequencies[firstIndex];
				while (current != frequencies[lastIndex]) {
					current *= ratio.numerator;
					current /= ratio.denominator;
					if (Arrays.binarySearch(frequencies, (int) current) < 0) {
						ratio = null;
						break;
					}
				}
				if (ratio == null)
					continue;
				boolean first = true;
				for (int k = 0; k < count; k++) {
					if (!processed[k])
						continue;
					if (first)
						first = false;
					else
						out.print(' ');
					out.print(frequencies[k]);
				}
				out.printLine();
				current = frequencies[firstIndex];
				out.print(current);
				while (current != frequencies[lastIndex]) {
					current *= ratio.numerator;
					current /= ratio.denominator;
					out.print(" " + current);
				}
				out.printLine();
				return;
			}
		}
		throw new RuntimeException();
	}
}
