package net.egork;

import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int shivaPart = in.readInt();
		int vishnuPart = in.readInt();
		int sunPart = in.readInt();
		int bhvaniPart = in.readInt();
		int teacherPart = in.readInt();
		if (check(shivaPart) || check(vishnuPart) || check(sunPart) || check(bhvaniPart) || check(teacherPart))
			throw new RuntimeException();
		Rational teacherShare = Rational.ONE.subtract(new Rational(1, shivaPart).add(new Rational(1, vishnuPart)).
			add(new Rational(1, sunPart)).add(new Rational(1, bhvaniPart)));
		if (teacherShare.compareTo(Rational.ZERO) <= 0)
			throw new RuntimeException();
		Rational result = teacherShare.reverse().multiply(teacherPart);
		if (result.denominator != 1)
			throw new RuntimeException();
		if (result.numerator % shivaPart != 0 || result.numerator % vishnuPart != 0 || result.numerator % sunPart != 0
			|| result.numerator % bhvaniPart != 0)
		{
			out.println(-1);
			return;
		}
		out.println(result.numerator);
	}

	private boolean check(int number) {
		return number <= 0 || number > 100;
	}
}
