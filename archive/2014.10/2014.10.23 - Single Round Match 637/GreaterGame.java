package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intcollection.IntSet;
import net.egork.collections.sequence.Array;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class GreaterGame {
    public double calc(int[] hand, int[] sothe) {
		IntSet unknown = new IntHashSet();
		for (int i = 1; i <= 2 * hand.length; i++) {
			unknown.add(i);
		}
		for (int i : hand) {
			unknown.remove(i);
		}
		for (int i : sothe) {
			unknown.remove(i);
		}
		Arrays.sort(hand);
		Arrays.sort(sothe);
		int at = 0;
		while (at < sothe.length && sothe[at] == -1) {
			at++;
		}
		double answer = 0;
		for (int i = 0; i < hand.length; i++) {
			if (at < sothe.length && hand[i] > sothe[at]) {
				at++;
				hand[i] = -1;
				answer++;
			}
		}
		Arrays.sort(hand);
		ArrayUtils.reverse(hand);
		for (int i = 0; i < unknown.size(); i++) {
			for (int j : unknown.toArray()) {
				if (hand[i] > j) {
					answer += 1d / unknown.size();
				}
			}
		}
		return answer;
    }
}
