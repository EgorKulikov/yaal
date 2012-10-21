package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RecipeReconstruction {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] recipe = in.readString().toCharArray();
		int answer = 1;
		int length = (recipe.length + 1) / 2;
		for (int i = 0; i < length; i++) {
			if (recipe[i] == '?' && recipe[recipe.length - i - 1] == '?') {
				answer *= 26;
				answer %= 10000009;
			} else if (recipe[i] != '?' && recipe[recipe.length - i - 1] != '?' && recipe[i] != recipe[recipe.length - i - 1]) {
				answer = 0;
				break;
			}
		}
		out.printLine(answer);
	}
}
