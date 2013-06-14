package net.egork;

import net.egork.string.StringUtils;

public class TheNumberGame {
    public String determineOutcome(int A, int B) {
		if (Integer.toString(A).contains(Integer.toString(B)) || Integer.toString(A).contains(StringUtils.reverse(Integer.toString(B))))
			return "Manao wins";
		return "Manao loses";
    }
}
