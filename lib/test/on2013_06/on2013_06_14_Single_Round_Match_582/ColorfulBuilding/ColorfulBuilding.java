package on2013_06.on2013_06_14_Single_Round_Match_582.ColorfulBuilding;



import net.egork.misc.ArrayUtils;
import net.egork.string.StringUtils;

import java.util.Arrays;

public class ColorfulBuilding {
	static final long MOD = (long) (1e9 + 9);

    public int count(String[] color1, String[] color2, int L) {
		int[] color = build(color1, color2);
		int maxColor = ArrayUtils.maxElement(color) + 1;
		int count = color.length;
		long[][] ways = new long[L + 1][maxColor];
		long[] total = new long[L + 1];
		total[0] = 1;
		int[] last = new int[maxColor];
		Arrays.fill(last, 1);
		int height = 1;
		for (int i : color) {
			long multiplier = 1;
			while (last[i] < height)
				multiplier = multiplier * (count - last[i]++) % MOD;
			last[i]++;
			for (int j = 1; j <= L; j++)
				ways[j][i] = ways[j][i] * multiplier % MOD;
			for (int j = L; j >= 1; j--) {
				long current = total[j - 1] - ways[j - 1][i] + ways[j][i];
				if (current >= MOD)
					current -= MOD;
				if (current < 0)
					current += MOD;
				ways[j][i] = (ways[j][i] * (count - height) + current) % MOD;
				total[j] = (total[j] * (count - height) + current) % MOD;
			}
			total[0] = total[0] * (count - height) % MOD;
			height++;
		}
		return (int) total[L];
    }

	private int[] build(String[] color1, String[] color2) {
		String s1 = StringUtils.unite(color1);
		String s2 = StringUtils.unite(color2);
		int[] result = new int[s1.length()];
		for (int i = 0; i < result.length; i++)
			result[i] = decode(s1.charAt(i)) + 52 * decode(s2.charAt(i));
		ArrayUtils.compress(result);
		return result;
	}

	private int decode(char c) {
		if (Character.isLowerCase(c))
			return c - 'a';
		return c - 'A' + 26;
	}
}
