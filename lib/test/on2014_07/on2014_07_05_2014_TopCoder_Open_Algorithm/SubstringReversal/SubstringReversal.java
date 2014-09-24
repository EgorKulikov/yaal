package on2014_07.on2014_07_05_2014_TopCoder_Open_Algorithm.SubstringReversal;



import net.egork.string.StringUtils;

public class SubstringReversal {
    public int[] solve(String S) {
		for (int i = 0; i < S.length(); i++) {
			char min = S.charAt(i);
			for (int j = i + 1; j < S.length(); j++) {
				min = (char) Math.min(min, S.charAt(j));
			}
			if (min == S.charAt(i)) {
				continue;
			}
			String result = S;
			int[] answer = new int[2];
			for (int j = i + 1; j < S.length(); j++) {
				if (S.charAt(j) == min) {
					String candidate = S.substring(0, i) + StringUtils.reverse(S.substring(i, j + 1)) + S.substring(j + 1);
					if (candidate.compareTo(result) < 0) {
						result = candidate;
						answer[0] = i;
						answer[1] = j;
					}
				}
			}
			return answer;
		}
		return new int[]{0, 0};
    }
}
