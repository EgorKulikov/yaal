package net.egork;

public class BigAndSmallAirports {
	public long solve(int Nlo, int Nhi, int Blo, int Bhi, int Slo, int Shi) {
		long answer = 0;
		for (int b = Blo; b <= Bhi; b++) {
			for (int s = Slo; s < b && s <= Shi; s++) {
				if (s == 1) {
					if (1 >= Nlo && 1 <= Nhi)
						answer++;
					if (2 >= Nlo && 2 <= Nhi)
						answer++;
				} else
					answer += Nhi - Nlo + 1;
				for (int l = 1; l <= b; l++) {
					int minK = Math.max((l * (b - l + 1) + s - 1) / s, Math.max(Nlo, b + 1) - l);
					answer += Math.max(0, Nhi - l - minK + 1);
				}
				if (Nhi <= b)
					continue;
				long curHi = Nhi - b;
				long curLo = Math.max(Nlo - b, 1);
				answer += curHi * (curHi + 1) / 2 - curLo * (curLo - 1) / 2;
			}
		}
		return answer;
	}


}

