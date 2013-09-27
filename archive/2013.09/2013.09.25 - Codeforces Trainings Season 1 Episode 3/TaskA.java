package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int withParents = in.readInt();
		int[] child = new int[withParents];
		int[] firstParent = new int[withParents];
		int[] secondParent = new int[withParents];
		IOUtils.readIntArrays(in, child, firstParent, secondParent);
		MiscUtils.decreaseByOne(child, firstParent, secondParent);
		boolean[] notProcessed = new boolean[count];
		for (int i : child)
			notProcessed[i] = true;
		BigRational[][] answer = new BigRational[count][count];
		for (int i = 0; i < count; i++) {
			if (notProcessed[i])
				continue;
			for (int j = 0; j < count; j++) {
				if (!notProcessed[j]) {
					if (i == j)
						answer[i][j] = BigRational.ONE;
					else
						answer[i][j] = BigRational.ZERO;
				}
			}
		}
		for (int i = 0; i < withParents; i++) {
			for (int j = 0; j < withParents; j++) {
				if (notProcessed[child[j]] && !notProcessed[firstParent[j]] && !notProcessed[secondParent[j]]) {
					answer[child[j]][child[j]] = BigRational.ONE;
					for (int k = 0; k < count; k++) {
						if (!notProcessed[k])
							answer[child[j]][k] = answer[k][child[j]] = BigRational.mean(answer[k][firstParent[j]], answer[k][secondParent[j]]);
					}
					notProcessed[child[j]] = false;
					break;
				}
			}
		}
		int queryCount = in.readInt();
		if (withParents == 0) {
			for (int i = 0; i < queryCount; i++) {
				int first = in.readInt() - 1;
				int second = in.readInt() - 1;
				if (first == second)
					out.printLine("100%");
				else
					out.printLine("0%");
			}
			return;
		}
		for (int i = 0; i < queryCount; i++) {
			int first = in.readInt() - 1;
			int second = in.readInt() - 1;
			BigRational total = answer[first][second];
			int length = total.denominator.bitLength() - 1;
			if (length < 2)
				length = 2;
			BigInteger result = new BigRational(total.numerator.multiply(BigInteger.TEN.pow(length)), total.denominator).numerator;
			String raw = result.toString();
			if (raw.length() == length + 1) {
				out.printLine("100%");
				continue;
			}
			StringBuilder builder = new StringBuilder();
			for (int j = length - raw.length(); j > 0; j--)
				builder.append('0');
			builder.append(raw);
			String curAnswer = builder.toString();
			for (int j = curAnswer.length() - 1; j >= 1; j--) {
				if (curAnswer.charAt(j) != '0' || j == 1) {
					curAnswer = curAnswer.substring(0, j + 1);
					break;
				}
			}
			int dotAt = 2;
			if (curAnswer.charAt(0) == '0') {
				curAnswer = curAnswer.substring(1);
				dotAt = 1;
			}
			out.print(curAnswer.substring(0, dotAt));
			if (curAnswer.length() > dotAt)
				out.print('.' + curAnswer.substring(dotAt));
			out.printLine("%");
		}
    }

	static class BigRational {
		static final BigRational ONE = new BigRational(BigInteger.ONE, BigInteger.ONE);
		static final BigRational ZERO = new BigRational(BigInteger.ZERO, BigInteger.ONE);

		final BigInteger numerator;
		final BigInteger denominator;


		BigRational(BigInteger numerator, BigInteger denominator) {
			BigInteger gcd = numerator.gcd(denominator);
			this.numerator = numerator.divide(gcd);
			this.denominator = denominator.divide(gcd);
		}

		static BigRational mean(BigRational first, BigRational second) {
			return new BigRational(first.numerator.multiply(second.denominator).add(second.numerator.multiply(first.denominator)), first.denominator.multiply(second.denominator).shiftLeft(1));
		}
	}
}
