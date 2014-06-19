package net.egork;

import net.egork.numbers.IntegerUtils;

public class PalindromePermutations {
    public double palindromeProbability(String word) {
		int[] qty = new int[26];
		for (char c : word.toCharArray())
			qty[c - 'a']++;
		double total = 1;
		double palindrome = 1;
		int remainingTotal = word.length();
		int remainingPalindrome = word.length() / 2;
		int remainingOdd = word.length() % 2;
		long[][] c = IntegerUtils.generateBinomialCoefficients(remainingTotal + 1);
		for (int i : qty) {
			total *= c[remainingTotal][i];
			palindrome *= c[remainingPalindrome][i / 2];
			remainingTotal -= i;
			remainingPalindrome -= i / 2;
			remainingOdd -= i % 2;
		}
		if (remainingOdd < 0)
			return 0;
		return (double) palindrome / total;
    }
}
