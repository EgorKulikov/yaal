package net.egork;

import java.util.Arrays;

public class FoxAndHandle {
	public String lexSmallestName(String S) {
		char[] sCopy = S.toCharArray();
		Arrays.sort(sCopy);
		int count = sCopy.length / 2;
		char[] required = new char[count];
		for (int i = 0; i < count; i++)
			required[i] = sCopy[2 * i];
		sCopy = S.toCharArray();
		boolean[] taken = new boolean[count];
		StringBuilder result = new StringBuilder();
		int[] need = new int[256];
		for (char c : required)
			need[c]++;
		int position = 0;
		int[] current = new int[256];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (taken[j])
					continue;
				Arrays.fill(current, 0);
				boolean on = false;
				int at = -1;
				for (int k = position; k < 2 * count; k++) {
					if (sCopy[k] == required[j] && !on) {
						on = true;
						at = k;
					}
					if (on)
						current[sCopy[k]]++;
				}
				boolean good = true;
				for (int k = 'a'; k <= 'z'; k++) {
					if (need[k] > current[k]) {
						good = false;
						break;
					}
				}
				if (good) {
					taken[j] = true;
					result.append(required[j]);
					need[required[j]]--;
					position = at + 1;
					break;
				}
			}
		}
		return result.toString();
	}
}
