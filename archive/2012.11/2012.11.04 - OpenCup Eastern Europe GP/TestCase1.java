package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

public class TestCase1 implements TestProvider {
	public Collection<Test> createTests() {
		String expr = "";
		for (int i = 0; i < 74; ++i) {
			expr += "(";
		}
		expr += "a";
		for (int i = 'b'; i <= 'z'; ++i) {
			expr += "+" + (char) (i) + ")";
		}
		for (int i = 26; i < 75; ++i) {
			expr += "+1)";
		}
		int p = 14999;
		while (!BigInteger.valueOf(p).isProbablePrime(100)) --p;
		return Collections.singleton(new Test(p + "\n" + expr + "\n", "9376"));
	}
}

