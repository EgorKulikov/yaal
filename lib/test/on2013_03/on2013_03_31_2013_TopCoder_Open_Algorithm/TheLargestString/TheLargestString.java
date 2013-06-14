package on2013_03.on2013_03_31_2013_TopCoder_Open_Algorithm.TheLargestString;



import net.egork.collections.Pair;

public class TheLargestString {
    public String find(String s, String t) {
		Pair<String, String>[] answer = new Pair[s.length() + 1];
		answer[0] = Pair.makePair("", "");
		for (int i = 0; i < s.length(); i++) {
			answer[i + 1] = Pair.makePair(s.substring(0, i + 1), t.substring(0, i + 1));
			for (int j = i; j > 0; j--) {
				Pair<String, String> candidate = Pair.makePair(answer[j - 1].first + s.charAt(i), answer[j - 1].second + t.charAt(i));
				if (candidate.compareTo(answer[j]) > 0)
					answer[j] = candidate;
			}
		}
		String result = "";
		for (int i = 1; i <= s.length(); i++) {
			String candidate = answer[i].first + answer[i].second;
			if (candidate.compareTo(result) > 0)
				result = candidate;
		}
		return result;
    }
}
