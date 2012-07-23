package net.egork;

import net.egork.numbers.IntegerUtils;

public class AkariDaisukiDiv1 {
	private static final long MOD = (long) (1e9 + 7);

	public long countF(String Waai, String Akari, String Daisuki, String S, String F, int k) {
		while (k > 0 && S.length() < F.length()) {
			S = Waai + S + Akari + S + Daisuki;
			k--;
		}
		if (k == 0)
			return count(S, 0, S.length(), F);
		int[] startOnce = new int[100];
		int[] startSure = new int[100];
		int[] middleOnce = new int[100];
		int[] middleSure = new int[100];
		int[] endOnce = new int[100];
		int[] endSure = new int[100];
		String start = Waai;
		String middle = Akari;
		String end = Daisuki;
		for (int i = 0; i < 100; i++) {
			int startCount = count(start, 0, Waai.length(), F);
			startOnce[i] = count(start + S, 0, Waai.length(), F) - startCount;
			startSure[i] = startCount;
			int middleCount = count(middle, Math.max(i * Daisuki.length() - F.length() + 1, 0), i * Daisuki.length() + Akari.length(), F);
			middleOnce[i] = count(S + middle + S, Math.max(i * Daisuki.length() - F.length() + 1 + S.length(), 0), i * Daisuki.length() + Akari.length() + S.length(), F) - middleCount;
			middleSure[i] = middleCount;
			int endCount = count(end, Math.max(i * Daisuki.length() - F.length() + 1, 0), end.length(), F);
			endOnce[i] = count(S + end, Math.max(i * Daisuki.length() - F.length() + 1 + S.length(), 0), S.length() + end.length(), F) - endCount;
			endSure[i] = endCount;
			start = Waai + start;
			middle = Daisuki + middle + Waai;
			end = end + Daisuki;
		}
		int totalStart = 0;
		int totalMiddle = 0;
		int totalEnd = 0;
		long answer = count(S, 0, S.length(), F) * IntegerUtils.power(2, k, MOD) % MOD;
		for (int i = 0; i < k && i < 100; i++) {
			totalStart = startSure[i];
			totalMiddle = middleSure[i];
			totalEnd = endSure[i];
			answer += (totalStart + startOnce[i] + totalMiddle + totalEnd + middleOnce[i] + endOnce[i]) * IntegerUtils.power(2, k - i - 1, MOD) % MOD;
		}
//		for (int i = 100; i < k; i++)
//			answer += (totalStart + totalMiddle + totalEnd) * IntegerUtils.power(2, k - i - 1, MOD) % MOD;
		if (k > 100)
			answer += (totalStart + totalMiddle + totalEnd) * (IntegerUtils.power(2, k - 100, MOD) - 1) % MOD;
		answer %= MOD;
		answer += MOD;
		answer %= MOD;
		return answer;
	}

	private int count(String s, int from, int to, String f) {
		int answer = -1;
		from--;
		do {
			answer++;
			from = s.indexOf(f, from + 1);
		} while (from != -1 && from < to);
		return answer;
	}

}

