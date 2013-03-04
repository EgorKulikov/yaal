package net.egork;

public class SwitchesAndLamps {
	public int theMin(String[] switches, String[] lamps) {
		long[] source = new long[switches.length];
		long[] destination = new long[switches.length];
		for (int i = 0; i < switches.length; i++) {
			for (int j = 0; j < switches[i].length(); j++) {
				if (switches[i].charAt(j) == 'Y')
					source[i] += 1L << j;
				if (lamps[i].charAt(j) == 'Y')
					destination[i] += 1L << j;
			}
		}
		int answer = 0;
		long all = (1L << switches[0].length()) - 1;
		for (int i = 0; i < switches[0].length(); i++) {
			long current = 0;
			for (int j = 0; j < switches[0].length(); j++) {
				boolean good = true;
				for (String switche : switches) {
					if (switche.charAt(i) != switche.charAt(j)) {
						good = false;
					}
				}
				if (good)
					current += 1L << j;
			}
			long curDestination = all;
			for (int j = 0; j < source.length; j++) {
				if ((source[j] & current) != 0)
					curDestination &= destination[j];
				else
					curDestination &= all - destination[j];
			}
			if (Long.bitCount(curDestination) != Long.bitCount(current))
				return -1;
			for (int j = 0; ; j++) {
				if ((1L << j) >= Long.bitCount(current)) {
					answer = Math.max(answer, j);
					break;
				}
			}
		}
		return answer;
	}


}

