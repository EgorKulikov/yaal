package on2011_10.on2011_9_29.taskh0;



import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int money = in.readInt();
		Rational tax = Rational.ZERO;
		money = Math.max(money - 180000, 0);
		tax = tax.add(new Rational(money, 10));
		money = Math.max(money - 300000, 0);
		tax = tax.add(new Rational(money, 20));
		money = Math.max(money - 400000, 0);
		tax = tax.add(new Rational(money, 20));
		money = Math.max(money - 300000, 0);
		tax = tax.add(new Rational(money, 20));
		long payable = (tax.numerator + tax.denominator - 1) / tax.denominator;
		if (payable != 0)
			payable = Math.max(payable, 2000);
		out.println("Case " + testNumber + ": " + payable);
	}
}
