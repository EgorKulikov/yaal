package net.egork;

public class FoxAndChess {
    public String ableToMove(String begin, String target) {
		int j = 0;
		for (int i = 0; i < begin.length(); i++) {
			if (begin.charAt(i) != '.') {
				while (j < target.length() && target.charAt(j) == '.')
					j++;
				if (j == target.length() || begin.charAt(i) != target.charAt(j) || begin.charAt(i) == 'R' && j < i ||
					begin.charAt(i) == 'L' && j > i)
				{
					return "Impossible";
				}
				j++;
			}
		}
		for (; j < target.length(); j++) {
			if (target.charAt(j) != '.')
				return "Impossible";
		}
		return "Possible";
    }
}
