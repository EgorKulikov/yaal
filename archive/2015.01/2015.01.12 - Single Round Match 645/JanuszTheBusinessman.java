package net.egork;

public class JanuszTheBusinessman {
    public int makeGuestsReturn(int[] arrivals, int[] departures) {
		boolean[] good = new boolean[arrivals.length];
		int remaining = arrivals.length;
		int answer = 0;
		while (remaining > 0) {
			answer++;
			int firstDeparture = Integer.MAX_VALUE;
			for (int i = 0; i < arrivals.length; i++) {
				if (!good[i]) firstDeparture = Math.min(firstDeparture, departures[i]);
			}
			int bestId = -1;
			for (int i = 0; i < arrivals.length; i++) {
				if (arrivals[i] <= firstDeparture && (bestId == -1 || departures[i] > departures[bestId])) bestId = i;
			}
			for (int i = 0; i < arrivals.length; i++) {
				if (!good[i] && departures[i] >= arrivals[bestId] && arrivals[i] <= departures[bestId]) {
					good[i] = true;
					remaining--;
				}
			}
		}
		return answer;
    }
}
